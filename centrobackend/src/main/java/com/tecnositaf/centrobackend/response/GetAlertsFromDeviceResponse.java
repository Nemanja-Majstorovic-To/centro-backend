package com.tecnositaf.centrobackend.response;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class GetAlertsFromDeviceResponse extends Response {
	private ArrayList<DTOAlert> alerts;	
	private Timestamp timestamp;
	private int size;
	
	public GetAlertsFromDeviceResponse(int code, String message) {
		super(code, message);
		this.alerts = null;
		this.size = 0;
	}

	public GetAlertsFromDeviceResponse(int code, String message, ArrayList<Alert> alerts, Timestamp ts, int size) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.timestamp = ts;
		this.size = size;
	}
	public GetAlertsFromDeviceResponse(int code, String message, ArrayList<Alert> alerts, Timestamp ts) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.timestamp = ts;
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
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public String toString() {
		return "GetAlertsFromDeviceResponse {alertUpdated= alerts=" + alerts + ", timestamp=" + timestamp.toString() + " size=" + size + "}";
	}

	
}
