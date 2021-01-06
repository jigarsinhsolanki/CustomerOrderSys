package com.example.CustomerService.service;

import com.example.CustomerService.entity.CartEntity;
import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.repository.CartRepository;
import com.example.CustomerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    public Customer saveCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }


    public List<Customer> saveCustomers(List<Customer> customers)
    {
        return customerRepository.saveAll(customers);
    }

    public List<Customer> getCustomers(){

        return customerRepository.findAll();
    }
    public Customer getCustomerById(int id){

        return customerRepository.findById(id).orElse(null);
    }

    public Customer getCustomerByName(String name){

        return customerRepository.findByName(name);
    }

    public String deleteCustomer(int id){

        customerRepository.deleteById(id);
        return "customer removed !! "+id;
    }

    public Customer updateCustomer(Customer customer)
    {
        Customer existingCustomer=customerRepository.findById(customer.getCustomerId()).orElse(null);
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmailId(customer.getEmailId());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setCard(customer.getCard());
        return customerRepository.save(existingCustomer);


    }

    public CartEntity addToCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    public CartEntity cartTotal(int id) {
        List<CartEntity> cartEntityList = cartRepository.findByName(id);
        double sumTotal = 0.0;
        for(CartEntity c : cartEntityList){
            sumTotal += (c.getPrice()*c.getQuantity());
        }
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartTotal(sumTotal);
        cartEntity.setCartEntityList(cartEntityList);
        return cartEntity;
    }
}
