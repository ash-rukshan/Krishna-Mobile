package lk.krishan_mobile.asset.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lk.krishan_mobile.asset.customer.entity.Customer;
import lk.krishan_mobile.asset.customer.service.CustomerService;
import lk.krishan_mobile.asset.common_asset.model.enums.Title;

@Controller
@RequestMapping("/search")
public class CustomerSearchController {

    private final CustomerService customerService;

    @Autowired
    public CustomerSearchController(CustomerService customerService){
        this.customerService = customerService;
    }

    private String commonThings(Model model, Customer customer, Boolean addState) {
        model.addAttribute("title", Title.values());
        model.addAttribute("customer", customer);
        model.addAttribute("addStatus", addState);
        return "customer/addCustomer";
    }

/* 
    @PostMapping("/searchPost")
    public String SearchByNic(@RequestParam("nicSearch") String nic, Model model){

        Customer customer = customerService.findByNic(nic);
        model.addAttribute("nic",customer.getNic());
        model.addAttribute("name",customer.getName());
        model.addAttribute("code",customer.getCode());
        model.addAttribute("mobile",customer.getMobile());
        model.addAttribute("email",customer.getEmail());
         return "customer/addCustomer";
    } */
    
    // @GetMapping("/edit/{nic}")
    // public String edit(@RequestParam("nicSearch") String nic, Model model) {

    //     Customer customer = customerService.findByNic(nic);
    //     model.addAttribute("nic",customer.getNic());
    //     model.addAttribute("name",customer.getName());
    //     model.addAttribute("code",customer.getCode());
    //     model.addAttribute("mobile",customer.getMobile());
    //     model.addAttribute("email",customer.getEmail());

    //     return commonThings(model, customerService.findByNic(nic), false);
        
        
    // }
}
