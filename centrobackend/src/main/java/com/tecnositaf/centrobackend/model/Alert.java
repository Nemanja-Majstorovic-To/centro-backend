package com.tecnositaf.centrobackend.model;

import java.sql.Timestamp;

import com.tecnositaf.centrobackend.utilities.AlertUtilities.AlertType;


public class Alert {

	private Integer idAlert;
	private Integer idDeviceFk;
	private Timestamp timestamp;
	private AlertType type;
	
	//TODO (opzionale) private String[] description = {"incidente, nebbia, incendio, veicolo contromano"}; => AlertTypeEnum (code, description)
	
	public Alert(int idAlert, int idDeviceFk, Timestamp ts, AlertType type) {
		this.setIdAlert(idAlert);
		this.setIdDeviceFk(idDeviceFk);
		this.setTimestamp(ts);
		this.setType(type);	
	}
	
	
	public Integer getIdAlert() {
		return idAlert;
	}


	public void setIdAlert(int idAlert) {
		this.idAlert = idAlert;
	}


	public Integer getIdDeviceFk() {
		return idDeviceFk;
	}


	public void setIdDeviceFk(int idDeviceFk) {
		this.idDeviceFk = idDeviceFk;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public AlertType getType() {
		return type;
	}


	public void setType(AlertType type) {
		this.type = type;
	}

	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Alert");
        sb.append("{id=").append(idAlert);
        sb.append(", device=").append(idDeviceFk);
        sb.append(", timestamp=").append(timestamp.toString());
        sb.append(", type=").append(type);
        sb.append('}');        
        return sb.toString();
    }

}
