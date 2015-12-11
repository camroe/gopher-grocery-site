package com.gophergroceries.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gophergroceries.model.entities.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
	List<OrdersEntity> findBy(Integer id);

	OrdersEntity findOneBySessionID(String sessionID);

	List<OrdersEntity> findAllByEmail(String email);

	OrdersEntity findOneByUsername(String username);

	OrdersEntity findOneByemail(String email);

}
