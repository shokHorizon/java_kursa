package Protocols;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    public static int hashInt(int integer){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(ByteBuffer.allocate(4).putInt(integer).array());
            return ByteBuffer.wrap(messageDigest).getInt();
        } catch (NoSuchAlgorithmException ignore) {};
        return 0;
    }

    public static int hashString(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(str.getBytes());
            return ByteBuffer.wrap(messageDigest).getInt();
        } catch (NoSuchAlgorithmException ignore) {};
        return 0;
    }
}
