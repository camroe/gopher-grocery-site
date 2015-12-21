package com.gophergroceries.model.repository;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.CategoryEntity;
import com.gophergroceries.model.entities.SubCategoryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class CategoryRepositoryTest {

	private CategoryEntity cat = new CategoryEntity();
	private SubCategoryEntity subCat = new SubCategoryEntity();
	private Set<SubCategoryEntity> subCatSet = new HashSet<SubCategoryEntity>();

	@Autowired
	private CategoryRepository repository;

	@Before
	public void executedBeforeEachTest() {
		cat.setName("TestCategory");
		cat.setUrladdress("This is the url #");

		subCatSet.clear();
		subCat = new SubCategoryEntity();
		subCat.setCat(cat);// must be done to manage relationship
		subCat.setDisplayname("SubCategory One");
		subCat.setUrladdress("endpoint for Subcategory One");
		subCatSet.add(subCat);

		subCat = new SubCategoryEntity();
		subCat.setCat(cat);
		subCat.setDisplayname("SubCategory Two");
		subCat.setUrladdress("endpoint for Subcategory Two");
		subCatSet.add(subCat);

		subCat = new SubCategoryEntity();
		subCat.setCat(cat);
		subCat.setDisplayname("SubCategory Three");
		subCat.setUrladdress("endpoint for Subcategory Three");
		subCatSet.add(subCat);

		/** Must be done to manage relationship */
		cat.setSubCats(subCatSet);
		System.out.println("BEFORE: " + cat);
	}

	@Test
	public void test() {
		repository.save(cat);

		CategoryEntity catent = repository.findOne(cat.getId());
		assertNotNull(catent);
		System.out.println(catent);
		// repository.delete(catent.getId());
		// catent = repository.findOne(catent.getId());
		// assert(null == catent);
	}

}
