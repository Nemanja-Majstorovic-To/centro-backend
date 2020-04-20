package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;

public class GetAlertByIdResponse extends Response {

	private DTOAlert alert;	
		
	public GetAlertByIdResponse(int code, String message) {
		super(code, message);
		this.alert = null;
	}
	public GetAlertByIdResponse(int code, String message, Alert alert) {
		super(code, message);
		this.alert = alert.toDTOAlert();	
	}

	public DTOAlert getAlert() {
		return alert;
	}
	
	public void setAlert(DTOAlert alert) {
		this.alert = alert;
	}
		
	@Override
	public String toString() {
		return "GetAlertByIdResponse {alert=" + alert + "}";
	}

	
}
