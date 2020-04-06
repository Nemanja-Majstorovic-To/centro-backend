package com.tecnositaf.centrobackend.database;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

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
		
		Alert alert1 = new Alert(1, 9, new Timestamp(2020),  AlertType.INCENDIO);
		addAlert(alert1);
		Alert alert2 = new Alert(2, 9, new Timestamp(2020),  AlertType.INCIDENTE);
		addAlert(alert2);
    }
	
	
	
	public  ArrayList<Alert> getAlertTable() {
		return alertTable;
	}
	
	public int addAlert(Alert alert) {
		DatabaseFake.simulateDatabaseAutoIncrementFor(alertTable, alert);
		alertTable.add(alert);
		return 1;
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
	
	public int update(Alert alert) {
		Alert alertDB = getById(alert.getIdAlert());
		if( alertDB!=null ) {
			alertDB = alert;
			return 1;
		}
		return 0;
	}
	
	public boolean delete(Alert alert) {
		return alertTable.remove(alert);
	}
	
	/*************************************************************************/
	
	private static void simulateDatabaseAutoIncrementFor(ArrayList<Alert> tableUsers, Alert alert){
		Optional<Alert> userWithMaxId = tableUsers.stream().max( Comparator.comparing(Alert::getIdAlert) );
		int nextIdAlert = userWithMaxId.isPresent() ? userWithMaxId.get().getIdAlert()+1 : 1;
		alert.setIdAlert(nextIdAlert);
	}
	
}
