package classes;
public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String nif;
	private String email;
	private String address;
	private String birthdate;
	private String role;
	
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

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getNif() {
		return nif;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNif(String nif) {
		this.nif = nif;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "id = " + id + "\nusername = " + username + "\nname = " + name + "\nnif = " + nif + "\nemail = " + email
				+ "\naddress = " + address + "\nbirthdate = " + Utils.formatDateEU(birthdate) + " " + Utils.getAge(Utils.formatDateEU(birthdate)) + " years\nrole = " + role;
	}
}