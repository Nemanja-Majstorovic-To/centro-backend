package com.tecnositaf.centrobackend.model;

import java.sql.Timestamp;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.utilities.DateUtility;


public class Alert {

	private Integer idAlert;
	private Integer idDeviceFk;
	private Timestamp timestamp;
	private Integer idType;
	
	public Alert() {
		
	}
	
	public Alert(Integer idAlert, Integer idDeviceFk, Timestamp ts, Integer idType) {
		this.setIdAlert(idAlert);
		this.setIdDeviceFk(idDeviceFk);
		this.setTimestamp(ts);
		this.setType(idType);	
	}
	
	
	public Integer getIdAlert() {
		return idAlert;
	}


	public void setIdAlert(Integer idAlert) {
		this.idAlert = idAlert;
	}


	public Integer getIdDeviceFk() {
		return idDeviceFk;
	}


	public void setIdDeviceFk(Integer idDeviceFk) {
		this.idDeviceFk = idDeviceFk;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getType() {
		return idType;
	}


	public void setType(Integer idType) {
		this.idType = idType;
	}

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Alert alert = (Alert) o;
		return Objects.equals(idAlert, alert.idAlert) &&
				Objects.equals(idDeviceFk, alert.idDeviceFk) &&
				Objects.equals(idType, alert.idType) &&
				Objects.equals(timestamp, alert.timestamp);
	}
	
	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Alert");
        sb.append("{id=").append(idAlert);
        sb.append(", device=").append(idDeviceFk);
        sb.append(", timestamp=").append(timestamp.toString());
        sb.append(", type=").append(idType);
        sb.append('}');        
        return sb.toString();
    }
	
	//XXX nuovo metodo che ho aggiunto al model per il passaggo al DTO corrispondente
	public DTOAlert toDTOAlert() {
		DTOAlert output = new DTOAlert();						
		BeanUtils.copyProperties(this, output);		
		/*** 'storageYears' of DTO class is a value calculated from 'timestamp' ***/
		Integer age = DateUtility.calculateAgeOf(this.getTimestamp());
		output.setStorageYears(age);

		return output;
	}

}
