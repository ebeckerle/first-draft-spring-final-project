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
        allStatesPostalCodes.put(FL, "Florida");
        allStatesPostalCodes.put(GA, "Georgia");
        allStatesPostalCodes.put(HI, "Hawaii");
        allStatesPostalCodes.put(ID, "Idaho");
        allStatesPostalCodes.put("IL", "Illinois");
        allStatesPostalCodes.put("IN", "Indiana");
        allStatesPostalCodes.put("IA", "Iowa");
        allStatesPostalCodes.put("KS", "Kansas");
        allStatesPostalCodes.put("KY", "Kentucky");
        allStatesPostalCodes.put("LA", "Louisiana");
        allStatesPostalCodes.put("ME", "Maine");
        allStatesPostalCodes.put("MD", "Maryland");
        allStatesPostalCodes.put("MA", "Massachusetts");
        allStatesPostalCodes.put("MI", "Michigan");
        allStatesPostalCodes.put("MN", "Minnesota");
        allStatesPostalCodes.put("MS", "Mississippi");
        allStatesPostalCodes.put("MO", "Missouri");
        allStatesPostalCodes.put("MT", "Montana");
        allStatesPostalCodes.put("NE", "Nebraska");
        allStatesPostalCodes.put("NV", "Nevada");

        return allStatesPostalCodes;
    }


}
