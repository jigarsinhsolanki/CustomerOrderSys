package com.example.CustomerService.controller;

import com.example.CustomerService.entity.CartEntity;
import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){

        return customerService.saveCustomer(customer);
    }

    @PostMapping("/addCustomers")
    public List<Customer> addCustomers(@RequestBody List<Customer> customers){

        return customerService.saveCustomers(customers);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){

        return customerService.getCustomers();
    }
    @GetMapping("/customers/{id}")
    public Customer findCustomertById(@PathVariable int id){

        return customerService.getCustomerById(id);
    }

    @GetMapping("/customer/{name}")
    public Customer findCustomerByName(@PathVariable String name){

        return customerService.getCustomerByName(name);
    }

    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer){

        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id){

        return customerService.deleteCustomer(id);
    }

    @PostMapping("/addToCart")
    public CartEntity addToCart(@RequestBody CartEntity cartEntity){

        return customerService.addToCart(cartEntity);
    }

    @GetMapping("/cartTotal/{id}")
    public CartEntity cartTotal(@PathVariable int id){

        return customerService.cartTotal(id);
    }
}

