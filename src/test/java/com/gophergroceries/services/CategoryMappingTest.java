package com.gophergroceries.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.Category;
import com.gophergroceries.model.SubCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class CategoryMappingTest {

	@Resource
	@Qualifier("beanName")
	CategoryMapping	catMap;

	private String	categoryUnderTest	= "Baking";

	@Test
	public void test() {
		List<Category> c = catMap.getCategoryList();
		assertNotNull(c);
		System.out.println(categoryUnderTest);
		for (Category cat : c) {
			System.out.println("Major Category: " + cat.getDisplayName() + " : " + cat.getUrlAddress());
			for (SubCategory subCat : cat.getSubCategories()) {
				System.out.println("\tSubCategory: " + subCat.getDisplayName() + " : " + subCat.getIdName());
			}
		}
	}

}
