package com.example.firstdraftspringfinalproject.TDDtests.integration;

import org.apache.catalina.connector.Request;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.XpathResultMatchers;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.XpathExpectationsHelper;
import org.springframework.test.web.servlet.ResultMatcher;
//import javax.xml.xpath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@SpringBootTest
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SecurityConfig.class)
//@WebAppConfiguration
public class EmployeePortalControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext context;

    @Test
    public void testEmployeeWelcomeLoads () throws Exception {
        mockMvc.perform(get("/employee"))
                .andExpect(status().is(302));
//                .andExpect(xpath("//h2").exists());
    }

    @Test
    public void shouldReturnHeader() throws Exception {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//        HttpServletRequest request = new RequestPostProcessor();
//        RequestPostProcessor user = new RequestPostProcessor() {
//            @Override
//            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
//                return null;
//            }
//        };

//        this.mockMvc.perform(get("/employee").with(user))
//                .andExpect(status().is(302)).andExpect(content().string(containsString("Home")));
    }
}
