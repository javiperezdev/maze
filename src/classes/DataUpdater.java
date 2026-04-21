package classes;

public class DataUpdater {
	private User user;
	private StringBuilder sb = new StringBuilder();
	private DAO dao = new DAO();
	private Session session;
	
	public DataUpdater(Session currentSession) {
		this.session = currentSession;
	}
	
	private String getMenu() {
		System.out.println(user);
		sb.append("\n1 - ").append("Update name")
		.append("\n2 - ").append("Update nif")
		.append("\n3 - ").append("Update email")
		.append("\n4 - ").append("Update address")
		.append("\n5 - ").append("Update birthdate")
		.append("\n6 - ").append("Update password")
		.append("\n7 - ").append("Delete user")
		.append("\n0 - ").append("Exit")
		.append("\n\nSelect an option: ");
		return sb.toString();
	}
	
	public void dataUpdaterMenu() {
		this.user = session.getUser();
		int option = Input.getInt(getMenu(), true);
		switch (option) {
		case 1:
			String newName = Input.getString("Enter your new name: ");
			dao.update("name", newName, user.getId());
			System.out.println("Name updated succesfully!");
			LogGenerator.generateLog("Name updated succesfully to " + newName, "user: " + user.getUsername());
			user.setName(newName);
			break;
		case 2:
			String newNif = Input.getString("Enter your new nif: ");
			if (!dao.nifExists(newNif)) {
				dao.update("nif", newNif, user.getId());
				System.out.println("Nif updated succesfully!");
				LogGenerator.generateLog("Nif updated succesfully to " + newNif, "user: " + user.getUsername());
				user.setNif(newNif);
			}
			else {
				System.out.println(newNif + " is already taken!");
			}
			break;
		case 3:
			String newEmail = Input.getString("Enter your new email: ");
			if (!dao.emailExists(newEmail)) {
				dao.update("email", newEmail, user.getId());
				System.out.println("Email updated succesfully!");
				LogGenerator.generateLog("Email updated succesfully to " + newEmail, "user: " + user.getUsername());
				user.setEmail(newEmail);
			}
			else {
				System.out.println(newEmail + " is already taken!");
			}
			break;
		case 4:
			String newAddress = Input.getString("Enter your new address: ");
			dao.update("address", newAddress, user.getId());
			System.out.println("Address updated succesfully!");
			LogGenerator.generateLog("Address updated succesfully to " + newAddress, "user: " + user.getUsername());
			user.setAddress(newAddress);
			break;
		case 5:
			String newBirthdate = Input.getString("Enter your new birthdate: ");
			dao.update("birthdate", newBirthdate, user.getId());
			System.out.println("Birthdate updated succesfully!");
			LogGenerator.generateLog("Birthdate updated succesfully to " + newBirthdate, "user: " + user.getUsername());
			user.setBirthdate(newBirthdate);
			break;
		case 6:
			String newPassword = Input.getString("Enter your new password: ");
			dao.update("password", newPassword, user.getId());
			System.out.println("Password updated succesfully!");
			LogGenerator.generateLog("Password updated succesfully to " + newPassword, "user: " + user.getUsername());
			user.setBirthdate(newPassword);
			break;
		case 7:
			if(dao.login(user.getUsername(), Input.getString("Enter your password to delete your account: ")) != null) {
				dao.delete(user.getId());
				System.out.println("Account deleted succesfully!");
				LogGenerator.generateLog("Account deleted successfully", "user: " + user.getUsername());
				session.logout();
			}
			else {
				System.out.println("Incorrect password!");
			}
			break;
		case 0: 
			return;
		default:
			System.out.println("The selected option is not valid!");
		}
	}
}
