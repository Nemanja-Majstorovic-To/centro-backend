package com.tecnositaf.centrobackend.controller.alert;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeleteAlertEndpointTest {
	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wepAppContext;
    
    /**********     REQUEST    **********/
    private final String ENDPOINT_RESOURCE_BASE_URL = "/alert/1";
    private final String ENDPOINT_RESOURCE_BASE_URL_NON_EXISTENT = "/alert/100";

    /**********     RESPONSE json    **********/
    private final String deleteAlertResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"alertToDelete\": { " +
                "\"idAlert\": 1," +
                "\"idDeviceFk\": 9," +
                "\"timestamp\": \"1997-04-18T10:00:00.000+0000\"," +
                "\"storageYears\": 22," +
                "\"type\": 1" +
            "}, \n" +
            "\"alerts\":[" +
                "{\"idAlert\":2,\"idDeviceFk\":9,\"timestamp\":\"1995-05-07T10:00:00.000+0000\",\"storageYears\":24,\"type\":2}, \n" +
                "{\"idAlert\":3,\"idDeviceFk\":9,\"timestamp\":\"1999-01-08T11:00:00.000+0000\",\"storageYears\":21,\"type\":3}  \n" +
            "],\"size\":2" +
        "}";
    private final String deleteAlertNonExistentResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_3.id +", \"message\":\""+ ResponseErrorEnum.ERR_3.description +"\"}";

    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
    }

    
    @Test
    public void successOnDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_RESOURCE_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                //response
                .andExpect(content().json( deleteAlertResponseJSON ))
                .andDo(print());
    }
    
    @Test
    public void failureForNonExistentAlert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_RESOURCE_BASE_URL_NON_EXISTENT)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( deleteAlertNonExistentResponseJSON ))
                .andDo(print());
    }
}
