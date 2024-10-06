package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import data.access.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;

public class BookRideMockBlackTest {
	static DataAccess sut;
	
	protected MockedStatic<Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	

	@Before
    public  void init() {
        MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);
    }
	@After
    public  void tearDown() {
		persistenceMock.close();
    }
	
	
	Driver driver;
	@Test
	//Ondo erreserbatzen da
	public void test1() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Andoni";
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);

		sut.open();
		Ride r =driver.addRide(rideFrom, rideTo, rideDate, 5,(float) 2.0);
		Boolean emaitza = sut.bookRide(izena, r, 2, 10);  

		 sut.close();
		 assertTrue(emaitza);
		//verify the results
	}
	@Test
	//Izena null da
	public void test2() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena=null;
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new javax.persistence.NoResultException());	

		sut.open();
		Ride r =driver.addRide(rideFrom, rideTo, rideDate, 5,(float) 2.0);
		Boolean emaitza = sut.bookRide(izena, r, 2, 10);  

		 sut.close();
		 assertFalse(emaitza);
		//verify the results
	}
	//Traveler ez dago DB-an
		public void test3() {
			String rideFrom="Donostia";
			String rideTo="Zarautz";
			String izena=null;
			Date rideDate=null;
		    Traveler traveler = new Traveler(izena, "pass");
		    traveler.setMoney(100);
		    driver=new Driver("a","a");
		    List<Traveler> lista = new ArrayList<Traveler>();
		    lista.add(traveler);
		    
		    TypedQuery<Traveler> query = mock(TypedQuery.class);

		    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
	        .thenReturn(query);
		    when(query.setParameter("username", izena)).thenReturn(query);
	        when(query.getSingleResult()).thenThrow(new javax.persistence.NoResultException());	

			sut.open();
			Ride r =driver.addRide(rideFrom, rideTo, rideDate, 5,(float) 2.0);
			Boolean emaitza = sut.bookRide(izena, r, 2, 10);  

			 sut.close();
			 assertFalse(emaitza);
			//verify the results
		}
	@Test
	//Eserleku kopurua negatiboa da
	public void test4() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Andoni";
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);

		sut.open();
		Ride r =driver.addRide(rideFrom, rideTo, rideDate, 5,(float) 2.0);
		Boolean emaitza = sut.bookRide(izena, r, -2, 10);  

		 sut.close();
		 assertFalse(emaitza);
		//verify the results
	}
	@Test
	//Deskontua negatiboa da
	public void test5() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Andoni";
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);

		sut.open();
		Ride r =driver.addRide(rideFrom, rideTo, rideDate, 5,(float) 2.0);
		Boolean emaitza = sut.bookRide(izena, r, 2, -10);  

		 sut.close();
		 assertFalse(emaitza);
		//verify the results
	}
	@Test
	//Ride ez dago DB-an
	public void test6() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Andoni";
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);

		sut.open();
		Ride r =new Ride(rideFrom, rideTo, rideDate, 5,(float) 2.0,driver);
		Boolean emaitza = sut.bookRide(izena, r, 2, -10);  

		 sut.close();
		 assertFalse(emaitza);
		//verify the results
	}
	@Test
	//Ride null da
	public void test7() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Andoni";
		Date rideDate=null;
	    Traveler traveler = new Traveler(izena, "pass");
	    traveler.setMoney(100);
	    driver=new Driver("a","a");
	    List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);

		sut.open();
		Ride r =null;
		Boolean emaitza = sut.bookRide(izena, r, 2, 10);  

		 sut.close();
		 assertFalse(emaitza);
		//verify the results
	}

}
