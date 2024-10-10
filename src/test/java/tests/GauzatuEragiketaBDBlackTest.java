package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import data.access.DataAccess;
import testOperations.TestDataAccess;

import domain.*;

public class GauzatuEragiketaBDBlackTest {

	//sut:system under test
/*	static DataAccess sut=new DataAccess();
		 
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
			assertTrue(emaitza);
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
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
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
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
	
	@Test
	public void test4() {
		
		String username = null;
		
		try {
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, false);
			sut.close();
			assertTrue(!emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} 
	}
	
	@Test
	public void test5() {
		
		String username = "TestUser1";
		
		try {
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, false);
			sut.close();
			testDA.open();
			assertTrue(!emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test6() {
		
		String username = "TestUser";
		
		testDA.open();
		
		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 0.0, true);
			sut.close();
			testDA.open();
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
	
	@Test
	public void test7() {
		
		String username = "TestUser";
		
		testDA.open();
		
		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, -1.0, true);
			sut.close();
			testDA.open();
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}
	
	@Test
	public void test8() {
		
		String username = "TestUser";
		
		testDA.open();
		
		try {
			testDA.createDriver(username, "1234");
		
			testDA.addMoney(username , 10.0);
			testDA.close();
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, -11.0, true);
			sut.close();
			testDA.open();
			assertTrue(emaitza && testDA.getMoney(username) > 0);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally {
			testDA.removeDriver(username);
			testDA.close();	
		}
	}

	
	*/
	
	
}