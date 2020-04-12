package com.tecnositaf.centrobackend.database;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.utilities.DateUtility;

@Component
public class DatabaseFake {

	final  Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArrayList<Alert> alertTable = new ArrayList<>();
	
	@PostConstruct
    private void init() {
        Alert alert1 = new Alert(1, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)),  1);
        queryInsertNewAlert(alert1);		
		Alert alert2 = new Alert(2, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1995, Month.MAY, 7), LocalTime.NOON)),  2);
		queryInsertNewAlert(alert2);
		Alert alert3 = new Alert(3, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1999, Month.JANUARY, 8), LocalTime.NOON)),  3);
		queryInsertNewAlert(alert3);
    }
	
		
	public ArrayList<Alert> queryGetAlerts() {
		logger.info("SELECT * FROM ALERTS");
		return this.alertTable;
	}
	
	public int queryInsertNewAlert(Alert alert) {
		logger.info("INSERT NEW ALERT - " + alert.toString());
		DatabaseFake.simulateDatabaseAutoIncrementFor(alertTable, alert);
		boolean success = this.alertTable.add(alert);
		return success ? 1 : 0;
	}
	
	public Alert queryGetAlertByID(Integer id) {
		logger.info("SELECT alert FROM ALERTS WHERE alert.id = " + id);
		Optional<Alert> alertSearched = this.alertTable.stream()
				.filter(item -> item.getIdAlert()==id)
				.findFirst();
		return alertSearched.isPresent() ? alertSearched.get() : null;
	}
	
	
	public int queryUpdateAlert(Alert alert) {
		logger.info("UPDATE alert SET idDevice = " + alert.getIdDeviceFk() 
			+ ", timestamp = " + alert.getTimestamp()
			+ ", type = " + alert.getType()
			+ " WHERE alert.id = " + alert.getIdAlert());
		Alert alertDB = queryGetAlertByID(alert.getIdAlert());
		if( alertDB==null ) {
			return 0;
		}
		alertDB.setIdDeviceFk(alert.getIdDeviceFk());
		alertDB.setTimestamp(alert.getTimestamp());
		alertDB.setType(alert.getType());
		return 1;
	}
	
	public int queryDeleteAlert(Alert alert) {
		logger.info("DELETE FROM alertTable WHERE idAlert = " + alert.getIdAlert());
		if(!alertTable.remove(alert))
			return 0;
		return 1;
	}
	
	public ArrayList<Alert> queryGetAlertsByDevice(Integer idDevice) {
		logger.info("SELECT * FROM ALERTS WHERE alert.idDeviceFk = " + idDevice);
		ArrayList<Alert> alertsDevice = new ArrayList<>();
		for(Alert alert : alertTable) { 
			if(alert.getIdDeviceFk()==idDevice) { 
				alertsDevice.add(alert);
			}
		}
		
		return alertsDevice;
	}
	
	public ArrayList<Alert> queryGetAlertsByDevice(Integer idDevice, Timestamp timestamp) {
		logger.info("SELECT * FROM ALERTS WHERE alert.idDeviceFk = " + idDevice
				+ " AND alert.timestamp >=" + timestamp.toString());
		ArrayList<Alert> alertsDevice = new ArrayList<>();
		for(Alert alert : alertTable) { 
			if(alert.getIdDeviceFk()==idDevice && 
				alert.getTimestamp().getTime() >= timestamp.getTime()) { 
				alertsDevice.add(alert);
			}
		}
		
		return alertsDevice;
	}
	

	public ArrayList<Alert> queryGetAlertsByStorageYears(Integer startTime) {
		logger.info("SELECT * FROM ALERTS WHERE alert.storageYears >= " + startTime);
		ArrayList<Alert> alerts = new ArrayList<>();
		for(Alert alert : alertTable) {
			if(DateUtility.calculateAgeOf(alert.getTimestamp()) >= startTime)
				alerts.add(alert);
		}
		
		return alerts;
	}
	
	/*************************************************************************/
	
	private static void simulateDatabaseAutoIncrementFor(ArrayList<Alert> alertTable, Alert alert){
		Optional<Alert> alertWithMaxId = alertTable.stream().max(Comparator.comparing(Alert::getIdAlert));
		int nextIdAlert = alertWithMaxId.isPresent() ? alertWithMaxId.get().getIdAlert()+1 : 1;
		alert.setIdAlert(nextIdAlert);
	}
	
}
