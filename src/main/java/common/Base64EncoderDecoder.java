package common;

public class Base64EncoderDecoder {
    public static String decode(String encodedString) {
        return new String(java.util.Base64.getDecoder().decode(encodedString), java.nio.charset.StandardCharsets.UTF_8);
    }

    public static String encode(String string) {
        return java.util.Base64.getEncoder().encodeToString(string.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }
}
