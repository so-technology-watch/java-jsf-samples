/*
 * JUnit test case for Car caching service
 * Created on 2017-01-30 ( Date ISO 2017-01-30 - Time 10:02:24 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package org.demo.basic.cache;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.demo.basic.bean.Car ;

import org.junit.Test;

/**
 * JUnit test case for Car caching service
 * 
 * @author Telosys Tools Generator
 *
 */
public class CarCacheTest 
{
	//protected static final java.util.Date now = new java.util.Date();

	private final static void populate(Car car) {
		car.setId( 100 ) ;
		car.setNom( "A" ) ;
		car.setDriver( Integer.valueOf(100) ) ;
	}

	@Test
	public void testPutGetRemove() {
		
		System.out.println("Testing class CarCache ..." );
		
		Car car = new Car();
		populate(car);
		System.out.println("Entity populated : " + car );
		
		CarCache.putCar(car) ;	// Store in cache	
		
		Car car2 = CarCache.getCar( car.getId() );
		assertTrue( car == car2 ) ; // Same instance
		
		CarCache.removeCar(  car.getId() ) ; // Remove from cache	
		
		Car car3 = CarCache.getCar( car.getId() );
		assertTrue( null == car3 ) ; // Not in cache
		assertNull(car3); // Not in cache

	}
}
