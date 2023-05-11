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
    private Integer id;
    private String workDescription;

    @OneToMany
    //    @OneToMany(mappedBy = "workType")
    private List<LineEntry> lineEntries;

    public WorkType(Integer id, String workDescription){
        this.id = id;
        this.workDescription = workDescription;
    }

    public WorkType () {}

    public Integer getId() {
        return id;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return id +" - " + workDescription;
    }

    public static Integer fromToStringToId(String workTypeString){
        String workingString =  "" + workTypeString.charAt(0) + "" + workTypeString.charAt(1) + "" + workTypeString.charAt(2);
        return Integer.valueOf(workingString);
    }

    public String toStringWorkTypeCode(){
        return String.valueOf(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkType workType = (WorkType) o;
        return Objects.equals(id, workType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
