package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import data.access.DataAccess;
import domain.Driver;
import domain.Ride;
import testOperations.TestDataAccess;

public class BookRideBDBlackTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 
	@Test
	//Ondo doa
	public void test1() {
		String izena = "Andoni";
		String izena2 = "a";
		String pas="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;

		testDA.open();
		testDA.createTraveler(izena, pas);

		testDA.createDriver(izena2, pas);
		Ride r =testDA.addRide(izena2, rideFrom,rideTo,  rideDate, 4, 0);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, 10.0);
		 sut.close();
		 assertTrue(emaitza);
		 testDA.open();
		 testDA.removeDriver(izena2);
		 testDA.removeTraveler(izena);
         testDA.close();

	}
	@Test
	//izena null da
	public void test2() {
		String izena = null;
		String izena2="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String pas="a";

		Date rideDate=null;;
		testDA.open();
		testDA.createDriver(izena2, pas);
		Ride ride = testDA.addRide( izena2,  rideFrom,  rideTo,   rideDate,2,10);
		testDA.close();
	    sut.open();
	    boolean emaitza = sut.bookRide(izena, ride, 2, 10.0);
	    sut.close();
	    assertFalse(emaitza);
	    testDA.open();
		testDA.removeDriver(izena2);
        testDA.close();
		
	}
	@Test
	//Treveler ez dago datu basean
	public void test3() {
		
		String izena = "Andoni";
		String izena2="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String pas="a";

		Date rideDate=null;;
		testDA.open();
		testDA.createDriver(izena2, pas);
		Ride ride = testDA.addRide( izena2,  rideFrom,  rideTo,   rideDate,2,10);
		testDA.close();
	    sut.open();
	    boolean emaitza = sut.bookRide(izena, ride, 2, 10.0);
	    sut.close();
	    testDA.open();
		testDA.removeDriver(izena2);
        testDA.close();
	    assertFalse(emaitza);
	    
	}
	@Test
	//Eserleku kopurua negatiboa da
	public void test4() {
		String izena = "Andoni";
		String izena2 = "a";
		String pas="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;
		testDA.open();
		testDA.createTraveler(izena, pas);
		testDA.setMoney(izena, (float)1000.0);

		testDA.createDriver(izena2, pas);
		Ride r =testDA.addRide(izena2, rideFrom,rideTo,  rideDate, 4, 0);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, -2, 10.0);
		 sut.close();
		 testDA.open();
		 testDA.removeDriver(izena2);
		 testDA.removeTraveler(izena);
         testDA.close();
		 assertFalse(emaitza);
		 
	}
	@Test
	//Deskontua negatiboa da
	public void test5() {
		String izena = "Andoni";
		String izena2 = "a";
		String pas="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;

		testDA.open();
		testDA.createTraveler(izena, pas);
		testDA.setMoney(izena, (float)1000.0);

		testDA.createDriver(izena2, pas);
		Ride r =testDA.addRide(izena2, rideFrom,rideTo,  rideDate, 4, 0);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, -10.0);
		 sut.close(); 
		 testDA.open();
		 testDA.removeDriver(izena2);
		 testDA.removeTraveler(izena);
         testDA.close();
		 assertFalse(emaitza);
		

	}
	@Test
	//Ride ez dago DB-an
	public void test6() {
		String izena = "Andoni";
		String izena2 = "a";
		String pas="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;;
try {
		testDA.open();
		testDA.createTraveler(izena, pas);
		testDA.setMoney(izena, (float)1000.0);
		Driver d=testDA.createDriver(izena2, pas);
		Ride r =new Ride(rideFrom,rideTo,  rideDate, 4, 0, d);
		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, -1.0);
		 sut.close();
		 fail();		 
		}catch(Exception e) {
			assertTrue(true);
		}finally {
			testDA.open();
			testDA.removeDriver(izena2);
			testDA.removeTraveler(izena);
			testDA.close();
		}

	}

	@Test
	//Ride null da
	public void test7() {
		String izena = "Andoni";
		String pas="a";
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Date rideDate=null;
		Driver d= new Driver("a", pas);
		Ride r=null;
		testDA.open();
		testDA.createTraveler(izena, pas);
		testDA.setMoney(izena, (float)1000.0);

		testDA.close();
		 sut.open();
		 boolean emaitza = sut.bookRide(izena, r, 2, 10.0);
		 sut.close();
		 assertFalse(emaitza);
		 testDA.open();
		 testDA.removeTraveler(izena);
         testDA.close();
	}
	
}
