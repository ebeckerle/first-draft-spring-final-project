package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Shipment {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "project_project_id")
    private Project project;
    private ShipmentType type;

    //OUTGOING Fields
    private Date outgoingDateScheduled; //should be null if incoming
    private Date outgoingDateActual;
    @ManyToOne
    @JoinColumn(name = "ship_out_sign_off_employee_id")
    private Employee shipOutSignOff;

    //INCOMING Fields
    private Date incomingDate;
    private Date inventoriedDate;
    @ManyToOne
    @JoinColumn(name = "inventoried_sign_off_employee_id")
    private Employee inventoriedSignOff;

    private ArrayList<Pallet> pallets = new ArrayList<Pallet>();
    private Integer palletCount;
    private HashMap<ProductType, Integer> productTypeAndCount;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Contact carrier;

    public Shipment(String name, Project project, ShipmentType type) {
        this.name = name;
        this.project = project;
        this.type = type;
    }

    public Shipment() {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ShipmentType getType() {
        return type;
    }

    public void setType(ShipmentType type) {
        this.type = type;
    }

    public Date getOutgoingDateScheduled() {
        return outgoingDateScheduled;
    }

    public void setOutgoingDateScheduled(Date outgoingDateScheduled) {
        this.outgoingDateScheduled = outgoingDateScheduled;
    }

    public Date getOutgoingDateActual() {
        return outgoingDateActual;
    }

    public void setOutgoingDateActual(Date outgoingDateActual) {
        this.outgoingDateActual = outgoingDateActual;
    }

    public Employee getShipOutSignOff() {
        return shipOutSignOff;
    }

    public void setShipOutSignOff(Employee shipOutSignOff) {
        this.shipOutSignOff = shipOutSignOff;
    }

    public Date getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(Date incomingDate) {
        this.incomingDate = incomingDate;
    }

    public Date getInventoriedDate() {
        return inventoriedDate;
    }

    public void setInventoriedDate(Date inventoriedDate) {
        this.inventoriedDate = inventoriedDate;
    }

    public Employee getInventoriedSignOff() {
        return inventoriedSignOff;
    }

    public void setInventoriedSignOff(Employee inventoriedSignOff) {
        this.inventoriedSignOff = inventoriedSignOff;
    }

    public ArrayList<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(ArrayList<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void addAPallet(Pallet pallet) {
        this.pallets.add(pallet);
    }

    public void removeAPallet(Pallet pallet) {
        this.pallets.remove(pallet);
    }

    public Integer getPalletCount() {
        return palletCount;
    }

    public void calculatePalletCount() {
        int palletCount = 0;
        palletCount = this.pallets.size();
        this.palletCount = palletCount;
    }

    public HashMap<ProductType, Integer> getProductTypeAndCount() {
        return productTypeAndCount;
    }

    public void setProductTypeAndCount(HashMap<ProductType, Integer> productTypeAndCount) {
        this.productTypeAndCount = productTypeAndCount;
    }

    public void calculateProductTypesAndCounts() {
        HashMap<ProductType, Integer> productTypeAndCount = new HashMap<>();
        for (Pallet pallet:
             this.pallets) {
            for (ProductType product :
                 pallet.getProductTypeAndCount().keySet()) {
                if(productTypeAndCount.containsKey(product)){
                    //find the count and add to
                    Integer oldProductCount = productTypeAndCount.get(product);
                    Integer newProductCount = pallet.getProductTypeAndCount().get(product) + oldProductCount;
                    productTypeAndCount.remove(product);
                    productTypeAndCount.put(product, newProductCount);
                }else{
                    productTypeAndCount.put(product, pallet.getProductTypeAndCount().get(product));
                }
            }

        }
        this.productTypeAndCount = productTypeAndCount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Contact getCarrier() {
        return carrier;
    }

    public void setCarrier(Contact carrier) {
        this.carrier = carrier;
    }
}
