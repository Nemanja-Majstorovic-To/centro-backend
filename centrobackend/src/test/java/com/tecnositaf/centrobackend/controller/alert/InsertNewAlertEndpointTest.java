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
public class InsertNewAlertEndpointTest {

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wepAppContext;
    
    /**********     REQUEST json    **********/
    private final String alertInsertRequestBodyJSON = "{\"idDeviceFk\":9,\"timestamp\":\"1990-01-08T19:03:55.164+0000\",\"type\":3 }";
    private final String alertFailInsertRequestBodyJSON = "{\"timestamp\":\"1990-01-08T19:03:55.164+0000\",\"type\":3 }";

    /**********     RESPONSE json    **********/
    private final String insertNewAlertOnInitResponseJSON = "{" +
            "\"code\":0," +
            "\"message\":\"SUCCESS\"," +
            "\"alertInserted\": { " +
                "\"idAlert\": 4," +
                "\"idDeviceFk\": 9," +
                "\"timestamp\": \"1990-01-08T19:03:55.164+0000\"," +
                "\"storageYears\": 30," +
                "\"type\": 3" +
            "}, \n" +
            "\"alerts\":[" +
                "{\"idAlert\":1,\"idDeviceFk\":9,\"timestamp\":\"1997-04-18T10:00:00.000+0000\",\"storageYears\":22,\"type\":1},  \n" +
                "{\"idAlert\":2,\"idDeviceFk\":9,\"timestamp\":\"1995-05-07T10:00:00.000+0000\",\"storageYears\":24,\"type\":2},  \n" +
                "{\"idAlert\":3,\"idDeviceFk\":9,\"timestamp\":\"1999-01-08T11:00:00.000+0000\",\"storageYears\":21,\"type\":3},  \n" +
                "{\"idAlert\":4,\"idDeviceFk\":9,\"timestamp\":\"1990-01-08T19:03:55.164+0000\",\"storageYears\":30,\"type\":3}   \n" +
            "],\"size\":4" +
        "}";
    private final String insertNewAlertOnInitInvalidFieldResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_1.id +", \"message\":\""+ ResponseErrorEnum.ERR_1.description +"\"}";

    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
    }

    private final String ENDPOINT_RESOURCE_BASE_URL = "/alert";
    
    @Test
    public void successOnInit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( alertInsertRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //response
                .andExpect(content().json( insertNewAlertOnInitResponseJSON ))
                .andDo(print());
    }

    @Test
    public void failureForEmptyField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content( alertFailInsertRequestBodyJSON )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //response
                .andExpect(content().json( insertNewAlertOnInitInvalidFieldResponseJSON ))
                .andDo(print());
    }

}
