package com.tecnositaf.centrobackend.controller.alert;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class GetAlertByIdEndpointTest {
	   private MockMvc mockMvc;

	    @Autowired
	    private WebApplicationContext wepAppContext;

	    @Before
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	    }

	    /**********     REQUEST    **********/
	    private final String ENDPOINT_RESOURCE_BASE_URL = "/alert/2";
	    private final String ENDPOINT_RESOURCE_BASE_URL_FAIL_NOT_EXISTENT = "/alert/20";

	    /**********     RESPONSE json    **********/
	    private final String getAlertByIdResponseJSON = "{" +
	            "\"code\":0," +
	            "\"message\":\"SUCCESS\"," +
	            "\"alert\":" +
	                "{\"idAlert\":2,\"idDeviceFk\":9,\"timestamp\":\"1995-05-07T10:00:00.000+0000\",\"storageYears\":24,\"type\":2} \n" 
	            + "}";
	    private final String getAlertNonExistentResponseJSON = "{\"code\":"+ ResponseErrorEnum.ERR_3.id +", \"message\":\""+ ResponseErrorEnum.ERR_3.description +"\"}";


	    @Test
	    public void successOnGet() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	                //response
	                .andExpect(jsonPath("$.code").exists())
	                .andExpect(jsonPath("$.code").value(0))
	                .andExpect(jsonPath("$.message").exists())
	                .andExpect(jsonPath("$.message").value("SUCCESS"))
	                .andExpect(jsonPath("$.alert").exists())
	                
	                .andExpect(jsonPath("$.alert.idAlert").exists())
	                .andExpect(jsonPath("$.alert.idAlert").value(2))
	                .andExpect(jsonPath("$.alert.idDeviceFk").exists())
	                .andExpect(jsonPath("$.alert.idDeviceFk").value(9))
	                .andExpect(jsonPath("$.alert.timestamp").exists())
	                .andExpect(jsonPath("$.alert.timestamp").value("1995-05-07T10:00:00.000+0000"))
	                .andExpect(jsonPath("$.alert.type").exists())
	                .andExpect(jsonPath("$.alert.type").value(2))
	                .andExpect(jsonPath("$.alert.storageYears").exists())
	                .andExpect(jsonPath("$.alert.storageYears").value(24))
	                
	                .andDo(print());
	    }

	    @Test
	    public void successOnGetAlternative() throws Exception
	    {
	        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	                //response
	                .andExpect(content().json(getAlertByIdResponseJSON))

	                .andDo(print());
	    }
	    
	    @Test
	    public void failureForNonExistentAlert() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL_FAIL_NOT_EXISTENT)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isBadRequest())
	                //response
	                .andExpect(content().json( getAlertNonExistentResponseJSON ))
	                .andDo(print());
	    }
}
