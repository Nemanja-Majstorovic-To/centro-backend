package com.tecnositaf.centrobackend.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.database.DatabaseFake;
import com.tecnositaf.centrobackend.model.Alert;

@Repository
public class AlertRepository {

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
		

}
