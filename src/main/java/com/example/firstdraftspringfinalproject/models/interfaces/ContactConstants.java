package com.example.firstdraftspringfinalproject.models.interfaces;

import java.util.ArrayList;

public interface ContactConstants {

    static ArrayList<String> populateAllStatesArrayList(){
        String AL = "AL";
        String AK = "AK";
        String AZ = "AZ";
        String AR = "AR";
        ArrayList<String> allStatesPostalCodes = new ArrayList<>();
        allStatesPostalCodes.add(AL);
        allStatesPostalCodes.add(AK);
        allStatesPostalCodes.add(AZ);
        allStatesPostalCodes.add(AR);
        return allStatesPostalCodes;
    }


}
