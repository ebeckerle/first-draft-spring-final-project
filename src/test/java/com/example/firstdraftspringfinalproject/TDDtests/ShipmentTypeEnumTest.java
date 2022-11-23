package com.example.firstdraftspringfinalproject.TDDtests;

import com.example.firstdraftspringfinalproject.models.enums.ShipmentType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentTypeEnumTest {


    @Test
    public void testEnum(){
        assertEquals("INCOMING", ShipmentType.INCOMING.getDisplayName());
        assertEquals("OUTGOING", ShipmentType.OUTGOING.getDisplayName());
    }
}
