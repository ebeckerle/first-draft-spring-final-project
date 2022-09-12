package com.example.firstdraftspringfinalproject.models;

import java.util.Random;
import java.util.SplittableRandom;

public class OtpGenerator {

    private String otp;

    public void setOtp(int length){
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
        this.otp = String.valueOf(password);
    }

    public String getOtp() {
        return otp;
    }
}
