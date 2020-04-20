package com.tecnositaf.centrobackend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;
import com.tecnositaf.centrobackend.utilities.DateUtility;

@Service
public class AlertService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private AlertRepository alertRepository;
	
	/*** READ ***/
	
	public ArrayList<Alert> getAlerts() {
		return (ArrayList<Alert>) alertRepository.findAll();
	}
	
	/*** READ BY ALERT ID ***/
	
	public Optional<Alert> getAlertById(String idAlert) {
		return alertRepository.findById(idAlert);
	}
	
	/*** READ BY DEVICE ID ***/
	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice) {
		return alertRepository.findByIdDeviceFk(idDevice);
	}	
	
	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
		return alertRepository.findByIdDeviceFkAndDate(idDevice, localDate);
	}
	
	
	/*** READ BY STORAGE YEARS ***/
	public ArrayList<Alert> getAlertsByStorageYears(Integer numYears) {
		ArrayList<Alert> alertTable = (ArrayList<Alert>) alertRepository.findAll();
		ArrayList<Alert> alerts = new ArrayList<>();
		for(Alert alert : alertTable) {
			if(DateUtility.calculateAgeOf(alert.getLocalDate()) >= numYears)
				alerts.add(alert);
		}
		
		return alerts;
	}
		
	
	/*** INSERT ***/	

	public int insertNewAlert(Alert alert) {
		
		if( alertRepository.insert(alert) == null) {
			logger.error("ERROR insert alert failure - " + alert.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return 1;
	}
	
	/*** UPDATE ***/
	
	public int updateAlert(Alert alert) {
		
		if( alertRepository.save(alert) == null ) {
			logger.error("ERROR update alert failure - " + alert.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		
		return 1;
	}
	
	/*** DELETE ***/
	
	public int deleteAlert(Alert alert) {
		alertRepository.delete(alert);
		return 1;
	}
	
	public void deleteAllAlerts() {
		alertRepository.deleteAll();
	}
	
	
}
