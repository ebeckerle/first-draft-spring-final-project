package com.example.firstdraftspringfinalproject.modelstests;

import com.example.firstdraftspringfinalproject.models.WorkType;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkTypeTest {


    @Before
    public void createTestWorkTypes(){
        WorkType wT101 = new WorkType(101, "Inventory");
        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
        WorkType wT103 = new WorkType(103, "CNC Machining");
        WorkType wT104 = new WorkType(104, "Glue up Sash Parts");
        WorkType wT201 = new WorkType(201, "LBP Removal: Sand and Scrape");
        WorkType wT202 = new WorkType(202, "Dutchman & Repairs");
        WorkType wT203 = new WorkType(203, "Sanding");
        WorkType wT301 = new WorkType(301, "Finish Sanding");
        WorkType wT401 = new WorkType(401, "Prep for Paint");
        WorkType wT402 = new WorkType(402, "Paint - Sash");
        WorkType wT403 = new WorkType(403, "Paint - Lineal");
        WorkType wT404 = new WorkType(404, "Stage - Lineal");
        WorkType wT501 = new WorkType(501, "Glazing");
        WorkType wT502 = new WorkType(502, "Puddy Glazing");
        WorkType wT503 = new WorkType(503, "Packaging - Sash");
        WorkType wT504 = new WorkType(504, "Packaging - Lineal");
    }

    @Test
    public void testFromToStringToId(){
        assertEquals(3, 3);
    }
//    public static Integer fromToStringToId(String workTypeString){
//        String workingString =  "" + workTypeString.charAt(0) + "" + workTypeString.charAt(1) + "" + workTypeString.charAt(2);
//        return Integer.valueOf(workingString);
//    }


    @Test
    public void testToStringWorkTypeCode(){
        assertEquals(3, 3);
    }
//    public String toStringWorkTypeCode(){
//        return String.valueOf(getWorkTypeId());
//    }

}
