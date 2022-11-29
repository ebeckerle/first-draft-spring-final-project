package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.*;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentModelTest {


    //TODO:
    //Shipment Feature should:
    //allow both a supervisor to use it & an employee

    //supervisor should be able to:
    // - create shipments,
    // -  schedule shipments,
    // - on creation set as incoming or outgoing (?),
    // - on creation set a carrier (?),
    // - on creation set a carrier (?),
    // - mark shipments complete / ready to ship
    //

    //employee should be able to:
    //  - load product to already existing shipments,
    //  - accept shipment deliveries,
    // - inventory incoming shipments


//    // TODO: POSSIBLE FIELDS
//    private String name;
//    //should have an ENUM: ShipmentTypes ; OUT, IN
//    private ShipmentTypes type;
//    private Date outgoingDateScheduled; //should be null if incoming
//    private Date outgoingDateActual;
//    private Employee shipOutSignOff;
//    private Date incomingDate;
//    private Date inventoriedDate;
//    private Employee inventorySignOff;
//    private Project project;
//    private Integer palletCount;
//    private Integer productCount;
//    private String comments;
//    private Contact carrier;
//    private ArrayList<Pallet> pallets = new ArrayList<Pallet>();



    // update Project Class:

    @Test
    public void testConstructorOne(){
        Project pNam = new Project("NAM", "Nelson Atkins Museum");
        Shipment testShipmentOne = new Shipment("PhaseII - NAM", pNam, ShipmentType.OUTGOING);

        assertEquals("102", "102");
//    public Shipment(String name, Project project, ShipmentType type) {
//            this.name = name;
//            this.project = project;
//            this.type = type;
//        }
    }

    @Test
    public void testAddAPallet(){
        assertEquals("102", "102");
//        public void addAPallet(Pallet pallet) {
//            this.pallets.add(pallet);
//        }

    }

    @Test
    public void testRemoveAPallet(){
        assertEquals("102", "102");
//        public void removeAPallet(Pallet pallet) {
//            this.pallets.remove(pallet);
//        }
    }

    @Test
    public void testCalculatePalletCount(){
        assertEquals("102", "102");
//        public void calculatePalletCount() {
//            int palletCount = 0;
//            palletCount = this.pallets.size();
//            this.palletCount = palletCount;
//        }
    }

    //TODO TEST NEXT!!!!

    @Test
    public void testCalculateProductTypesAndCounts(){
        assertEquals("102", "102");
//    public void calculateProductTypesAndCounts() {
//        HashMap<ProductType, Integer> productTypeAndCount = new HashMap<>();
//        for (Pallet pallet:
//                this.pallets) {
//            for (ProductType product :
//                    pallet.getProductTypeAndCount().keySet()) {
//                if(productTypeAndCount.containsKey(product)){
//                    //find the count and add to
//                    Integer oldProductCount = productTypeAndCount.get(product);
//                    Integer newProductCount = pallet.getProductTypeAndCount().get(product) + oldProductCount;
//                    productTypeAndCount.remove(product);
//                    productTypeAndCount.put(product, newProductCount);
//                }else{
//                    productTypeAndCount.put(product, pallet.getProductTypeAndCount().get(product));
//                }
//            }
//
//        }
//        this.productTypeAndCount = productTypeAndCount;
//    }
    }




}
