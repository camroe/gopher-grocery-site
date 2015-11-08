package com.gophergroceries.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gophergroceries.model.entities.CategoryEntity;
import com.gophergroceries.model.entities.SubCategoryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class CategoryMappingTest {

	@Resource
	@Qualifier("categoryMapping")
	CategoryMappingService catMap;

	private String categoryUnderTest = "Baking";

	@Test
	public void test() {
		List<CategoryEntity> c = catMap.getCategoryList();
		assertNotNull(c);
		System.out.println(categoryUnderTest);
		for (CategoryEntity cat : c) {
			System.out.println("Major Category: " + cat.getName() + " : " + cat.getUrlAddress());
			for (SubCategoryEntity subCat : cat.getSubCats()) {
				System.out.println("\tSubCategory: " + subCat.getDisplayName() + " : " + subCat.getUrlAddress());
			}
		}
	}

}
