package com.codegym.demo.controller;

import com.codegym.demo.model.Customer;
import com.codegym.demo.model.CustomerForm;
import com.codegym.demo.service.CustomerService;
import com.codegym.demo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/customers")
@PropertySource("classpath:upload_file.properties")
public class CustomerController {
    @Value("${file-upload}")
    private String fileUpload;

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
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer",
                new CustomerForm());
        return modelAndView;
    }
//    @PostMapping("/create")
//    public ModelAndView createNewCustomer(@RequestParam String name, String address, String email){
//        Customer customer = new Customer(1, name, address, email);
//        return new ModelAndView("create");
//    }
    @PostMapping("/create")
    public String createNewCustomer(CustomerForm customerForm){

        //lấy ra file
        MultipartFile multipartFile = customerForm.getAvatar();
        //lay ten file
        String fileName = multipartFile.getOriginalFilename();
        String name = customerForm.getName();
        String address = customerForm.getAddress();
        String email = customerForm.getEmail();
        int id = (int) (Math.random()*10000);
        //copy
        try {
            System.out.println(fileUpload+ fileName);
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload+ fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tao mới customer thông thường
        Customer customer = new Customer(id, name, email, address);
        customer.setAvatar(fileName);
        customerService.save(customer);
        return "redirect:/customers";
    }
}
