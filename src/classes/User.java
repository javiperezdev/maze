package classes;

public class User {

	private String username;
	private String fullName;
	private String nif;
	private String email;
	private String address;
	private String birthdate;
	private String role;
	
	public User(String username, String fullName, String nif, String email, String address, String birthdate, String role) {
		this.username = username;
		this.fullName = fullName;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = "user";
	}

	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
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
	
	@Override
	public String toString() {
		return "username = " + username + "\nfull name = " + fullName + "\nnif = " + nif + "\nemail = " + email
				+ "\naddress = " + address + "\nbirthdate = " + birthdate + "\nrole = " + role;
	}
	
}
