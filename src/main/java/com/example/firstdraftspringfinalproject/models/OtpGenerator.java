package com.example.firstdraftspringfinalproject.models;

import java.util.Random;
import java.util.SplittableRandom;

public class OtpGenerator {

    public static void main(String[] args){
        System.out.println(OtpGenerator.generateOtp(7));

        System.out.println(generateOtpAlphaNumeric(5));
    }

    public static String generateOtp (Integer OtpLength){
        SplittableRandom splittableRandom = new SplittableRandom();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < OtpLength ; i++){
            sb.append(splittableRandom.nextInt(0,10));
        }

        return  sb.toString();
    }

    static char[] generateOtpAlphaNumeric(int length){
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_=+-/.?<>)";

        String values = Capital_chars + Small_chars + numbers + symbols;

        Random random = new Random();

        char[] password = new char[length];

        for(int i = 0; i<length; i++){
            password[i] = values.charAt(random.nextInt(values.length()));
        }

        return password;
    }
}
