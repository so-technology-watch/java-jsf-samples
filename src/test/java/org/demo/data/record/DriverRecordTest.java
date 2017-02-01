/*
 * Created on 2017-01-30 ( Date ISO 2017-01-30 - Time 10:04:00 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package org.demo.data.record;


import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for bean DriverRecord
 * 
 * @author Telosys Tools Generator
 *
 */
public class DriverRecordTest 
{

	@Test
	public void testSettersAndGetters() {
		
		System.out.println("Checking class DriverRecord getters and setters ..." );
		
		DriverRecord driverRecord = new DriverRecord();


		//--- Test setter/getter for attribute "id"  ( model type : int / wrapperType : Integer )
		driverRecord.setId( 100 ) ;
		Assert.assertTrue(  100 == driverRecord.getId()  ) ; // Primitive type in model


		//--- Test setter/getter for attribute "nom"  ( model type : String / wrapperType : String )
		driverRecord.setNom( "A" ) ;
		Assert.assertEquals( "A", driverRecord.getNom() ) ; // Not primitive type in model


	}

}
