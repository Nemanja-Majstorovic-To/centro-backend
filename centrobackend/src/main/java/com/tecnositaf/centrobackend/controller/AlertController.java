package com.tecnositaf.centrobackend.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.alert.DeleteAlertResponse;
import com.tecnositaf.centrobackend.response.alert.GetAlertByIdResponse;
import com.tecnositaf.centrobackend.response.alert.GetAlertsByStorageYearsResponse;
import com.tecnositaf.centrobackend.response.alert.GetAlertsFromDeviceResponse;
import com.tecnositaf.centrobackend.response.alert.GetAlertsResponse;
import com.tecnositaf.centrobackend.response.alert.InsertNewAlertResponse;
import com.tecnositaf.centrobackend.response.alert.PutAlertResponse;
import com.tecnositaf.centrobackend.service.AlertService;
import com.tecnositaf.centrobackend.utilities.AlertUtility;

import io.swagger.annotations.ApiParam;

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
	public ResponseEntity<InsertNewAlertResponse> createAlert(
			@RequestBody
			@ApiParam(value = "Id Alert not required")
			DTOAlert dtoAlert) {
		logger.info("---------- POST /alert----------");

		if( !AlertUtility.isValidAlertForInsert(dtoAlert) ){		
			logger.info("INPUT VALIDATION ERROR - alert => " + dtoAlert.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}

		Alert alert = dtoAlert.toAlert();
		

		if(alert == null) {
			logger.info("ERROR WHILE CONVERTING DTO TO ALERT ");
			throw new FailureException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseErrorEnum.ERR_500);
		}
		
		int numRowsInserted = alertService.insertNewAlert(alert);
		logger.info("numRowsInserted => " + numRowsInserted);
		
		ArrayList<Alert> alerts = alertService.getAlerts();
		return ResponseEntity.status(HttpStatus.OK).body(
			new InsertNewAlertResponse(0, "SUCCESS", alert, alerts)
		);
	}
		
	@GetMapping("/{idAlert}")
	public ResponseEntity<GetAlertByIdResponse> getAlert(@PathVariable String idAlert) {
		logger.info("---------- GET /alert/{idAlert} ----------");
		Optional<Alert> alertOpt = alertService.getAlertById(idAlert);
		
		if(alertOpt.isEmpty()) {
			logger.info("INPUT VALIDATION ERROR - no alert found with id => ");
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		Alert alert = alertOpt.get();		
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
		
		Optional<Alert> searchedAlert = alertService.getAlertById(dtoAlert.getIdAlert());
		if(searchedAlert.isEmpty()) {
			logger.info("INPUT VALIDATION ERROR - alert not found for update => " + dtoAlert.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		Alert alert = searchedAlert.get();
		alert = dtoAlert.toAlert();
		int numRowsUpdated = alertService.updateAlert(alert);;
		logger.info("numRowsUpdated => " + numRowsUpdated);
		
		ArrayList<Alert> alerts = alertService.getAlerts();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new PutAlertResponse(0, "SUCCESS", alert, alerts)
		);
	}
	
	@DeleteMapping("/{idAlert}")
	public ResponseEntity<DeleteAlertResponse> deleteAlert(@PathVariable String idAlert) {
		logger.info("---------- DELETE /alert ----------");
		Optional<Alert> alertOpt = alertService.getAlertById(idAlert);
		
		if(alertOpt.isEmpty()) {
			logger.info("INPUT VALIDATION ERROR - no alert found with id => " + idAlert);
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		Alert alert = alertOpt.get();
		int numRowsDeleted = alertService.deleteAlert(alert);
		logger.info("numRowsDeleted => " + numRowsDeleted);
		
		ArrayList<Alert> alerts = alertService.getAlerts();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new DeleteAlertResponse(0, "SUCCESS", alert, alerts)
		);
	}
		
	@GetMapping("/device/{idDevice}")
	public ResponseEntity<GetAlertsFromDeviceResponse> getFromDevice(@PathVariable Integer idDevice, 
			@RequestParam(required = false)
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDate localDate) {
		
		logger.info("---------- GET /alert/device/{idDevice} ----------");
				
		ArrayList<Alert> alertsDevice;
		if(localDate == null) 
			alertsDevice = alertService.getAlertsByDevice(idDevice);
		else
			alertsDevice = alertService.getAlertsByDevice(idDevice, localDate);
		

		if(alertsDevice.isEmpty()){
			logger.info("INPUT VALIDATION ERROR  - no alert found for specified device => " + idDevice);
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetAlertsFromDeviceResponse(0, "SUCCESS", alertsDevice, localDate)
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
