package com.example.CustomerService.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_entity")
//@Service

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartEntity {
    @Id @GeneratedValue private int cartId;
    private int customerId;
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    @Transient
    private double cartTotal;
    @Transient
    private List<CartEntity> cartEntityList;
}
