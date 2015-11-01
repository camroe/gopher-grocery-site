package com.gophergroceries.model.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.Orders;
import com.gophergroceries.model.repository.OrdersRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class OrdersRepositoryTest {

	@Autowired
	OrdersRepository repository;

	@Test
	public void testSimplePutGet() {
		Orders orders = new Orders();
		orders.setCreationDate(new Date(System.currentTimeMillis()));
		orders.setEmail("camroe@gmail.com");
		orders.setItems("There be items here");
		repository.save(orders);

		Orders dbOrders = repository.findOne(orders.getId());
		assertNotNull(dbOrders);
		System.out.println(orders.getEmail());
	}

}

