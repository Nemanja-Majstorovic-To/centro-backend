package com.tecnositaf.centrobackend.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.database.DatabaseFake;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.utilities.AlertUtilities;

@RestController
@RequestMapping("/alert")
public class AlertController {
	
	@Autowired
	private DatabaseFake dbFake;
	
	@GetMapping
	public ResponseEntity<ArrayList<Alert>> readAlert() {
		return ResponseEntity.ok().body(dbFake.getAlertTable());
	}
	
	@PostMapping
	public ResponseEntity<ArrayList<Alert>> createAlert(Alert alert) {
		boolean checkValid = AlertUtilities.checkValidAlertForInsert(alert);
		if(checkValid == false) 	
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		dbFake.addAlert(alert);	//TODO check rows inserted -> INTERNAL_SERVER_ERROR
		return ResponseEntity.ok().body(dbFake.getAlertTable());
	}
		

	@GetMapping("/{idAlert}")
	public ResponseEntity<Alert> getById(@PathVariable int idAlert) {
		Alert searchedAlert = dbFake.getById(idAlert);
		if(searchedAlert == null) 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(searchedAlert);
		return ResponseEntity.ok().body(searchedAlert); 
	}
	
	@PutMapping	
	public ResponseEntity<ArrayList<Alert>> updateAlert(Alert alert) {
		boolean checkValid = AlertUtilities.checkValidAlertForUpdate(alert);
		if(checkValid == false)
			return ResponseEntity.badRequest().body(dbFake.getAlertTable());
		Alert searchedAlert = dbFake.getById(alert.getIdAlert());
		if(searchedAlert == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		dbFake.update(alert);	//TODO check rows update -> INTERNAL_SERVER_ERROR
		return ResponseEntity.ok().body(dbFake.getAlertTable());
	}
	
	@DeleteMapping("/{idAlert}")
	public ResponseEntity<ArrayList<Alert>> deleteAlert(@PathVariable int idAlert) {
		if(dbFake.delete(dbFake.getById(idAlert))==false) 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.ok().body(dbFake.getAlertTable());
	}
	
	@GetMapping("/device/{idDevice}")
	public ResponseEntity<ArrayList<Alert>> getFromDevice (@PathVariable int idDevice, Timestamp ts) {
		ArrayList<Alert> alertsDevice = dbFake.getByIdDevice(idDevice, ts);
		if(alertsDevice.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alertsDevice);
		return ResponseEntity.ok().body(alertsDevice);
	}

}
