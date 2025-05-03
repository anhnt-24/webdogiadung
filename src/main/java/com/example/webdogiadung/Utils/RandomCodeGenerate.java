package com.example.webdogiadung.Utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomCodeGenerate {

    private static final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateOtp() {
        String otp = String.format("%06d", random.nextInt(1000000));
        return otp;
    }


    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
    public static String generateOrderCode() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String randomPart = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5).toUpperCase();

        return "ORD" + date + "-" + randomPart;
    }
}
