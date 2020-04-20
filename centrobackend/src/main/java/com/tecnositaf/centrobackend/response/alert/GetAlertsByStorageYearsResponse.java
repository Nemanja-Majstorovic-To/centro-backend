package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class GetAlertsByStorageYearsResponse extends Response {
	private ArrayList<DTOAlert> alerts;	
	private int size;
	private int years;
	
	public GetAlertsByStorageYearsResponse(int code, String message) {
		super(code, message);
		this.alerts = null;
		this.size = 0;
	}

	public GetAlertsByStorageYearsResponse(int code, String message, ArrayList<Alert> alerts, int size, int years) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = size;
		this.setYears(years);
	}
	public GetAlertsByStorageYearsResponse(int code, String message, ArrayList<Alert> alerts, int years) {
		super(code, message);
		this.alerts = new ArrayList<DTOAlert>();	
		alerts.forEach(alert -> 
			this.alerts.add( alert.toDTOAlert() )
		);
		this.size = alerts.size();
		this.setYears(years);
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
	
	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}
	

	@Override
	public String toString() {
		return "GetAlertsByStorageYearsResponse {alertUpdated= alerts=" + alerts +  ", size=" + size + ", years=" + years + "}";
	}

	

}
