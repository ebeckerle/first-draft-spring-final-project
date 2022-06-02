package com.example.firstdraftspringfinalproject.data;

import com.example.firstdraftspringfinalproject.models.Project;
import com.example.firstdraftspringfinalproject.models.WorkType;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkTypeData {

    private static final ArrayList<WorkType> workTypes = new ArrayList<>(Arrays.asList(
            new WorkType(101, "Inventory"),
            new WorkType(102, "Cut and Process Rough Parts"),
            new WorkType(103, "CNC Machining"),
            new WorkType(104, "Glue up Sash Parts"),
            new WorkType(105, ""),
            new WorkType(201, "LBP Removal: Sand and Scrape"),
            new WorkType(202, "Dutchman & Repairs"),
            new WorkType(203, "Sanding"),
            new WorkType(204, ""),
            new WorkType(205, ""), new WorkType(301, "Finish Sanding"),
            new WorkType(401, "Prep for Paint"),
            new WorkType(402, "Paint - Sash"),
            new WorkType(403, "Paint - Lineal"),
            new WorkType(404, "Stage - Lineal"),
            new WorkType(501, "Glazing"),
            new WorkType(502, "Puddy Glazing"),
            new WorkType(503, "Packaging - Sash"),
            new WorkType(504, "Packaging - Lineal")
    ));

    //This will go away once we learn MySQL (??)
//    public static ArrayList<WorkType> addHardcodedWorkTypes() {
//        WorkType wT101 = new WorkType(101, "Inventory");
//        WorkType wT102 = new WorkType(102, "Cut and Process Rough Parts");
//        WorkType wT103 = new WorkType(103, "CNC Machining");
//        WorkType wT104 = new WorkType(104, "Glue up Sash Parts");
//        WorkType wT105 = new WorkType(105, "");
//        WorkType wT201 = new WorkType(201, "LBP Removal: Sand and Scrape");
//        WorkType wT202 = new WorkType(202, "Dutchman & Repairs");
//        WorkType wT203 = new WorkType(203, "Sanding");
//        WorkType wT204 = new WorkType(204, "");
//        WorkType wT205 = new WorkType(205, "");
//        WorkType wT301 = new WorkType(301, "Finish Sanding");
//        WorkType wT401 = new WorkType(401, "Prep for Paint");
//        WorkType wT402 = new WorkType(402, "Paint - Sash");
//        WorkType wT403 = new WorkType(403, "Paint - Lineal");
//        WorkType wT404 = new WorkType(404, "Stage - Lineal");
//        WorkType wT501 = new WorkType(501, "Glazing");
//        WorkType wT502 = new WorkType(502, "Puddy Glazing");
//        WorkType wT503 = new WorkType(503, "Packaging - Sash");
//        WorkType wT504 = new WorkType(504, "Packaging - Lineal");
//        workTypes.add(wT101);
//        workTypes.add(wT102);
//        workTypes.add(wT103);
//        workTypes.add(wT104);
//        workTypes.add(wT105);
//        workTypes.add(wT201);
//        workTypes.add(wT202);
//        workTypes.add(wT203);
//        workTypes.add(wT204);
//        workTypes.add(wT205);
//        workTypes.add(wT301);
//        workTypes.add(wT401);
//        workTypes.add(wT402);
//        workTypes.add(wT403);
//        workTypes.add(wT404);
//        workTypes.add(wT501);
//        workTypes.add(wT502);
//        workTypes.add(wT503);
//        workTypes.add(wT504);
//        return workTypes;
//    }

    public static WorkType findWorkTypeByCode(String name){
        WorkType workTypeReturned = new WorkType(999, "default description");
        for (WorkType workType:
                workTypes) {
            if (workType.toString().equals(name)){
                workTypeReturned = workType;
            }
        }
        return workTypeReturned;
    }

    public static ArrayList<WorkType> getAllWorkTypes(){
        return workTypes;
    }
}
