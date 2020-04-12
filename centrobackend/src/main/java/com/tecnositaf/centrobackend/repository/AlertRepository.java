package com.tecnositaf.centrobackend.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.database.DatabaseFake;
import com.tecnositaf.centrobackend.model.Alert;

@Repository
public class AlertRepository {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DatabaseFake dbFake;
	
	public ArrayList<Alert> getAlerts() {
		return dbFake.queryGetAlerts();
	}

	public int insertNewAlert(Alert alert) {
		return dbFake.queryInsertNewAlert(alert);
	}

	public Alert getAlertById(Integer idAlert) {
		return dbFake.queryGetAlertByID(idAlert);
	}
	
	public int updateAlert(Alert alert) {
		return dbFake.queryUpdateAlert(alert);
	}
	
	public int deleteAlert(Alert alert) {
		return dbFake.queryDeleteAlert(alert);
	}
	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice) {
		return dbFake.queryGetAlertsByDevice(idDevice);
	}
	
	public ArrayList<Alert> getAlertsByDevice(Integer idDevice, Timestamp ts) {
		return dbFake.queryGetAlertsByDevice(idDevice, ts);
	}
			
	public ArrayList<Alert> getAlertsByStorageYears(Integer startTime) {
		return dbFake.queryGetAlertsByStorageYears(startTime);
	}
	

}
