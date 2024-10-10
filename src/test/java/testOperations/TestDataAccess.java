package testOperations;

//sgd
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Driver;
import domain.Ride;
import domain.Traveler;


public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("TestDataAccess created");

		//open();
		
	}

	
	public void open(){
		

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		System.out.println("TestDataAccess opened");

		
	}
	public void close(){
		db.close();
		System.out.println("TestDataAccess closed");
	}

	public boolean removeDriver(String name) {
		System.out.println(">> TestDataAccess: removeDriver");
		Driver d = db.find(Driver.class, name);
		if (d!=null) {
			db.getTransaction().begin();
			db.remove(d);
			db.getTransaction().commit();
			return true;
		} else 
		return false; 
    }
	public boolean removeTraveler(String name) {
		System.out.println(">> TestDataAccess: removeTraveler");
		Traveler t = db.find(Traveler.class, name);
		if (t!=null) {
			db.getTransaction().begin();
			db.remove(t);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	public Driver createDriver(String name, String pass) {
		System.out.println(">> TestDataAccess: addDriver");
		Driver driver=null;
			db.getTransaction().begin();
			try {
			    driver=new Driver(name,pass);
				db.persist(driver);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return driver; 
    }
	public Traveler createTraveler(String name, String pass) {
		System.out.println(">> TestDataAccess: addTraveler");
		Traveler traveler=null;
			db.getTransaction().begin();
			try {
			    traveler=new Traveler(name,pass);
				db.persist(traveler);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return traveler; 
    }
	public boolean existDriver(String email) {
		 return  db.find(Driver.class, email)!=null;
		 

	}
		
		public Driver addDriverWithRide(String name, String from, String to,  Date date, int nPlaces, float price) {
			System.out.println(">> TestDataAccess: addDriverWithRide");
				Driver driver=null;
				db.getTransaction().begin();
				try {
					 driver = db.find(Driver.class, name);
					if (driver==null) {
						System.out.println("Entra en null");
						driver=new Driver(name,null);
				    	db.persist(driver);
					}
				    driver.addRide(from, to, date, nPlaces, price);
					db.getTransaction().commit();
					System.out.println("Driver created "+driver);
					
					return driver;
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return null;
	    }
		
		
		public boolean existRide(String name, String from, String to, Date date) {
			System.out.println(">> TestDataAccess: existRide");
			Driver d = db.find(Driver.class, name);
			if (d!=null) {
				return d.doesRideExists(from, to, date);
			} else 
			return false;
		}
		public Ride removeRide(String name, String from, String to, Date date ) {
			System.out.println(">> TestDataAccess: removeRide");
			Driver d = db.find(Driver.class, name);
			if (d!=null) {
				db.getTransaction().begin();
				Ride r= d.removeRide(from, to, date);
				db.getTransaction().commit();
				System.out.println("created rides" +d.getCreatedRides());
				return r;

			} else 
			return null;

		}
		
		public Ride addRide(String name, String from, String to,  Date date, int nPlaces, float price) {
			db.getTransaction().begin();
			Driver d = db.find(Driver.class, name);
			Ride ride= null;
			if (d!=null) {
				ride=d.addRide(from, to, date, nPlaces, price);
				db.getTransaction().commit();
			}
			return ride;
		}
		
		public boolean setMoney(String name, Float d) {
			db.getTransaction().begin();
			Traveler t = db.find(Traveler.class, name);
			if (t!=null) {
			t.setMoney(d);
			db.getTransaction().commit();
			return true;
			}else return false;
		}
		public void addMoney(String user, double amount) {
			System.out.println(">> TestDataAccess: addMoney");
			Driver d = db.find(Driver.class, user);
			if(d != null) {
				db.getTransaction().begin();
				d.setMoney(d.getMoney()+amount);
				db.getTransaction().commit();
				System.out.println("User: " + user + " added money: " + amount);
			}
		}
		
		public double getMoney(String user) {
			System.out.println(">> TestDataAccess: getMoney");
			Driver d = db.find(Driver.class, user);
			return d.getMoney();
		}



		
}