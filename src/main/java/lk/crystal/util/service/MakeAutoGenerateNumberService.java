package lk.crystal.util.service;


import lk.crystal.asset.customer.entity.Customer;
import lk.crystal.asset.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;

@Service
public class MakeAutoGenerateNumberService {

    @Autowired
    public final CustomerService customerService;
    private final DateTimeAgeService dateTimeAgeService;

    public MakeAutoGenerateNumberService(DateTimeAgeService dateTimeAgeService) {
        this.dateTimeAgeService = dateTimeAgeService;
        customerService = null;
    }

    public Integer numberAutoGen(String lastNumber) {
        int newNumber;
        int previousNumber;
        int newNumberFirstTwoCharacters;

        int currentYearLastTwoNumber =
                Integer.parseInt(String.valueOf(dateTimeAgeService.getCurrentDate().getYear()).substring(2, 4));
//if it has own number
        if (lastNumber != null) {
            previousNumber = Integer.parseInt(lastNumber);
            //first two digits of last record
            newNumberFirstTwoCharacters = Integer.parseInt(lastNumber.substring(0, 2));
//if first two number is equal
            if (currentYearLastTwoNumber == newNumberFirstTwoCharacters) {
                newNumber = previousNumber + 1;
            } else {
                newNumber = Integer.parseInt(currentYearLastTwoNumber + "0000");
            }
        }
        // if it has not own last number
        else {
            newNumber = Integer.parseInt(currentYearLastTwoNumber + "0000");
        }
        System.out.println("new number "+ newNumber);
        return newNumber;
    }
    // phone number length validator
    public String phoneNumberLengthValidator(String number) {
        if ( number.length() == 9 ) {
            number = "0".concat(number);
        }
        return number;
    }

    // phone number length validator

    public String NICchecker(String nic) {
        Customer customer = customerService.findByNic(nic);
        if (customer.getNic()!= null) {
            return (customer.getNic());
        }
        return null;
    }

}
