package com.tecnositaf.centrobackend.response.alert;

import java.time.LocalDate;
import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.Response;

public class GetAlertsFromDeviceResponse extends Response {
	private ArrayList<DTOAlert> alerts;	
	private LocalDate localDate;
	private int size;
	
	public GetAlertsFromDeviceResponse(int code, String message) {
		super(code, message);
		this.alerts = null;
		this.size = 0;
	}

	public GetAlertsFromDeviceResponse(int code, String message, ArrayList<Alert> alerts, LocalDate localDate, int size) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.localDate = localDate;
		this.size = size;
	}
	public GetAlertsFromDeviceResponse(int code, String message, ArrayList<Alert> alerts, LocalDate localDate) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.localDate = localDate;
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
	
	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	
	@Override
	public String toString() {
		return "GetAlertsFromDeviceResponse {alertUpdated= alerts=" + alerts + ", localDate=" + localDate.toString() + " size=" + size + "}";
	}

	
}
