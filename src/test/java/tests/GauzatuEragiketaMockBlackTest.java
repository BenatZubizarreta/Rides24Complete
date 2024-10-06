package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import data.access.DataAccess;
import domain.*;

public class GauzatuEragiketaMockBlackTest {

	static DataAccess sut;
	protected MockedStatic<Persistence> persistenceMock;
	@Mock
	protected EntityManagerFactory entityManagerFactory;
	@Mock
	protected EntityManager db;
	@Mock
	protected EntityTransaction et;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() ->
		Persistence.createEntityManagerFactory(Mockito.any())).thenReturn(entityManagerFactory);
		Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
		sut=new DataAccess(db);
	}
	@After
	public void tearDown() {
		persistenceMock.close();
	}
	
	@Test
	public void test1() {
		
		try {
			String username = "TestUser";
			
			Driver d = new Driver(username, "1234");
			d.setMoney(10.0);
			
            TypedQuery<User> query = mock(TypedQuery.class);

            when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class))
                .thenReturn(query);

            when(query.setParameter("username", username)).thenReturn(query);
            when(query.getSingleResult()).thenReturn(d);
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, true);
			sut.close();
			
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test2() {
		
		try {
			String username = "TestUser";
			
			Driver d = new Driver(username, "1234");
			d.setMoney(10.0);
			
            TypedQuery<User> query = mock(TypedQuery.class);

            when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class))
                .thenReturn(query);

            when(query.setParameter("username", username)).thenReturn(query);
            when(query.getSingleResult()).thenReturn(d);
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, false);
			sut.close();
			
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test3() {
		
		try {
			String username = "TestUser";
			
			Driver d = new Driver(username, "1234");
			d.setMoney(10.0);
			
            TypedQuery<User> query = mock(TypedQuery.class);

            when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class))
                .thenReturn(query);

            when(query.setParameter("username", username)).thenReturn(query);
            when(query.getSingleResult()).thenReturn(d);
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 11.0, false);
			sut.close();
			
			assertTrue(emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test4() {
		
		try {
			String username = null;
			
            TypedQuery<User> query = mock(TypedQuery.class);

            when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class))
                .thenReturn(query);

            when(query.setParameter("username", username)).thenReturn(query);
            when(query.getSingleResult()).thenReturn(null);
			
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
		
		try {
			String username = "TestUser1";
			
            when(db.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)).thenReturn(null);
			
			sut.open();
			boolean emaitza = sut.gauzatuEragiketa(username, 1.0, false);
			sut.close();

			assertTrue(!emaitza);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}