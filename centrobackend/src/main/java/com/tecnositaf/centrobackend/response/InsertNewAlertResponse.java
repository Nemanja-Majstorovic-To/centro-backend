package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class InsertNewAlertResponse extends Response {
	private DTOAlert alertInserted;
	private ArrayList<DTOAlert> alerts;	
	private int size;
	
	public InsertNewAlertResponse(int code, String message) {
		super(code, message);
		this.alertInserted = null;
		this.alerts = null;
		this.size = 0;
	}

	public InsertNewAlertResponse(int code, String message, Alert alertInserted, ArrayList<Alert> alerts, int size) {
		super(code, message);
		this.alertInserted = alertInserted.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = size;
	}
	public InsertNewAlertResponse(int code, String message, Alert alertInserted, ArrayList<Alert> alerts) {
		super(code, message);
		this.alertInserted = alertInserted.toDTOAlert();
		this.alerts = new ArrayList<DTOAlert>();	//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = alerts.size();
	}

	
	
	public DTOAlert getAlertInserted() {
		return alertInserted;
	}

	public void setAlertInserted(DTOAlert alertInserted) {
		this.alertInserted = alertInserted;
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
		return "InsertNewAlertResponse {alertInserted=" + alertInserted + ", alerts=" + alerts + ", size=" + size + "}";
	}
}
