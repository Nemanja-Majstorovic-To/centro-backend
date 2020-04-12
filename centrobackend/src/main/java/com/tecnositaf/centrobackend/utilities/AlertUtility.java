package com.tecnositaf.centrobackend.utilities;

import com.tecnositaf.centrobackend.dto.DTOAlert;

public class AlertUtility {
	
	public static boolean isValidAlertForInsert(DTOAlert alert) {
		if(alert.getIdAlert() != null) return false;
		if(alert.getIdDeviceFk() == null) return false;
		if(alert.getTimestamp() == null) return false;
		if(alert.getType() == null) return false;	
		
		return true;
	}
	
	public static boolean isValidAlertForUpdate(DTOAlert alert) {
		if(alert.getIdAlert() == null) return false;
		if(alert.getIdDeviceFk() == null) return false;
		if(alert.getTimestamp() == null) return false;
		if(alert.getType() == null) return false;	
		
		return true;
	}		
	
}
