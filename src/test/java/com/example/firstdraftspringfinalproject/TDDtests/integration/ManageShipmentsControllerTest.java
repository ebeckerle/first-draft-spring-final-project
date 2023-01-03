package com.example.firstdraftspringfinalproject.TDDtests.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManageShipmentsControllerTest {


    //TODO - what to test? - That the processAddIncomingShipment creates an Incoming Shipment,
    // that the processAddOutgoingShipment creates an Outgoing shipment,


    //test displayAddAnIncomingShipment creates a new Shipment, but can it be a new shipment that
    //is already an incoming Shipment?

    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testDisplayAddAnIncomingShipment(){
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/supervisor/manageshipments/",
                String.class)).contains("Add an Incoming Shipment");
    }

}
