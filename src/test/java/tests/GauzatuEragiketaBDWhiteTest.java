package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import data.access.DataAccess;
import testOperations.TestDataAccess;

import domain.*;

public class GauzatuEragiketaBDWhiteTest {


	//sut:system under test
	static DataAccess sut=new DataAccess();
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
		 
	@Test
	public void test1() {
		
		String username = "TestUser";
		
		testDA.open();
		
		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, true);
			sut.close();
			testDA.open();
			assertTrue(emaitza && testDA.getMoney(username)==11.0);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
	
	@Test
	public void test2() {
		String username = "TestUser";
		
		testDA.open();

		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();

			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, false);
			sut.close();
			testDA.open();
			assertTrue(emaitza && testDA.getMoney(username)==9.0);
		} catch(Exception e) {
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
		
	@Test
	public void test3() {
		String username = "TestUser";
		
		testDA.open();

		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();

			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 11.0, false);
			sut.close();
			testDA.open();
			assertTrue(emaitza && testDA.getMoney(username)==0.0);
		} catch(Exception e) {
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
			
	@Test
	public void test4() {
		String username = "TestUser";
		
		try {
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 11.0, false);
			sut.close();
			assertTrue(!emaitza);
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test5() {
		String username = "TestUser";
		try {
			sut.gauzatuEragiketa(username, 11.0, false);
			fail();
		} catch(Exception e) {
			assertTrue(true);
		}
	}

}