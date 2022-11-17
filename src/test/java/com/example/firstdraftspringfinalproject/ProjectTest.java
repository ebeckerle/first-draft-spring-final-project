package com.example.firstdraftspringfinalproject;

import com.example.firstdraftspringfinalproject.controllers.AuthenticationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ProjectTest {

    @Autowired
    private AuthenticationController authenticationController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authenticationController).isNotNull();
    }

}
