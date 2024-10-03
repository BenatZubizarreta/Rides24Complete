package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import data.access.DataAccess;
import domain.Ride;
import domain.Traveler;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import testOperations.TestDataAccess;
import domain.Driver;
import org.junit.Test;

public class BookRideBDWhiteTest {

	
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 
	
	
	@Test
	//Treveler ez dago datu basean
	public void test1() {
		
		String username = "Existitzen ez den izena";

		String rideFrom="Donostia";
		String rideTo="Zarautz";
		
		Date rideDate=null;;
		testDA.open();
		Ride ride = testDA.addRide( username,  rideFrom,  rideTo,   rideDate,2,10);
		testDA.close();
	    sut.open();
	    boolean emaitza = sut.bookRide(username, ride, 2, 10.0);
	    sut.close();
	    assertFalse(emaitza);
	}
	@Test
	//Ez dago nahikoa eserleku
	public void test2() {
		String izena = "Ander";
		String pas="pas";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;

		testDA.open();
		testDA.createDriver(izena, pas);
		Ride r =testDA.addRide(izena, rideFrom,rideTo,  rideDate, 1, 0);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, 10.0);
		 sut.close();
		 assertFalse(emaitza);
		 testDA.open();
		 testDA.removeDriver(izena);
         testDA.close();
       

	}
	
	@Test
	//Ez dago diru nahikoa
	public void test3() {
		String izena = "Ander";
		String pas="pas";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;

		testDA.open();
		testDA.createDriver(izena, pas);
		Ride r =testDA.addRide(izena, rideFrom,rideTo,  rideDate, 4, 100);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, 10.0);
		 sut.close();
		 assertFalse(emaitza);
		 testDA.open();
		 testDA.removeDriver(izena);
         testDA.close();
       

	}
	
	@Test
	//Ondo doa
	public void test4() {
		String izena = "Ander";
		String pas="pas";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;

		testDA.open();
		testDA.createDriver(izena, pas);
		Ride r =testDA.addRide(izena, rideFrom,rideTo,  rideDate, 4, 0);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, 10.0);
		 sut.close();
		 assertFalse(emaitza);
		 testDA.open();
		 testDA.removeDriver(izena);
         testDA.close();

	}
	@Test
	//Catch-era joan
	public void test5() {
		try {
			String izena = "Ander";
			String pas="pas";
			String rideFrom="Donostia";
			String rideTo="Zarautz";
			Date rideDate=null;
			testDA.open();
			testDA.createDriver(izena, pas);
			Ride r =testDA.addRide(izena, rideFrom,rideTo,  rideDate, 4, 0);
			testDA.close();
			sut.close();
			sut.bookRide(izena, r, 2, 10.0);
			fail();
		}catch(Exception e){
			assertTrue(true);
		}
	}


}
