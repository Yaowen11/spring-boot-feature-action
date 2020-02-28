package web.boot.action.service;

import web.boot.action.config.Algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author z
 */
public class HashString {

    public String StringHashHex(String origin, Algorithm algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm.toString());
        byte[] hash = messageDigest.digest(origin.getBytes());
        StringBuilder stringHashHex = new StringBuilder();
        int n;
        for (byte b : hash) {
            n = b;
            if (n < 0) {
                n += 256;
            }
            if (n < 16) {
                stringHashHex.append("0");
            }
            stringHashHex.append(Integer.toHexString(n));
        }
        return stringHashHex.toString();
    }
}
