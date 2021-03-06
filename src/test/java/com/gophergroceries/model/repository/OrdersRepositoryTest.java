package com.gophergroceries.model.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.OrderLinesEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.entities.ProductEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class OrdersRepositoryTest {

	@Autowired
	OrdersRepository repository;

	@Autowired
	ProductsRepository productRepository;

	private OrdersEntity order = new OrdersEntity();
	private OrdersEntity orderDB = new OrdersEntity();

	private SortedSet<OrderLinesEntity> orderlines = new TreeSet<OrderLinesEntity>();
	private final String SESSION_ID = "TestSessionID";
	private final String EMAIL = "camroe@gmail.com";

	@Before
	public void setupBeforeAll() {
		orderDB = repository.findOneBySessionID(SESSION_ID);
		if (orderDB == null) {
			Iterable<ProductEntity> products = productRepository.findAll();
			for (ProductEntity pe : products) {
				OrderLinesEntity ole = new OrderLinesEntity();
				ole.setOrder(order);// must be done to manage the relationship, but all
				// line items will be on the same order.
				ole.setPrice(pe.getPrice());
				ole.setProduct(pe);
				;
				ole.setQuantity(1);
				orderlines.add(ole);
			}
			// Set up order

			order.setCreationdate(new Date(System.currentTimeMillis()));
			order.setEmail(EMAIL);
			order.setOrderlines(orderlines);
			order.setSessionID(SESSION_ID);
			orderDB = repository.save(order);

		}
	}

	@Test
	public void testSimplePutGet() {
		OrdersEntity order = new OrdersEntity();
		order = repository.findOne(orderDB.getId());
		assertNotNull(order);
		System.out.println(order.getEmail());
	}

	@Test
	public void testPayPayRetrieval() {
		OrdersEntity order = new OrdersEntity();
		order = repository.findOneBySessionID(SESSION_ID);
		assertNotNull(order);
		System.out.println(order.getSessionID());
	}

	@Test
	public void testEmailRetrieval() {
		List<OrdersEntity> orders = repository.findAllByEmail(EMAIL);
		assertNotNull(orders);
		for (OrdersEntity oe : orders) {
			System.out.println("Order: " + oe.getId() + " : " + oe.getEmail());

		}
	}

}
