package com.codegym.demo.controller;

import com.codegym.demo.model.Customer;
import com.codegym.demo.service.CustomerService;
import com.codegym.demo.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService = new CustomerService();

    @GetMapping()
    public ModelAndView getAllCustomer(){
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customerArrayList = customerService.findAll();
        modelAndView.addObject("customers", customerArrayList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        return new ModelAndView("create");
    }
//    @PostMapping("/create")
//    public ModelAndView createNewCustomer(@RequestParam String name, String address, String email){
//        Customer customer = new Customer(1, name, address, email);
//        return new ModelAndView("create");
//    }
    @PostMapping("/create")
    public String createNewCustomer(Customer customer){
//        Customer customer = new Customer(1, name, address, email);
        customer.setId((int) (Math.random()*1000));
        customerService.save(customer);
        return "redirect:/customers";
    }
}
