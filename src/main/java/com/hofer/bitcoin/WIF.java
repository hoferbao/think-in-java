package com.hofer.bitcoin;

import org.bouncycastle.crypto.digests.SHA256Digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WIF {
    public static String generatePrivateKeyWIF(String string1 ) throws NoSuchAlgorithmException {
        String string = "80" + string1;
        byte[] data = hexStringToByteArray(string);
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(hexStringToByteArray(string));
        String result = bytesToHex(digest);
        System.out.println(result);
        return result;
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
