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
public class PulAlertEndpointTest {

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wepAppContext;
    
    /**********     REQUEST json    **********/
    private final String alertPutRequestBodyJSON = "{\"idAlert\":3,\"idDeviceFk\":90,\"timestamp\":\"2000-01-08T19:03:55.164+0000\",\"type\":4 }";
    private final String alertPutFailPutRequestBodyJSON = "{\"idDeviceFk\":90,\"timestamp\":\"1990-01-08T19:03:55.164+0000\",\"type\":3 }";
    private final String alertNonExistentPutFailRequestBodyJSON = "{\"idAlert\":5,\"idDeviceFk\":90,\"timestamp\":\"2000-01-08T19:03:55.164+0000\",\"type\":4 }";

    /**********     RESPONSE json    **********/
    private final String putAlertOnInitResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"alertUpdated\": { " +
                "\"idAlert\": 3," +
                "\"idDeviceFk\": 90," +
                "\"timestamp\": \"2000-01-08T19:03:55.164+0000\"," +
                "\"storageYears\": 20," +
                "\"type\": 4" +
            "}, \n" +
            "\"alerts\":[" +
                "{\"idAlert\":1,\"idDeviceFk\":9,\"timestamp\":\"1997-04-18T10:00:00.000+0000\",\"storageYears\":22,\"type\":1},  \n" +
                "{\"idAlert\":2,\"idDeviceFk\":9,\"timestamp\":\"1995-05-07T10:00:00.000+0000\",\"storageYears\":24,\"type\":2},  \n" +
                "{\"idAlert\":3,\"idDeviceFk\":90,\"timestamp\":\"2000-01-08T19:03:55.164+0000\",\"storageYears\":20,\"type\":4}  \n" +
            "],\"size\":3" +
        "}";
    private final String putAlertOnInitInvalidFieldResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_1.id +", \"message\":\""+ ResponseErrorEnum.ERR_1.description +"\"}";
    private final String putAlertOnInitNonExistentResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_3.id +", \"message\":\""+ ResponseErrorEnum.ERR_3.description +"\"}";

    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
    }

    private final String ENDPOINT_RESOURCE_BASE_URL = "/alert";
    
    @Test
    public void successOnInit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( alertPutRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //response
                .andExpect(content().json( putAlertOnInitResponseJSON ))
                .andDo(print());
    }

    @Test
    public void failureForEmptyField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( alertPutFailPutRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( putAlertOnInitInvalidFieldResponseJSON ))
                .andDo(print());
    }
    
    @Test
    public void failureForNonExistentAlert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( alertNonExistentPutFailRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( putAlertOnInitNonExistentResponseJSON ))
                .andDo(print());
    }
}
