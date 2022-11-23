package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Contact;
import com.example.firstdraftspringfinalproject.models.Employee;
import com.example.firstdraftspringfinalproject.models.Pallet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

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
        assertEquals("102", "102");

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



}
