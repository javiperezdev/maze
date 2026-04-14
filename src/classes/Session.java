package classes;

/**
 * Clase encargada de gestionar la sesión activa, registro y 
 * validación de los usuarios a través de archivos de texto.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class Session {
	/** Usuario que actualmente tiene la sesión iniciada */
	private User user;
	/** Indica si existe un usuario logueado en el sistema */
	private boolean logged;
	private DAO dao = new DAO();

	/**
	 * Constructor por defecto que inicializa la sesión como no logueada.
	 */
	public Session() {
		this.logged = false;
	}
	
	/**
	 * Verifica si hay una sesión activa.
	 * @return true si hay un usuario logueado, false si no.
	 */
	public boolean isLogged() {
		return this.logged;
	}
	
	/**
	 * Cierra la sesión activa del usuario actual.
	 */
	public void logout() {
		System.out.println("\nSuccessfully logged out!");
		this.user = null;
		this.logged = false;
	}
	
	/**
	 * Muestra por consola la información del usuario con sesión activa.
	 */
	public void showUser()  {
		System.out.println("\n===="+ (user.getUsername()).toUpperCase() + " INFORMATION====");
		System.out.println(user.toString());
		System.out.println("=========================");
	}
	

	/**
	 * Gestiona el proceso de registro solicitando datos por pantalla.
	 * @return true si el registro se completó con éxito, false en caso contrario.
	 */
	public boolean signUp(){
		String username = Input.getString("\nEnter your username: ");
		if (!Utils.validateUsername(username)) {
			System.out.println("ERROR: username is not valid!");
			return false;
		}
		
		String password = Input.getString("Enter a password: ");
		if (!Utils.validatePassword(password)) {
			System.out.println("ERROR: Your password must be at least 8 characters long and include: an uppercase letter, a lowercase letter, a number, and a special character (no spaces allowed)!");
			return false;
		}
		
		String confirmPassword = Input.getString("Confirm your password: ");
		if (!password.equals(confirmPassword)) {
			System.out.println("Passwords don't match!");
			return false;
		}
		
		String fullName = Input.getString("Enter your full name: ");
		if (!Utils.validateName(fullName)) {
			System.out.println("ERROR: Name is not valid!");
			return false;
		}
		
		String nif = Input.getString("Enter your nif: ");
		if (!Utils.validateNif(nif)) {
			System.out.println("ERROR: NIF format is not valid!");
			return false;
		}
		
		String email = Input.getString("Enter your email: ");
		if (!Utils.validateEmail(email)) {
			System.out.println("ERROR: email format is not valid!");
			return false;
		}
		
		String address = Input.getString("Enter your address: ");

		
		String birthdate = Input.getString("Enter your birthdate: ");
		if (!Utils.validateDate(birthdate)) {
			System.out.println("ERROR: date format is not valid!");
			return false;
		}
		
		user = new User(username, password, fullName, nif, email, address, birthdate, "user");
		
		if (dao.checkUser(user)) {
	        System.out.println("ERROR: Username, Email or NIF are already in the system!");
	        return false;
	    }
		
		System.out.println("Registration successful!");
		return dao.signup(user);	
	}
	
	
	/**
	 * Inicia el proceso de login pidiendo datos por teclado.
	 */
	public void login() {
		String username = Input.getString("\nEnter your username:");
		String password = Input.getString("Enter your password: ");
		
		user = dao.login(username, password);
		if (user != null) {
			System.out.println("\nLogin was successful!");
			logged = true;
		}
		
		else {
			System.out.println("Incorrect username or password!");
		}
	}
}