package classes;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class SessionTest {
	Session session = new Session();
	
	private static String filePath = "assets/files/";
	private static String usersFile = "users-test.txt";
	
	@BeforeAll
	static void setup() {
		try {
			File f = new File(filePath + usersFile);
			if (f.exists()) f.delete();
				f.createNewFile();
			}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Unit tests for the `addUser` method, which is the logic for the sign up.
	 */
	
	@Test 
	void addUser1() {
		User user1 = new User(
			    "mgarcia",
			    "María García López",
			    "12345678Z",
			    "mgarcia@correo.com",
			    "Calle Mayor 15, 2ºA, Madrid",
			    "1990-05-15",
			    "user"
			);
		
		String password = "123456";
		session.addUser(user1, password, filePath, usersFile);
		
		assertTrue(session.checkUserExists(user1.getUsername(), filePath, usersFile));
	}
	
	@Test 
	void addUser2() {
		User user2 = new User(
				"lfernandez",
		        "Lucía Fernández Ruiz",
		        "11223344B",
		        "lucia.fer@servidor.org",
		        "Plaza de España 3, Sevilla",
		        "1995-02-10",
		        "user"
			);
		
		String password = "987654";
		session.addUser(user2, password, filePath, usersFile);
		
		assertTrue(session.checkUserExists(user2.getUsername(), filePath, usersFile));
	}
	
	@Test 
	void addUser3() {
	    User user3 = new User(
	        "jrodriguez",
	        "Javier Rodríguez Sola",
	        "87654321X",
	        "javi.rod@email.net",
	        "Av. Diagonal 402, 1ºB, Barcelona",
	        "1985-11-23",
	        "user"
	    );
	
		
		String password = "654321";
		session.addUser(user3, password, filePath, usersFile);
		
		assertTrue(session.checkUserExists(user3.getUsername(), filePath, usersFile));
	}

	/*
	 * Unit tests for the `authentication` method, which is the logic behind the login.
	 */
	
	@Test 
	void login1() {
		assertTrue(session.authentication("mgarcia", "123456", filePath, usersFile));
	}
	
	@Test 
	void login2() {
		assertFalse(session.authentication("lfernandez", "fake", filePath, usersFile));
	}
	
	@Test 
	void login3() {
		assertTrue(session.authentication("jrodriguez", "654321", filePath, usersFile));
	}
	
	@AfterAll
	static void teardown() {
			File f = new File(filePath + usersFile);
			if (f.exists()) f.delete();
	}
}
