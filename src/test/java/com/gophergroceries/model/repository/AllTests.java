package com.gophergroceries.model.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OrdersRepositoryTest.class, ProductRepositoryTest.class, CategoryRepositoryTest.class })
public class AllTests {

}
