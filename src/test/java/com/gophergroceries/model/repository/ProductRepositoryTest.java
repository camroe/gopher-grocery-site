
package com.gophergroceries.model.repository;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class ProductRepositoryTest {

	Product product = new Product();

	@Autowired
	ProductsRepository repository;

	@Before
	public void executedBeforeEachTest() {
		product.setSku("TestSKU");
		product.setName("Test Name");
		product.setDescription("Test Description");
		product.setPrice(new BigDecimal("1.99"));
		product.setInventory(1);
		product.setPopular("Y");
		product.setImage("Test Image Location");
		product.setCategory("TestCategory");
		System.out.println(product);
	}

	@Test
	public void testSimpleGetSetSingleProduct() {

		repository.save(product);

		Product dbProduct = repository.findOne(product.getId());
		assertNotNull(dbProduct);
		System.out.println(product);
		assertNotNull(product);
	}

	@Test
	public void testCreateDelete() {
		repository.save(product);

		Product dbProduct = repository.findOne(product.getId());
		assertNotNull(dbProduct);
		System.out.println("PRODUCT: " + product);
		System.out.println("DBPRODUCT:" + dbProduct);
		repository.delete(product.getId());
		product = repository.findOne(dbProduct.getId());
		assert (null == product);
	}

	@Test
	public void testFindByPopular() {
		List<Product> returnList = repository.findByPopularIgnoreCase("Y");
		for (Product product : returnList) {
			System.out.println("POPULAR PRODUCT: " + product);
		}
	}
}
