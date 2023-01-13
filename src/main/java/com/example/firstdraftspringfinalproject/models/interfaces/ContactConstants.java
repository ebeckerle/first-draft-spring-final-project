package com.example.firstdraftspringfinalproject.models.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface ContactConstants {

    static HashMap<String, String> populateAllStatesHashMap(){
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
        HashMap<String, String> allStatesPostalCodes = new HashMap<>();
        allStatesPostalCodes.put(AL, "Alabama");
        allStatesPostalCodes.put(AK, "Alaska");
        allStatesPostalCodes.put(AZ, "Arizona");
        allStatesPostalCodes.put(AR, "Arkansas");
        allStatesPostalCodes.put(CA, "California");
        allStatesPostalCodes.put(CO, "Colorado");
        allStatesPostalCodes.put(CT, "Connecticut");
        allStatesPostalCodes.put(DE, "Delaware");
        return allStatesPostalCodes;
    }


}