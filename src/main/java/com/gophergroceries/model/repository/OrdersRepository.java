package com.gophergroceries.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gophergroceries.model.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
