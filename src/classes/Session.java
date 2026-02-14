package classes;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Session {
	private User user;
	private boolean logged;
	private final String FILE_PATH = "assets/files/";;
	private final String USERS_FILE = "users.txt";
	
	public Session() {
		this.logged = false;
	}
	
	public boolean isLogged() {
		return this.logged;
	}
	
	public void logout() {
		System.out.println("\nSuccessfully logged out!");
		this.user = null;
		this.logged = false;
	}
	
	public void showUser()  {
		System.out.println("\n===="+ (user.getUsername()).toUpperCase() + " INFORMATION====");
		System.out.println(user.toString());
		System.out.println("=========================");
	}
	
	public boolean addUser(User u, String password, String filePath, String usersFile) {
		
		try {
			FileWriter writer = new FileWriter(filePath+usersFile, true);
			String fileFormatOutput = 
					u.getUsername() + "#" +
					password + "#" +
					u.getFullName() + "#" +
					u.getNif() + "#" +
					u.getEmail() + "#" +
					u.getAddress() + "#" +
					u.getBirthdate() + "#" + 
					u.getRole();
			
			
			writer.write(System.lineSeparator() + fileFormatOutput);
			
			
			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private String[] searchByUsername(String username, String filePath, String usersFile) {
		File f = new File(filePath+usersFile);
		
		try (Scanner reader = new Scanner(f)){
			while (reader.hasNextLine()) {
				String row = reader.nextLine();
				String[] columns = row.split("#");
				if (columns.length != 8) continue;
				
				if (columns[0].equalsIgnoreCase(username)) {
					return columns;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public boolean signUp(){
		String username = Input.getString("\nEnter your username: ");
		if (checkUserExists(username, FILE_PATH, USERS_FILE)) {
			System.out.println("Username already taken!");
			return false;
		}
		
		String password = Input.getString("Enter a password: ");
		String confirmPassword = Input.getString("Confirm your password: ");
		if (!password.equals(confirmPassword)) {
			System.out.println("Passwords don't match!");
			return false;
		}
		
		String fullName = Input.getString("Enter your full name: ");
		String nif = Input.getString("Enter your nif: ");
		String email = Input.getString("Enter your email: ");
		String address = Input.getString("Enter your address: ");
		String birthdate = Input.getString("Enter your birthdate: ");
		
		user = new User(username, fullName, nif, email, address, birthdate, "user");
		addUser(user, password, FILE_PATH, USERS_FILE);
		
		return true;	
	}
	
	public boolean checkUserExists(String username, String filePath, String usersFile) {	

		String[] userData = searchByUsername(username, filePath, usersFile); 
		
		if (userData == null) { 
			return false;
		}
		
		return true;
	}
	

	public void login() {
		String username = Input.getString("\nEnter your username:");
		if(!checkUserExists(username, FILE_PATH, USERS_FILE)) {
			System.out.println("\nUsername does not exist!");
			return;
		}
		String password = Input.getString("Enter your password: ");
		
		if (authentication(username, password)) {
			System.out.println("\nLogin was successful!");
		}
	}
	
	public boolean authentication(String username, String password) {	

			String[] userData = searchByUsername(username, FILE_PATH, USERS_FILE); 
						
			if (userData == null) { 
				System.out.println("\nUsername does not exist!");
				return false;
			}
			
			
			if (!password.equalsIgnoreCase(userData[1])) {
				System.out.println("\nIncorrect password!");
				return false;
			}
			user  = new User(userData[0], userData[2], userData[3], userData[4], userData[5], userData[6], userData[7]);
			this.logged = true;
			return true;
	}
	
	public boolean authentication(String username, String password, String filePath, String usersFile) {	

		String[] userData = searchByUsername(username, filePath, usersFile); 
					
		if (userData == null) { 
			System.out.println("\nUsername does not exist!");
			return false;
		}
		
		
		if (!password.equalsIgnoreCase(userData[1])) {
			System.out.println("\nIncorrect password!");
			return false;
		}
		user  = new User(userData[0], userData[2], userData[3], userData[4], userData[5], userData[6], userData[7]);
		this.logged = true;
		return true;
}
}
