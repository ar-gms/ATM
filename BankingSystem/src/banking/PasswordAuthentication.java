package banking;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordAuthentication {

    // hash password
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Input für hash des gegebenen Kontos
    public static boolean authenticate(String originalPassword, String hashedPassword) {
        String newHashedPassword = hashPassword(originalPassword);
        return newHashedPassword.equals(hashedPassword);
    }
}
