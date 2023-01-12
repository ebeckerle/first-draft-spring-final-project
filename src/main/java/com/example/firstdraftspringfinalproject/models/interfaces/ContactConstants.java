package com.example.firstdraftspringfinalproject.models.interfaces;

import java.util.ArrayList;

public interface ContactConstants {

    static ArrayList<String> populateAllStatesArrayList(){
        String AL = "AL";
        String AK = "AK";
        String AZ = "AZ";
        String AR = "AR";
        String CA = "CA";
        String CO = "CO";
        String CT = "CT";
        String DE = "DE";
        String FL = "FL";
        String GA = "GA";
        String HI = "HI";
        String ID = "ID";
        ArrayList<String> allStatesPostalCodes = new ArrayList<>();
        allStatesPostalCodes.add(AL);
        allStatesPostalCodes.add(AK);
        allStatesPostalCodes.add(AZ);
        allStatesPostalCodes.add(AR);
        return allStatesPostalCodes;
    }


}
