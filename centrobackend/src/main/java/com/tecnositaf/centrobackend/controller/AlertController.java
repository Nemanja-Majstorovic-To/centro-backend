package com.tecnositaf.centrobackend.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.DeleteAlertResponse;
import com.tecnositaf.centrobackend.response.GetAlertByIdResponse;
import com.tecnositaf.centrobackend.response.GetAlertsByStorageYearsResponse;
import com.tecnositaf.centrobackend.response.GetAlertsFromDeviceResponse;
import com.tecnositaf.centrobackend.response.GetAlertsResponse;
import com.tecnositaf.centrobackend.response.InsertNewAlertResponse;
import com.tecnositaf.centrobackend.response.PutAlertResponse;
import com.tecnositaf.centrobackend.service.AlertService;
import com.tecnositaf.centrobackend.utilities.AlertUtility;

@RestController
@RequestMapping("/alert")
public class AlertController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AlertService alertService;
	
	@GetMapping
	public ResponseEntity<GetAlertsResponse> getAlert() {
		logger.info("---------- GET /alert ----------");
		
		ArrayList<Alert> alerts = alertService.getAlerts();
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetAlertsResponse(0, "SUCCESS", alerts)
		);
	}
	
	
	@PostMapping
	public ResponseEntity<InsertNewAlertResponse> createAlert(@RequestBody DTOAlert dtoAlert) {
		logger.info("---------- POST /alert----------");

		if( !AlertUtility.isValidAlertForInsert(dtoAlert) ){		//XXX aggiunta (per scopi di esempio) la validazione dell'input "fatta a mano"
			logger.info("INPUT VALIDATION ERROR - alert => " + dtoAlert.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}
		Alert alert = dtoAlert.toAlert();
		
		int numRowsInserted = alertService.insertNewAlertWithRowsInsertedCheck(alert);
		logger.info("numRowsInserted => " + numRowsInserted);
		
		ArrayList<Alert> alerts = alertService.getAlerts();
		return ResponseEntity.status(HttpStatus.OK).body(
			new InsertNewAlertResponse(0, "SUCCESS", alert, alerts)
		);
	}
		
	@GetMapping("/{idAlert}")
	public ResponseEntity<GetAlertByIdResponse> getAlert(@PathVariable Integer idAlert) {
		logger.info("---------- GET /alert/{idAlert} ----------");
		Alert alert = alertService.getAlertById(idAlert);
		
		if(alert == null) {
			logger.info("INPUT VALIDATION ERROR - no alert found with id => " + idAlert.intValue());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetAlertByIdResponse(0, "SUCCESS", alert)
		);
	}
	
	@PutMapping	
	public ResponseEntity<PutAlertResponse> updateAlert(@RequestBody DTOAlert dtoAlert) {
		logger.info("---------- PUT /alert ----------");

		if(!AlertUtility.isValidAlertForUpdate(dtoAlert)){
			logger.info("INPUT VALIDATION ERROR - alert missing one or more fields => " + dtoAlert.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}
		
		Alert searchedAlert = alertService.getAlertById(dtoAlert.getIdAlert());
		if(searchedAlert == null) {
			logger.info("INPUT VALIDATION ERROR - alert not found for update => " + dtoAlert.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		searchedAlert = dtoAlert.toAlert();
		int numRowsUpdated = alertService.updateAlert(searchedAlert);;
		logger.info("numRowsUpdated => " + numRowsUpdated);
		
		ArrayList<Alert> alerts = alertService.getAlerts();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new PutAlertResponse(0, "SUCCESS", searchedAlert, alerts)
		);
	}
	
	@DeleteMapping("/{idAlert}")
	public ResponseEntity<DeleteAlertResponse> deleteAlert(@PathVariable Integer idAlert) {
		logger.info("---------- DELETE /alert ----------");
		Alert alert = alertService.getAlertById(idAlert);
		
		if(alert == null) {
			logger.info("INPUT VALIDATION ERROR - no alert found with id => " + idAlert.intValue());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}

		int numRowsDeleted = alertService.deleteAlert(alert);
		logger.info("numRowsDeleted => " + numRowsDeleted);
		
		ArrayList<Alert> alerts = alertService.getAlerts();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new DeleteAlertResponse(0, "SUCCESS", alert, alerts)
		);
	}
	
	
	@GetMapping("/device/{idDevice}")
	public ResponseEntity<GetAlertsFromDeviceResponse> getFromDevice(@PathVariable Integer idDevice, Timestamp ts) {
		logger.info("---------- GET /alert/device/{idDevice} ----------");
				
		ArrayList<Alert> alertsDevice;
		if(ts == null) 
			alertsDevice = alertService.getAlertsByDevice(idDevice);
		else
			alertsDevice = alertService.getAlertsByDevice(idDevice, ts);

		if(alertsDevice.isEmpty()){
			logger.info("INPUT VALIDATION ERROR  - no alert found for specified device => " + idDevice);
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetAlertsFromDeviceResponse(0, "SUCCESS", alertsDevice, ts)
		);
	}
	
	@GetMapping("/filter/storageYears/{storageYears}") 
	public ResponseEntity<GetAlertsByStorageYearsResponse> getAlertsByStorageYears(@PathVariable Integer storageYears){
		logger.info("---------- GET /alert/filter/storageYears/{storageYears} ----------");
		ArrayList<Alert> alerts = alertService.getAlertsByStorageYears(storageYears);

		if(alerts.isEmpty()){
			logger.info("INPUT VALIDATION ERROR  - no alert with the following amount of stored years => " + storageYears);
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetAlertsByStorageYearsResponse(0, "SUCCESS", alerts, storageYears)
		);
	}
	

}
