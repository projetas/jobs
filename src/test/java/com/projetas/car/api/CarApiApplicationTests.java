package com.projetas.car.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CarServiceTest.class,
	CarResourceTest.class,
	CarRestTests.class    
})
 
public class CarApiApplicationTests {
	
    @Test
    public void contextLoads() {
    }
}
