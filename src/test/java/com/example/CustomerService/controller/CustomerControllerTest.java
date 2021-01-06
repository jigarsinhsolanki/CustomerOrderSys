package com.example.CustomerService.controller;


import com.example.CustomerService.entity.Customer;
import com.example.CustomerService.repository.CartRepository;
import com.example.CustomerService.repository.CustomerRepository;
import com.example.CustomerService.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;






    @WebAppConfiguration
    @WebMvcTest(CustomerController.class)
    @ContextConfiguration(classes = {com.example.CustomerService.repository.CartRepository.class})
    public class CustomerControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

//        @Configuration
//        @ComponentScan
//        @EnableWebMvc
//        public class TestConfiguration extends WebMvcConfigurationSupport {
//            @Bean
//            public CustomerService customerService(){
//                return mock(CustomerService.class);
//            }
//        }

        @MockBean
        private CustomerRepository customerRepository;

        @Test
        public void testListCustomers() throws Exception {

            List<Customer> customerList = new ArrayList<>();
            customerList.add(new Customer(9,"Jack","jack@gmail.com","2003 Peral vista",232342200,23234221));
            Mockito.when(customerRepository.findAll()).thenReturn(customerList);

            String url = "/customers";

            MvcResult mvcResult= mockMvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();


            String actualJsonResponse = mvcResult.getResponse().getContentAsString();
            System.out.println(actualJsonResponse);

            String expectedJsonResponse= objectMapper.writeValueAsString(customerList);

            assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

        }

        @Test
        void addCustomer() throws Exception {

            Customer newCustomer=new Customer(9,"Jack","jack@gmail.com","2003 Peral vista",232342200,23234221);
            Customer savedCustomer=new Customer(9,"Jack","jack@gmail.com","2003 Peral vista",232342200,23234221);

            Mockito.when(customerRepository.save(newCustomer)).thenReturn(savedCustomer);

            String url="/addCustomer";
            mockMvc.perform(
                    post(url)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(newCustomer)))
                            .andExpect(status().isOk())
                            .andExpect(content().string("Jack")).andDo(print());

        }

        @Test
        void updateCustomer() throws Exception {

            Customer updateCustomer=new Customer(9,"Lancerbhai","lancerbhai@gmail.com","2003 Peral vista",232342200,23234221);
            Customer savedCustomer=new Customer(9,"Lancerbhai","lancerbhai@gmail.com","2003 Peral vista",232342200,23234221);

            Mockito.when(customerRepository.save(updateCustomer)).thenReturn(savedCustomer);

            String url="/updateCustomer";
            mockMvc.perform(
                    put(url)
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(updateCustomer)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lancerbhai")).andDo(print());

        }

        @Test
        public void deleteCustomer() throws Exception {
            Integer customerId=1;

            Mockito.doNothing().when(customerRepository).deleteById(customerId);

            String url="/delete/"+customerId;

            mockMvc.perform(delete(url)).andExpect(status().isOk());

            Mockito.verify(customerRepository,times(1)).deleteById(customerId);


        }



    }
