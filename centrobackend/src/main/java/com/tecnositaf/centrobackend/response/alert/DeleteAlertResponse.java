package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class DeleteAlertResponse extends Response {
	private DTOAlert alertToDelete;
	private ArrayList<DTOAlert> alerts;	
	private int size;
	
	public DeleteAlertResponse(int code, String message) {
		super(code, message);
		this.alertToDelete = null;
		this.alerts = null;
		this.size = 0;
	}

	public DeleteAlertResponse(int code, String message, Alert alertToDelete, ArrayList<Alert> alerts, int size) {
		super(code, message);
		this.alertToDelete = alertToDelete.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = size;
	}
	public DeleteAlertResponse(int code, String message, Alert alertToDelete, ArrayList<Alert> alerts) {
		super(code, message);
		this.alertToDelete = alertToDelete.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = alerts.size();
	}

	
	
	public DTOAlert getAlertToDelete() {
		return alertToDelete;
	}

	public void setAlertToDelete(DTOAlert alertToDelete) {
		this.alertToDelete = alertToDelete;
	}

	public ArrayList<DTOAlert> getAlerts() {
		return alerts;
	}

	public void setAlerts(ArrayList<DTOAlert> alerts) {
		this.alerts = alerts;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	@Override
	public String toString() {
		return "DeleteAlertResponse {alertToDelete=" + alertToDelete + ", alerts=" + alerts + ", size=" + size + "}";
	}
}
