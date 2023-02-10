package com.example.firstdraftspringfinalproject.models;

import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
public class Shipment {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Shipment must have a name.")
    private String name;
    @NotNull(message = "Shipment must be connected to a Project.")
    @ManyToOne
    @JoinColumn(name = "project_project_id")
    private Project project;
    @NotNull(message = "Shipment must be Incoming or Outgoing.")
    private ShipmentType type;

    //OUTGOING Fields
    @OneToOne
//    @OneToOne(cascade = CascadeType.ALL)
//    @Valid
    private Event outgoingDateScheduled; //should be null if incoming
    @OneToOne
    @JoinColumn(name = "outgoing_date_actual_id")
    private Event outgoingDateActual;
    @ManyToOne
    @JoinColumn(name = "ship_out_sign_off_employee_id")
    private Employee shipOutSignOff;

    //INCOMING Fields
    @OneToOne(cascade = CascadeType.ALL)
    private Event incomingDate;
    @OneToOne
    private Event inventoriedDate;
    @ManyToOne
    @JoinColumn(name = "inventoried_sign_off_employee_id")
    private Employee inventoriedSignOff;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pallet> pallets = new ArrayList<Pallet>();
    private Integer palletCount = 0;
    private HashMap<ProductType, Integer> productTypeAndCount;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Contact carrier;

    public Shipment(String name, Project project, ShipmentType type) {
        this.name = name;
        this.project = project;
        this.type = type;
        if(type.equals(ShipmentType.INCOMING)){
            this.incomingDate = new Event();
            this.outgoingDateScheduled = null;
            this.outgoingDateActual = null;
            this.shipOutSignOff = null;
        }
        if(type.equals(ShipmentType.OUTGOING)){
            this.outgoingDateScheduled = new Event();
            this.incomingDate = null;
            this.inventoriedDate = null;
            this.inventoriedSignOff = null;
        }
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

    public Event getOutgoingDateScheduled() {
        return outgoingDateScheduled;
    }

    public void setOutgoingDateScheduled(Event outgoingDateScheduled) {
        this.outgoingDateScheduled = outgoingDateScheduled;
    }

    public Event getOutgoingDateActual() {
        return outgoingDateActual;
    }

    public void setOutgoingDateActual(Event outgoingDateActual) {
        this.outgoingDateActual = outgoingDateActual;
    }

    public Employee getShipOutSignOff() {
        return shipOutSignOff;
    }

    public void setShipOutSignOff(Employee shipOutSignOff) {
        this.shipOutSignOff = shipOutSignOff;
    }

    public Event getIncomingDate() {
        return incomingDate;
    }

    public void setIncomingDate(Event incomingDate) {
        this.incomingDate = incomingDate;
    }

    public Event getInventoriedDate() {
        return inventoriedDate;
    }

    public void setInventoriedDate(Event inventoriedDate) {
        this.inventoriedDate = inventoriedDate;
    }

    public Employee getInventoriedSignOff() {
        return inventoriedSignOff;
    }

    public void setInventoriedSignOff(Employee inventoriedSignOff) {
        this.inventoriedSignOff = inventoriedSignOff;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(ArrayList<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void addAPallet(Pallet pallet) {
        this.pallets.add(pallet);
        this.palletCount += 1;
    }

    public void removeAPallet(Pallet pallet) {
        if(this.pallets.contains(pallet)){
            this.pallets.remove(pallet);
            this.palletCount += -1;
        }
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
