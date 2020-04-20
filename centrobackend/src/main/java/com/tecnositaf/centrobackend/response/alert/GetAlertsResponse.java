package com.tecnositaf.centrobackend.response.alert;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.Response;


public class GetAlertsResponse extends Response {

	private ArrayList<DTOAlert> alerts;	
	private int size;
	
	
	public GetAlertsResponse(int code, String message) {
		super(code, message);
		this.alerts = null;
		this.size = 0;
	}
	public GetAlertsResponse(int code, String message, ArrayList<Alert> alerts, int size) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		alerts.forEach(user -> 
			this.alerts.add( user.toDTOAlert() )
		);	
		this.size = size;
	}
	public GetAlertsResponse(int code, String message, ArrayList<Alert> alerts) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		alerts.forEach(user -> 
			this.alerts.add( user.toDTOAlert() )
		);
		this.size = alerts.size();
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
		return "GetAlertsResponse {alerts=" + alerts + ", size=" + size + "}";
	}

}
