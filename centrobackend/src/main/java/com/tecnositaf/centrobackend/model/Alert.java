package com.tecnositaf.centrobackend.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.utilities.DateUtility;

@Document("Alert")
public class Alert {

	@Id
	private String idAlert;
	
	private Integer idDeviceFk;
	private LocalDate localDate;
	private Integer idType;
	
	public Alert() {
		
	}
	
	public Alert(String idAlert, Integer idDeviceFk, LocalDate ts, Integer idType) {
		this.setIdAlert(idAlert);
		this.setIdDeviceFk(idDeviceFk);
		this.setLocalDate(ts);
		this.setType(idType);	
	}
	
	public Alert(Integer idDeviceFk, LocalDate ts, Integer idType) {
		this.setIdDeviceFk(idDeviceFk);
		this.setLocalDate(ts);
		this.setType(idType);	
	}
	
	public String getIdAlert() {
		return idAlert;
	}


	public void setIdAlert(String idAlert) {
		this.idAlert = idAlert;
	}


	public Integer getIdDeviceFk() {
		return idDeviceFk;
	}


	public void setIdDeviceFk(Integer idDeviceFk) {
		this.idDeviceFk = idDeviceFk;
	}


	public LocalDate getLocalDate() {
		return localDate;
	}


	public void setLocalDate(LocalDate LocalDate) {
		this.localDate = LocalDate;
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
				Objects.equals(localDate, alert.localDate);
	}
	
	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Alert");
        sb.append("{id=").append(idAlert);
        sb.append(", device=").append(idDeviceFk);
        sb.append(", LocalDate=").append(localDate.toString());
        sb.append(", type=").append(idType);
        sb.append('}');        
        return sb.toString();
    }
	
	public DTOAlert toDTOAlert() {
		DTOAlert output = new DTOAlert();						
		BeanUtils.copyProperties(this, output);		
		/*** 'storageYears' of DTO class is a value calculated from 'LocalDate' ***/
		Integer age = DateUtility.calculateAgeOf(this.getLocalDate());
		output.setStorageYears(age);

		return output;
	}

}
