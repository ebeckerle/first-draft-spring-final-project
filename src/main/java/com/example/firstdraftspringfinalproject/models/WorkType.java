package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class WorkType {

    @Id
    private Integer workTypeId;
    private String workDescription;

    public WorkType(Integer workTypeId, String workDescription){
        this.workTypeId = workTypeId;
        this.workDescription = workDescription;
    }

    public WorkType () {}

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    @Override
    public String toString() {
        return workTypeId +" - " + workDescription;
    }

    public static Integer fromToStringToId(String workTypeString){
        String workingString = String.valueOf(workTypeString.charAt(0)+workTypeString.charAt(1)+workTypeString.charAt(2));
        return Integer.valueOf(workingString);
    }

    public static String toStringWorkTypes(WorkType aWorkType){
        String workTypeId;
        workTypeId = String.valueOf(aWorkType.getWorkTypeId());
        return  workTypeId + " - " + aWorkType.getWorkDescription();
    }

    public String toStringWorkTypeCode(){
        return String.valueOf(getWorkTypeId());
    }

//    public boolean equalsWorkTypes(WorkType aWorkType){
//        if (aWorkType ==this){
//            return true;
//        }
//        if (aWorkType ==null){
//            return false;
//        }
//        if (aWorkType.getClass() != getClass()){
//            return false;
//        }
//        WorkType theWorkType = (WorkType) aWorkType;
//        return theWorkType.getWorkTypeId() == getWorkTypeId();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkType workType = (WorkType) o;
        return Objects.equals(workTypeId, workType.workTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workTypeId);
    }
}
