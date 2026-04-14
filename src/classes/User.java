package classes;

/**
 * Clase que representa a un usuario del sistema.
 * Contiene la información personal y de la cuenta del usuario.
 * * @author Francisco Javier Pérez Pastor
 * @version 1.1.0
 */
public class User {
	
	private int id;
	/** Nombre de usuario para el inicio de sesión */
	private String username;
	private String password;
	/** Nombre completo del usuario */
	private String name;
	/** Número de Identificación Fiscal (NIF) del usuario */
	private String nif;
	/** Correo electrónico del usuario */
	private String email;
	/** Dirección física del usuario */
	private String address;
	/** Fecha de nacimiento del usuario */
	private String birthdate;
	/** Rol que desempeña el usuario en el sistema (ej. "user") */
	private String role;
	
	/**
	 * Constructor que inicializa un nuevo usuario con todos sus datos.
	 * @param username Nombre de usuario.
	 * @param name Nombre completo.
	 * @param nif Número de identificación.
	 * @param email Correo electrónico.
	 * @param address Dirección.
	 * @param birthdate Fecha de nacimiento.
	 * @param role Rol del usuario.
	 */
	public User(String username, String password, String name, String nif, String email, String address, String birthdate, String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = "user";
	}
	

	public User(int id, String username, String name, String nif, String email, String address, String birthdate, String role) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = "user";
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de usuario.
	 * @return El nombre de usuario.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Obtiene el nombre completo del usuario.
	 * @return El nombre completo.
	 */
	public String getFullName() {
		return name;
	}

	/**
	 * Obtiene el NIF del usuario.
	 * @return El NIF.
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 * @return El correo electrónico.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Obtiene la dirección del usuario.
	 * @return La dirección.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Obtiene la fecha de nacimiento del usuario.
	 * @return La fecha de nacimiento.
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * Obtiene el rol del usuario en el sistema.
	 * @return El rol del usuario.
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Devuelve una representación en texto de los datos del usuario.
	 * @return Cadena de texto con los atributos del usuario.
	 */
	@Override
	public String toString() {
		return "id = " + id + "\nusername = " + username + "\nname = " + name + "\nnif = " + nif + "\nemail = " + email
				+ "\naddress = " + address + "\nbirthdate = " + Utils.formatDateEU(birthdate) + " " + Utils.getAge(Utils.formatDateEU(birthdate)) + " years\nrole = " + role;
	}
}