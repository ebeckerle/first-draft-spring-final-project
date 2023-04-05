package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.domainentityclasses.Pallet;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.ProductType;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Project;
import com.example.firstdraftspringfinalproject.models.domainentityclasses.Shipment;
import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

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

    private Project pNam = new Project("NAM", "Nelson Atkins Museum");
    private Shipment testShipmentOne = new Shipment("PhaseII - NAM", pNam, ShipmentType.OUTGOING);
    private ProductType testProductTypeOne = new ProductType("Wooden Window Sash", "double-hung, six over one");
    private ProductType testProductTypeTwo = new ProductType("Steel Window Sash", "casement, 1 lite");
    private Pallet testPalletOne = new Pallet(testProductTypeOne, 5);
    private Pallet testPalletTwo = new Pallet(testProductTypeTwo, 100);


    @Test
    public void testConstructorOne(){
        assertFalse(isNull(testShipmentOne));
        assertNull(testShipmentOne.getIncomingDate());
        assertFalse(isNull(testShipmentOne.getOutgoingDateScheduled()));
        System.out.println(testShipmentOne.getOutgoingDateScheduled().toString());

//    public Shipment(String name, Project project, ShipmentType type) {
//            this.name = name;
//            this.project = project;
//            this.type = type;
//        if(type.equals(ShipmentType.INCOMING)){
//            this.outgoingDateScheduled = null;
//            this.outgoingDateActual = null;
//            this.shipOutSignOff = null;
//        }
//        if(type.equals(ShipmentType.OUTGOING)){
//            this.incomingDate = null;
//            this.inventoriedDate = null;
//            this.inventoriedSignOff = null;
//        }
//        }
    }

    @Test
    public void testAddAPallet(){
        testShipmentOne.addAPallet(testPalletOne);
        assertEquals(testPalletOne, testShipmentOne.getPallets().get(0));
//        public void addAPallet(Pallet pallet) {
//            this.pallets.add(pallet);
//        }

    }

    @Test
    public void testRemoveAPallet(){
        testShipmentOne.addAPallet(testPalletOne);
        testShipmentOne.addAPallet(testPalletTwo);
        assertEquals(2, testShipmentOne.getPalletCount());
        testShipmentOne.removeAPallet(testPalletOne);
        assertEquals(1, testShipmentOne.getPalletCount());

//        public void removeAPallet(Pallet pallet) {
//            this.pallets.remove(pallet);
//        }
    }

    @Test
    public void testCalculatePalletCount(){
        testShipmentOne.addAPallet(testPalletOne);
        testShipmentOne.addAPallet(testPalletTwo);
        testShipmentOne.calculatePalletCount();
        assertEquals(2, testShipmentOne.getPalletCount());
//        public void calculatePalletCount() {
//            int palletCount = 0;
//            palletCount = this.pallets.size();
//            this.palletCount = palletCount;
//        }
    }


    @Test
    public void testCalculateProductTypesAndCounts(){
        testPalletOne.setAProduct(testProductTypeTwo, 33);
        testShipmentOne.addAPallet(testPalletOne);
        testShipmentOne.addAPallet(testPalletTwo);
        testShipmentOne.calculateProductTypesAndCounts();
        assertEquals(133, testShipmentOne.getProductTypeAndCount().get(testProductTypeTwo));
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
