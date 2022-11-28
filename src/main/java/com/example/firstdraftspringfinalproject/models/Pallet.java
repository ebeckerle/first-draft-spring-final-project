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

    private ArrayList<ProductType> productType = new ArrayList<>();
    private HashMap<ProductType, Integer> productTypeAndCount = new HashMap<>();
    private Integer totalCount = 0;
    private Integer weight = 0;

    public Pallet(ArrayList<ProductType> productType, HashMap<ProductType, Integer> productCount, Integer weight){
        this.productType = productType;
        this.productTypeAndCount = productCount;
        this.weight = weight;
    }

    public Pallet(ProductType productType, Integer productCount){
            this.productType.add(productType);
            this.productTypeAndCount.put(productType, productCount);
    }

    public Pallet() {
    }


    public Integer getId() {
        return id;
    }

    public ArrayList<ProductType> getProductType() {
        return productType;
    }

    public void setAProduct(ProductType productType, Integer productCount) {
        if(!this.productType.contains(productType)){
            this.productType.add(productType);
        }
        if(this.productTypeAndCount.containsKey(productType)){
            Integer oldProductCount = this.productTypeAndCount.get(productType);
            Integer newProductCount = productCount + oldProductCount;
            this.productTypeAndCount.remove(productType);
            this.productTypeAndCount.put(productType, newProductCount);
        }else{
            this.productTypeAndCount.put(productType, productCount);
        }
    }

//    public void setAProductType(ArrayList<String> productType) {
//        this.productType = productType;
//    }

    public HashMap<ProductType, Integer> getProductTypeAndCount() {
        return productTypeAndCount;
    }

    public void setProductCount(HashMap<ProductType, Integer> productCount) {
        this.productTypeAndCount = productCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    //TODO NEXT!!!!
    public int calculateTotalCountForProductType(ProductType productType) {
        int totalCountForAProductType=0;
        for (ProductType type:
             this.productTypeAndCount.keySet()) {
            if(type.equals(productType)){
                totalCountForAProductType=this.productTypeAndCount.get(type);
            }
        }
        return totalCountForAProductType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
