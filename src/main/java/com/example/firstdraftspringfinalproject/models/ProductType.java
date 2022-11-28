package com.example.firstdraftspringfinalproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductType {

    @GeneratedValue
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String description;

    public ProductType() {
    }

    public ProductType(String name, String description){
        this.name = name;
        this.description = description;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
