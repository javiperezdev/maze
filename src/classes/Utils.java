package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

public class Utils {
    public static boolean validateUsername(String username) {
        if (username == null) return false;
        String regex = "^[a-zA-Z0-9_]{3,15}$";
        return Pattern.matches(regex, username);
    }

    public static boolean validatePassword(String password) {
        if (password == null) return false;
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return Pattern.matches(regex, password);
    }

    public static boolean validateName(String name) {
        if (name == null) return false;
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        return Pattern.matches(regex, name.trim());
    }

    public static boolean validateEmail(String email) {
        if (email == null) return false;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
        return Pattern.matches(regex, email);
    }

    public static boolean validateNif(String nif) {
        if (nif == null) return false;
        nif = nif.toUpperCase();

        if (!nif.matches("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")) {
            return false;
        }

        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int number = Integer.parseInt(nif.substring(0, 8));
        char letter = nif.charAt(8);

        return letters.charAt(number % 23) == letter;
    }

    public static boolean validateDate(String dateStr) {
        if (dateStr == null) return false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        try {
            LocalDate.parse(dateStr, dtf);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
	public static String encryptMd5(String input) {
		try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: " + e);
        }
	}

	public static String formatDateEU(String sqlDate) {
		if (sqlDate == null || sqlDate.isEmpty()) {
	        return "";
	    }
		
		LocalDate date = LocalDate.parse(sqlDate);
	    DateTimeFormatter europeanFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return date.format(europeanFormatter);
	}

	
	public static String formatDateSQL(String dateEu) {
        if (dateEu == null || dateEu.isEmpty()) return null;
        
        LocalDate date = LocalDate.parse(dateEu, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static int getAge(String birthDateStr) {
        if (birthDateStr == null) return -1;

        LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate currentDate = LocalDate.now();
        
        return Period.between(birthDate, currentDate).getYears();
    }
	
}
