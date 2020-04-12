package com.tecnositaf.centrobackend.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;

@Service
public class AlertService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private AlertRepository alertRepository;
	
	/*** READ ***/
	
	public ArrayList<Alert> getAlerts() {
		return alertRepository.getAlerts();
	}
	
	/*** READ BY ALERT ID ***/
	
	public Alert getAlertById(Integer idAlert) {
		return alertRepository.getAlertById(idAlert);
	}
	
	/*** READ BY DEVICE ID ***/
	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice) {
		return alertRepository.getAlertsByDevice(idDevice);
	}	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice, Timestamp timestamp) {
		return alertRepository.getAlertsByDevice(idDevice, timestamp);
	}
	
	/*** READ BY STORAGE YEARS ***/
	public ArrayList<Alert> getAlertsByStorageYears(Integer numYears) {
		/*Long start = DateUtility.calculateStartDate(numYears);
		if(start == null) {
			logger.error("ERROR calculate storage years failure");
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_500
			);
		}*/
		return alertRepository.getAlertsByStorageYears(numYears);
	}
		
	
	/*** INSERT ***/	

	public int insertNewAlertWithRowsInsertedCheck(Alert alert) {
		int numRowsInserted = alertRepository.insertNewAlert(alert);
		if( numRowsInserted==0 ) {
			logger.error("ERROR insert alert failure - " + alert.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return numRowsInserted;
	}
	
	/*** UPDATE ***/
	
	public int updateAlert(Alert alert) {
		int numRowsUpdated = alertRepository.updateAlert(alert);
		if( numRowsUpdated==0 ) {
			logger.error("ERROR update alert failure - " + alert.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return numRowsUpdated;
	}
	
	/*** DELETE ***/
	
	public int deleteAlert(Alert alert) {
		int numRowsDeleted = alertRepository.deleteAlert(alert);
		if( numRowsDeleted==0 ) {
			logger.error("ERROR delete alert failure - " + alert.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return numRowsDeleted;
	}
	
	
	
	
}
