package lk.krishan_mobile.asset.customer.controller;


import lk.krishan_mobile.asset.common_asset.model.enums.LiveDead;
import lk.krishan_mobile.asset.common_asset.model.enums.Title;
import lk.krishan_mobile.asset.customer.entity.Customer;
import lk.krishan_mobile.asset.customer.service.CustomerService;
import lk.krishan_mobile.util.interfaces.AbstractController;
import lk.krishan_mobile.util.service.EmailService;
import lk.krishan_mobile.util.service.MakeAutoGenerateNumberService;
import lk.krishan_mobile.util.service.TwilioMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public  class CustomerController {
    private final CustomerService customerService;
    private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;
    private final EmailService emailService;
    private final TwilioMessageService twilioMessageService;

    @Autowired
    public CustomerController(CustomerService customerService, MakeAutoGenerateNumberService makeAutoGenerateNumberService, EmailService emailService, TwilioMessageService twilioMessageService) {
        this.customerService = customerService;
        this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
        this.emailService = emailService;
        this.twilioMessageService = twilioMessageService;
    }

    private String commonThings(Model model, Customer customer, Boolean addState) {
        model.addAttribute("title", Title.values());
        model.addAttribute("customer", customer);
        model.addAttribute("addStatus", addState);
        return "customer/addCustomer";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.findAll().stream()
            .filter(x-> LiveDead.ACTIVE.equals(x.getLiveDead()))
            .collect(Collectors.toList()));
        return "customer/customer";
    }


    // @Override
    // public String findById(Integer id, Model model) {
    //     return null;
    // }

    @GetMapping("/add")
    public String addForm(Model model) {
        return commonThings(model, new Customer(), true);
    }

    @PostMapping(value = {"/save", "/update"})
    public String persist(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Customer dbCustomer = customerService.findByNic(customer.getNic());

        if(dbCustomer != null){
                ObjectError error = new ObjectError("customer",
                                          "There is already in the system. <br>System message -->");
      bindingResult.addError(error);

    //   model.addAttribute("ExCustomer", dbCustomer);
      return commonThings(model, customer, true);
        }

        if (bindingResult.hasErrors()) {
            return commonThings(model, customer, true);
        }
        
//NIC Validator
        // if (customerService.findByNic(nic) != null) {

        //     Customer customer2 = customerService.findByNic(nic);

        //     model.addAttribute("ExCustomer", customer2);

        
        //     // return "customer/addCustomer";
        //     return commonThings(model, customer2, true);

        // }

//phone number length validator
        if (customer.getMobile() != null) {
            customer.setMobile(makeAutoGenerateNumberService.phoneNumberLengthValidator(customer.getMobile()));
        }

//if customer has id that customer is not a new customer
        if (customer.getId() == null) {
            //if there is not customer in db
            if (customerService.lastCustomer() == null) {
                System.out.println("last customer null");
                //need to generate new one
                customer.setCode("KMSC"+makeAutoGenerateNumberService.numberAutoGen(null).toString());
            } else {
                System.out.println("last customer not null");
                //if there is customer in db need to get that customer's code and increase its value
                String previousCode = customerService.lastCustomer().getCode().substring(4);
                customer.setCode("KMSC"+makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());
            }
            //send welcome message and email
            if (customer.getEmail() != null) {
                emailService.sendEmail(customer.getEmail(), "Welcome Message", "Welcome to Krishna mobile...");
            }
            if (customer.getMobile() != null) {
            //    twilioMessageService.sendSMS(customer.getMobile(), "Welcome to Krishna mobile");
            }

        }

        redirectAttributes.addFlashAttribute("customerDetail", customerService.persist(customer));
        return "redirect:/customer";
    }

    // @GetMapping("/save/{nic}")
    // public boolean validateNIC(@PathVariable String nic, Model model, Customer customer) {

    //     List<String> NICList = (List<String>) customerService.findByNic(nic);

    //    if (nic != NICList.get(Integer.parseInt(nic))){

    //        return true;
    //    }

    //     return false;
    // }
    
//     @GetMapping("/save")
//     public String validateNIC(@RequestParam("nic") String nic, Model model){
    
//             Customer customer = customerService.findByNic(nic);
    
//             if(customer == null) {
//                 return "customer/add";
//     }else{

//         model.addAttribute("customerExists", customer);
//         System.out.println(customer.getEmail());
        
//     }
//     return "customer/addCustomer";
// }
        
    

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        return commonThings(model, customerService.findById(id), false);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        customerService.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("customerDetail", customerService.findById(id));
        return "customer/customer-detail";
    }

   
    // public String persist(@Valid Customer e, BindingResult bindingResult, RedirectAttributes redirectAttributes,
    //         Model model) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'persist'");
    // }

}
