package com.example.CustomerService.repository;

import com.example.CustomerService.entity.CartEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    //CartEntity findByName(String name);

    @Query("SELECT c FROM CartEntity c where c.customerId = :customerId")
    List<CartEntity> findByName(@Param("customerId") int customerId);
}
