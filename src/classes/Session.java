package classes;


public class Session {
	private User user;
	private boolean logged;
	private DAO dao = new DAO();


	public Session() {
		this.logged = false;
	}
	
	public boolean isLogged() {
		return this.logged;
	}
	
	public void logout() {
		System.out.println("\nSuccessfully logged out!");
		LogGenerator.generateLog("Succesful log out", "username: " + user.getUsername());
		this.user = null;
		this.logged = false;
	}
	
	public void showUser()  {
		System.out.println("\n===="+ (user.getUsername()).toUpperCase() + " INFORMATION====");
		System.out.println(user.toString());
		System.out.println("=========================");
		LogGenerator.generateLog("Check personal information", "username: " + user.getUsername() + " id: " + user.getId());

	}
	
	public boolean signUp(){ // Need to add fail fast for the username
		String username = Input.getString("\nEnter your username: ");
		if (!Utils.validateUsername(username)) {
			System.out.println("ERROR: username is not valid!");
			LogGenerator.generateLog("Failed sign up", "details: introduced username is not valid");

			return false;
		}
		
		String password = Input.getString("Enter a password: ");
		if (!Utils.validatePassword(password)) {
			System.out.println("ERROR: Your password must be at least 8 characters long and include: an uppercase letter, a lowercase letter, a number, and a special character (no spaces allowed)!");
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid password");
			return false;
		}
		
		String confirmPassword = Input.getString("Confirm your password: ");
		if (!password.equals(confirmPassword)) {
			System.out.println("Passwords don't match!");
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid password");
			return false;
		}
		
		String fullName = Input.getString("Enter your full name: ");
		if (!Utils.validateName(fullName)) {
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid name");
			System.out.println("ERROR: Name is not valid!");
			return false;
		}
		
		String nif = Input.getString("Enter your nif: ");
		if (!Utils.validateNif(nif)) {
			System.out.println("ERROR: NIF format is not valid!");
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid nif");
			return false;
		}
		
		String email = Input.getString("Enter your email: ");
		if (!Utils.validateEmail(email)) {
			System.out.println("ERROR: email format is not valid!");
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid email");
			return false;
		}
		
		String address = Input.getString("Enter your address: ");

		
		String birthdate = Input.getString("Enter your birthdate: ");
		if (!Utils.validateDate(birthdate)) {
			System.out.println("ERROR: date format is not valid!");
			LogGenerator.generateLog("Failed to sign up", "details: introduced invalid birthdate");
			return false;
		}
		
		this.user = new User(username, password, fullName, nif, email, address, birthdate, "user");
		
		if (dao.checkUser(user)) {
	        System.out.println("ERROR: Username, Email or NIF are already in the system!");
	        return false;
	    }
		
		System.out.println("Registration successful!");
		LogGenerator.generateLog("Succesful registration", "username: " + username);
		return dao.signup(user);	
	}

	public void login() {
		String username = Input.getString("\nEnter your username: ");
		String password = Input.getString("Enter your password: ");
		
		this.user = dao.login(username, password);
		if (user != null) {
			System.out.println("\nLogin was successful!");
			LogGenerator.generateLog("Succesful log in", "username: " + username);
			logged = true;
			return;
		}

		System.out.println("Incorrect username or password!");
		LogGenerator.generateLog("Failed log in", "username: " + username);
	}
	
	public User getUser() {
		return this.user;
	}
}