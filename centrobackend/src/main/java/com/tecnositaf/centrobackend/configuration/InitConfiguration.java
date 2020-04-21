package com.tecnositaf.centrobackend.configuration;

import java.time.LocalDate;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.AlertService;

@Configuration
public class InitConfiguration implements CommandLineRunner {
	
	@Autowired
	private AlertService service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void mongoDbInit() {
	    service.deleteAllAlerts();
	    Alert alert1 = new Alert(9, LocalDate.of(1994, Month.SEPTEMBER, 9), 1);
		Alert alert2 = new Alert(10, LocalDate.of(1998, Month.MAY, 7), 2);
		Alert alert3 = new Alert(9, LocalDate.of(2003, Month.JULY, 16), 3);
		// save a couple of alerts
	    service.insertNewAlert(alert1);
	    service.insertNewAlert(alert2);
	    service.insertNewAlert(alert3);


	    // fetch all alerts
		logger.info("---------- GET /alert ----------");

	    for (Alert alert : service.getAlerts()) {
	    	logger.info(alert.toString());	      
	    }
	}

	@Override
	public void run(String... args) throws Exception {
		mongoDbInit();		
	}
}
