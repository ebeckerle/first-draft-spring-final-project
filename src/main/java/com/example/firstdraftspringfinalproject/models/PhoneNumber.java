package com.example.firstdraftspringfinalproject.models;

public class PhoneNumber {

    private String countryCode;
    private String areaCode;
    private String exchangeCode;
    private String lineNumber;
    private String extension;

    public PhoneNumber(String phoneNumber10digit){
        this.countryCode = "+1";
        this.areaCode = phoneNumber10digit.substring(0,3);
        this.exchangeCode = phoneNumber10digit.substring(3,6);
        this.lineNumber = phoneNumber10digit.substring(6,10);

    }



    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}