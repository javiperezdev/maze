package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {
    private final String SERVER = "localhost";
    private final String PORT = "3306";
    private final String DB = "maze";
    private final String USER = "javi";
    private final String PASS = "1234";
    private final String URL = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DB;

    public boolean checkUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                
                String query = "SELECT user.username FROM user WHERE user.username = '" + user.getUsername() + "' or user.nif = '" + user.getNif() + "' or user.email = '" + user.getEmail() + "';";
                try (ResultSet rs = stmt.executeQuery(query)) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return false;
    }

    public User login(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 Statement stmt = conn.createStatement()) {
                
                String encryptedPass = Utils.encryptMd5(password);
                String query = "SELECT * FROM user WHERE username = '" + username + "';";
                
                try (ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) { 
                        if (encryptedPass.equals(rs.getString("password"))) {
                            return new User(
                                rs.getInt("id"), 
                                username, 
                                rs.getString("name"), 
                                rs.getString("nif"), 
                                rs.getString("email"), 
                                rs.getString("address"), 
                                rs.getString("birthdate"), 
                                rs.getString("role")
                            );
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }

    public boolean signup(User user) {
    	if (checkUser(user)) return false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                Statement stmt = conn.createStatement()) {
                
                String query = "INSERT INTO user (username, password, name, nif, email, address, birthdate, role) VALUES ('" + user.getUsername() + "', '" + Utils.encryptMd5(user.getPassword()) + "', '" 
                             + user.getFullName() + "', '" + user.getNif() + "', '" + user.getEmail() + "', '" 
                             + user.getAddress() + "', '" + Utils.formatDateSQL(user.getBirthdate()) + "', '" + user.getRole() + "')";
                
                return stmt.executeUpdate(query) > 0;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return false;
    }
}