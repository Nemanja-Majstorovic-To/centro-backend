package com.tecnositaf.centrobackend.controller.alert;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GetAllAlertsEndpointTest {
	   private MockMvc mockMvc;

	    @Autowired
	    private WebApplicationContext wepAppContext;

	    @Before
	    public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	    }

	    private final String ENDPOINT_RESOURCE_BASE_URL = "/alert";

	    /**********     RESPONSE json    **********/
	    private final String getAllAlertsOnInitResponseJSON = "{" +
	            "\"code\":0," +
	            "\"message\":\"SUCCESS\"," +
	            "\"alerts\":[" +
	                "{\"idAlert\":1,\"idDeviceFk\":9,\"timestamp\":\"1997-04-18T10:00:00.000+0000\",\"storageYears\":22,\"type\":1 },  \n" +
	                "{\"idAlert\":2,\"idDeviceFk\":9,\"timestamp\":\"1995-05-07T10:00:00.000+0000\",\"storageYears\":24,\"type\":2},  \n" +
	                "{\"idAlert\":3,\"idDeviceFk\":9,\"timestamp\":\"1999-01-08T11:00:00.000+0000\",\"storageYears\":21,\"type\":3}  \n" +
	            "],\"size\":3" +
	        "}";


	    @Test
	    public void successOnInit() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	                //response
	                .andExpect(jsonPath("$.code").exists())
	                .andExpect(jsonPath("$.code").value(0))
	                .andExpect(jsonPath("$.message").exists())
	                .andExpect(jsonPath("$.message").value("SUCCESS"))
	                .andExpect(jsonPath("$.size").exists())
	                .andExpect(jsonPath("$.size").value(3))
	                .andExpect(jsonPath("$.alerts").exists())
	                .andExpect(jsonPath("$.alerts").isArray())
	                .andExpect(jsonPath("$.alerts", hasSize(3)))
	                // 'alerts'
	                .andExpect(jsonPath("$.alerts[0].idAlert").exists())
	                .andExpect(jsonPath("$.alerts[0].idAlert").value(1))
	                .andExpect(jsonPath("$.alerts[0].idDeviceFk").exists())
	                .andExpect(jsonPath("$.alerts[0].idDeviceFk").value(9))
	                .andExpect(jsonPath("$.alerts[0].timestamp").exists())
	                .andExpect(jsonPath("$.alerts[0].timestamp").value("1997-04-18T10:00:00.000+0000"))
	                .andExpect(jsonPath("$.alerts[0].type").exists())
	                .andExpect(jsonPath("$.alerts[0].type").value(1))
	                .andExpect(jsonPath("$.alerts[0].storageYears").exists())
	                .andExpect(jsonPath("$.alerts[0].storageYears").value(22))
	                .andExpect(jsonPath("$.alerts[1].idAlert").exists())
	                .andExpect(jsonPath("$.alerts[1].idAlert").value(2))
	                .andExpect(jsonPath("$.alerts[1].idDeviceFk").exists())
	                .andExpect(jsonPath("$.alerts[1].idDeviceFk").value(9))
	                .andExpect(jsonPath("$.alerts[1].timestamp").exists())
	                .andExpect(jsonPath("$.alerts[1].timestamp").value("1995-05-07T10:00:00.000+0000"))
	                .andExpect(jsonPath("$.alerts[1].type").exists())
	                .andExpect(jsonPath("$.alerts[1].type").value(2))
	                .andExpect(jsonPath("$.alerts[1].storageYears").exists())
	                .andExpect(jsonPath("$.alerts[1].storageYears").value(24))
	                .andExpect(jsonPath("$.alerts[2].idAlert").exists())
	                .andExpect(jsonPath("$.alerts[2].idAlert").value(3))
	                .andExpect(jsonPath("$.alerts[2].idDeviceFk").exists())
	                .andExpect(jsonPath("$.alerts[2].idDeviceFk").value(9))
	                .andExpect(jsonPath("$.alerts[2].timestamp").exists())
	                .andExpect(jsonPath("$.alerts[2].timestamp").value("1999-01-08T11:00:00.000+0000"))
	                .andExpect(jsonPath("$.alerts[2].type").exists())
	                .andExpect(jsonPath("$.alerts[2].type").value(3))
	                .andExpect(jsonPath("$.alerts[2].storageYears").exists())
	                .andExpect(jsonPath("$.alerts[2].storageYears").value(21))

	                .andDo(print());
	    }

	    @Test
	    public void successOnInitAlternative() throws Exception
	    {
	        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	                //response
	                .andExpect(content().json(getAllAlertsOnInitResponseJSON))

	                .andDo(print());
	    }
}
