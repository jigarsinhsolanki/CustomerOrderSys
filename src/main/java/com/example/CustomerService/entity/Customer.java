package com.example.CustomerService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="Customer")
public class Customer {

    @Id
    @GeneratedValue
    private int CustomerId;
    private String name;
    private String emailId;
    private String address;
    private long phone;
    private long card;
}
