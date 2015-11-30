package com.gophergroceries.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gophergroceries.model.entities.ConfirmedOrdersEntity;

public interface ConfirmedOrdersRepository extends JpaRepository<ConfirmedOrdersEntity, Integer> {
	List<ConfirmedOrdersEntity> findBy(Integer id);

	ConfirmedOrdersEntity findOneBySessionID(String sessionID);

	ConfirmedOrdersEntity findOneByConfirmationID(String confirmationID);

	List<ConfirmedOrdersEntity> findAllByEmail(String email);

	ConfirmedOrdersEntity findOneByUsername(String username);

	ConfirmedOrdersEntity findOneByemail(String email);
}
