package com.gophergroceries.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.AddToCartForm;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.results.AddToOrderResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class OrderServiceTest {

	private final String SESSION_ID = "TestSessionID";
	private final String EMAIL = "camroe@gmail.com";
	private final String PAYPAL_CONFIRMATION = "Test PayPal Confirmation";
	private final String CONFIRMATION_ID = "TestConfirmation";

	@Autowired
	OrderService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetOrderWithSessionID() {
		OrdersEntity oe = orderService.getOrderWithSessionID(SESSION_ID);
		assertNotNull(oe);
	}

	@Test
	public void testGetOrderWithConfirmationID() {
		OrdersEntity oe = orderService.getOrderWithConfirmationID(CONFIRMATION_ID);
		assertNotNull(oe);
	}

	@Test
	public void testAddItemToOrder() {
		AddToCartForm actf = new AddToCartForm();
		actf.setCartkey("");
		actf.setId("1");// product
		actf.setQuantity("10");
		actf.setSessionID(SESSION_ID);
		actf.setSku("SKU01");

		AddToOrderResult ator = orderService.addItemToOrder(actf);
		assertNotNull("Add to order Result is null", ator);
	}

}
