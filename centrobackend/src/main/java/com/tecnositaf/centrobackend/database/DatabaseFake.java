package com.tecnositaf.centrobackend.database;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.utilities.AlertUtilities.AlertType;

@Component
public class DatabaseFake {

	private ArrayList<Alert> alertTable = new ArrayList<>();
	
	@PostConstruct
    private void postConstruct() {
        Alert alert = new Alert(0, 9, new Timestamp(2020),  AlertType.NEBBIA);
		addAlert(alert);
    }
	
	public  ArrayList<Alert> getAlertTable() {
		return alertTable;
	}
	
	//TODO public int addAlert(Alert alert) {
	public boolean addAlert(Alert alert) {
		return alertTable.add(alert);
	}
	
	public Alert getById(int id) {
		for(Alert alert : alertTable) { 
		   if(alert.getIdAlert()==id) { 
			   return alert;
		   }
		}
		return null;
	}
	
	public ArrayList<Alert> getByIdDevice(int idDevice, Timestamp timestamp) {
		ArrayList<Alert> alertsDevice = new ArrayList<>();
		if(timestamp == null) {
			for(Alert alert : alertTable) { 
			   if(alert.getIdDeviceFk()==idDevice) { 
					   alertsDevice.add(alert);
			   }
			}
		} else {
			for(Alert alert : alertTable) { 
			   if(alert.getIdDeviceFk()==idDevice && 
				  alert.getTimestamp().getTime() >= timestamp.getTime()) { 
				   alertsDevice.add(alert);
			   }
			}
		}
		
		return alertsDevice;
	}
	
	//TODO idea sbagliata
	//TODO public int update(Alert old, Alert updated) {
	public int update(Alert old, Alert updated) {
		if(old != null) {
			old.setIdAlert(updated.getIdAlert());
			old.setIdDeviceFk(updated.getIdDeviceFk());
			old.setTimestamp(updated.getTimestamp());
			old.setType(updated.getType());
		} 
		return alertTable.size();
	}
	
	public boolean delete(Alert alert) {
		return alertTable.remove(alert);
	}
}
