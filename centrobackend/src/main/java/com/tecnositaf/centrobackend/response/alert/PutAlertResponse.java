package com.tecnositaf.centrobackend.response.alert;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.Response;

public class PutAlertResponse extends Response {
	private DTOAlert alertUpdated;
	private ArrayList<DTOAlert> alerts;	
	private int size;
	
	public PutAlertResponse(int code, String message) {
		super(code, message);
		this.alertUpdated = null;
		this.alerts = null;
		this.size = 0;
	}

	public PutAlertResponse(int code, String message, Alert alertUpdated, ArrayList<Alert> alerts, int size) {
		super(code, message);
		this.alertUpdated = alertUpdated.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = size;
	}
	public PutAlertResponse(int code, String message, Alert alertUpdated, ArrayList<Alert> alerts) {
		super(code, message);
		this.alertUpdated = alertUpdated.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = alerts.size();
	}

	
	
	public DTOAlert getAlertUpdated() {
		return alertUpdated;
	}

	public void setAlertUpdated(DTOAlert alertUpdated) {
		this.alertUpdated = alertUpdated;
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
		return "PutAlertResponse {alertUpdated=" + alertUpdated + ", alerts=" + alerts + ", size=" + size + "}";
	}
}
