package com.gophergroceries.services;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/root-context.xml")
public class CategoryMappingTest {

	@Resource
	@Qualifier("beanName")
	CategoryMapping catMap;

	private String categoryUnderTest = "Baking";

	@Test
	public void test() {
		List<String> c = catMap.categoryMap.get(categoryUnderTest);
		assertNotNull(c);
		System.out.println(categoryUnderTest);
		for (String str : c) {
			System.out.println("\t: " + str);
		}
	}

}
