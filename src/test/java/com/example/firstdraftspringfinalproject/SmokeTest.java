package com.example.firstdraftspringfinalproject;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstdraftspringfinalproject.controllers.AuthenticationController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SmokeTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authenticationController).isNotNull();
    }

}
