package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class Pallet {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    private ArrayList<String> productType;
    private HashMap<String, Integer> productCount;
    private Integer totalCount;
    private Integer weight;

    public Pallet(ArrayList<String> productType, HashMap<String, Integer> productCount, Integer weight){
        this.productType = productType;
        this.productCount = productCount;
        this.weight = weight;
    }

    public Pallet() {
    }


    public Integer getId() {
        return id;
    }

    public ArrayList<String> getProductType() {
        return productType;
    }

    public void setProductType(ArrayList<String> productType) {
        this.productType = productType;
    }

    public HashMap<String, Integer> getProductCount() {
        return productCount;
    }

    public void setProductCount(HashMap<String, Integer> productCount) {
        this.productCount = productCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void calculateTotalCount() {
        for (:
             ) {
            
        }
        this.totalCount = totalCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
