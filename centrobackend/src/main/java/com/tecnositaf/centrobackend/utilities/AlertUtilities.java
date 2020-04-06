package com.tecnositaf.centrobackend.utilities;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.model.Alert;

public class AlertUtilities {
	
	public enum AlertType {

		INCIDENTE("incidente", 1),
		NEBBIA("nebbia", 2),
		INCENDIO("incendio", 3),
		VEICOLO_CONTROMANO("veicolo contromano", 4);

		private String description;
		private int code;
		
		private AlertType(String description , int code) {
			this.description = description;
			this.code = code;			
		}

		public String getDescription() {
			return description;
		}

		public int getCode() {
			return code;
		}			
		
		@Override
	    public String toString(){
	        final StringBuilder sb = new StringBuilder();
	        sb.append("Type");
	        sb.append("{description=").append(description);
	        sb.append(", code=").append(code);
	        sb.append('}');
	        return sb.toString();
	    }
	}

	public static boolean checkValidAlertForInsert(Alert alert) {
		if(alert.getIdAlert() != null)
			return false;
		if(alert.getIdDeviceFk() == null)
			return false;
		if(alert.getTimestamp() == null)
			return false;
		if(alert.getType() == null)
			return false;	
		return true;
	}
	public static boolean checkValidAlertForUpdate(Alert alert) {
		if(alert.getIdAlert() == null)
			return false;
		if(alert.getIdDeviceFk() == null)
			return false;
		if(alert.getTimestamp() == null)
			return false;
		if(alert.getType() == null)
			return false;	
		return true;
	}
	
	public static boolean checkIdConflict(Alert alertCheck, ArrayList<Alert> alertTable) {
		for(Alert alert : alertTable) { 
		   if(alert.getIdAlert().intValue()==alertCheck.getIdAlert().intValue()) { 
				   return true;
		   }
		} 
		return false;
	}
	
}
