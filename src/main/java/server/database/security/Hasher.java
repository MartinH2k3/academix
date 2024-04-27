package server.database.security;

import server.logging.Logging;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    /**
     * Hashes the given password using SHA-256 algorithm
     * @param password the password to hash
     * @return the hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Logging.getInstance().logException(e, "Failed to hash the password");
            throw new RuntimeException("Failed to hash the password", e);
        }
    }
}
