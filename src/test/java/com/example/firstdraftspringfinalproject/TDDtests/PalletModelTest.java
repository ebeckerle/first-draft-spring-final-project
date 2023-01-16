package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.Pallet;
import com.example.firstdraftspringfinalproject.models.ProductType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PalletModelTest {

    private ProductType testProductTypeOne = new ProductType("Wooden Window Sash", "double-hung, six over one");
    private ProductType testProductTypeTwo = new ProductType("Steel Window Sash", "casement, 1 lite");
    private Pallet testPallet = new Pallet(testProductTypeOne, 5);


    @Test
    public void testSetAProduct(){
        testPallet.setAProduct(testProductTypeTwo, 3);
        assertEquals(5, testPallet.getProductTypeAndCount().get(testProductTypeOne));
        assertEquals(3, testPallet.getProductTypeAndCount().get(testProductTypeTwo));
    }
//    public void setAProduct(String productType, Integer productCount) {
//        if(!this.productType.contains(productType)){
//            this.productType.add(productType);
//        }
//        if(this.productCount.containsKey(productType)){
//            Integer oldProductCount = this.productCount.get(productType);
//            Integer newProductCount = productCount + oldProductCount;
//            this.productCount.remove(productType);
//            this.productCount.put(productType, newProductCount);
//        }else{
//            this.productCount.put(productType, productCount);
//        }
//    }

    @Test
    public void testCalculateTotalCountForProductType(){
        testPallet.setAProduct(testProductTypeOne, 3);
        int actual = testPallet.calculateTotalCountForProductType(testProductTypeOne);
        assertEquals(8, actual);
    }

//    public int calculateTotalCountForProductType(ProductType productType) {
//        int totalCountForAProductType=0;
//        for (ProductType type:
//                this.productTypeAndCount.keySet()) {
//            if(type.equals(productType)){
//                totalCountForAProductType=this.productTypeAndCount.get(type);
//            }
//        }
//        return totalCountForAProductType;
//    }
}
