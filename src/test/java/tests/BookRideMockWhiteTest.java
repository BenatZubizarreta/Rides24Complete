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

public class BookRideMockWhiteTest {
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
	//Erabiltzailea ez da existitzen
	public void test1() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		Ride r=null;
		String izena="Ez erregistratua";
		Date rideDate=null;
		Traveler t=new Traveler(izena,"1234");
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new javax.persistence.NoResultException());			
        sut.open();
		Boolean emaitza=sut.bookRide(izena,r,2,10);
		sut.close();
		//verify the results
		assertFalse(emaitza);
	}
	
	@Test
	//Ez dago nahikoa eserleku
	public void test2() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Ez erregistratua";
		Date rideDate=null;
		Traveler traveler = new Traveler(izena, "1234");
		traveler.setMoney(50.0); 
		Ride r = new Ride(rideFrom, rideTo, rideDate, 1, 20.0, driver);
		List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);
        sut.open();		
        sut.open();
        
		Boolean emaitza=sut.bookRide(izena,r,2,10);
		sut.close();
		//verify the results
		assertFalse(emaitza);
	}
	

	@Test
	//Ez dago nahikoa diru
	public void test3() {
		String rideFrom="Donostia";
		String rideTo="Zarautz";
		String izena="Ez erregistratua";
		Date rideDate=null;
		Traveler traveler = new Traveler(izena, "1234");
		traveler.setMoney(0.0); 
		Ride r = new Ride(rideFrom, rideTo, rideDate, 5, 100.0, driver);
		List<Traveler> lista = new ArrayList<Traveler>();
	    lista.add(traveler);
	    
	    TypedQuery<Traveler> query = mock(TypedQuery.class);

	    when(db.createQuery("SELECT t FROM Traveler t WHERE t.username = :username", Traveler.class))
        .thenReturn(query);
	    when(query.setParameter("username", izena)).thenReturn(query);
    	when(query.getResultList()).thenReturn(lista);
        sut.open();
        
		Boolean emaitza=sut.bookRide(izena,r,2,10);
		sut.close();
		//verify the results
		assertFalse(emaitza);
	}
	
	@Test
	//Ondo erreserbatzen da
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
		Boolean emaitza = sut.bookRide(izena, r, 2, 10);  

		 sut.close();
		 assertTrue(emaitza);
		//verify the results
	}

}
