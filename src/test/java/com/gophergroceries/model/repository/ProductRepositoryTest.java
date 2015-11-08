
package com.gophergroceries.model.repository;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.ProductEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class ProductRepositoryTest {

	ProductEntity product = new ProductEntity();

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
		product.setImageFile("Test Image Location");
		product.setCategory("TestCategory");
		System.out.println(product);
	}

	@Test
	public void testSimpleGetSetSingleProduct() {

		repository.save(product);

		ProductEntity dbProduct = repository.findOne(product.getId());
		assertNotNull(dbProduct);
		System.out.println(product);
		assertNotNull(product);
		repository.delete(dbProduct.getId());
	}

	@Test
	public void testCreateDelete() {
		repository.save(product);

		ProductEntity dbProduct = repository.findOne(product.getId());
		assertNotNull(dbProduct);
		System.out.println("PRODUCT: " + product);
		System.out.println("DBPRODUCT:" + dbProduct);
		repository.delete(product.getId());
		product = repository.findOne(dbProduct.getId());
		assert (null == product);
	}

	@Test
	public void testFindByPopular() {
		List<ProductEntity> returnList = repository.findByPopularIgnoreCase("Y");
		for (ProductEntity product : returnList) {
			System.out.println("POPULAR PRODUCT: " + product);
		}
	}
}
