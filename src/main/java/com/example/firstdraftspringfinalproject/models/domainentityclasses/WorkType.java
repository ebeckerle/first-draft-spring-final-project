package com.example.firstdraftspringfinalproject.models.domainentityclasses;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.timesheets.LineEntry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class WorkType {

    @Id
    private Integer workTypeId;
    private String workDescription;

    @OneToMany
    //    @OneToMany(mappedBy = "workType")
    private List<LineEntry> lineEntries;

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

    public List<LineEntry> getLineEntries() {
        return lineEntries;
    }

    public void setLineEntries(List<LineEntry> lineEntries) {
        this.lineEntries = lineEntries;
    }

    @Override
    public String toString() {
        return workTypeId +" - " + workDescription;
    }

    public static Integer fromToStringToId(String workTypeString){
        String workingString =  "" + workTypeString.charAt(0) + "" + workTypeString.charAt(1) + "" + workTypeString.charAt(2);
        return Integer.valueOf(workingString);
    }

    public String toStringWorkTypeCode(){
        return String.valueOf(getWorkTypeId());
    }

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
