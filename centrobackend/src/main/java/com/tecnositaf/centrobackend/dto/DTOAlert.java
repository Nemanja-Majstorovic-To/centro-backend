package com.tecnositaf.centrobackend.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.model.Alert;

public class DTOAlert {

	private String idAlert;
	private Integer idDeviceFk;
	private LocalDate localDate;
	private Integer idType;
	private Integer storageYears;
	
	public DTOAlert() {
		
	}
	
	public DTOAlert(String idAlert, Integer idDeviceFk, LocalDate localDate, Integer idType, Integer storageYears) {
		this.setIdAlert(idAlert);
		this.setIdDeviceFk(idDeviceFk);
		this.setLocalDate(localDate);
		this.setType(idType);
		this.setStorageYears(storageYears);
	}
	
	public DTOAlert(Integer idDeviceFk, LocalDate localDate, Integer idType, Integer storageYears) {
		this.setIdDeviceFk(idDeviceFk);
		this.setLocalDate(localDate);
		this.setType(idType);
		this.setStorageYears(storageYears);
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
	
	public Integer getStorageYears() {
		return storageYears;
	}


	public void setStorageYears(Integer storageYears) {
		this.storageYears = storageYears;
	}

	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Alert");
        sb.append("{id=").append(idAlert);
        sb.append(", device=").append(idDeviceFk);
        sb.append(", LocalDate=").append(localDate.toString());
        sb.append(", type=").append(idType);
        sb.append(", storageYears=").append(storageYears);
        sb.append('}');        
        return sb.toString();
    }

	/*************************************************************************************************/
	/*************************************************************************************************/
	/*************************************************************************************************/

	
	public Alert toAlert() {
		Alert output = new Alert();			// 'this' 	=> DTOUser				'output'	=> User
		BeanUtils.copyProperties(this, output);		//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser
		return output;
	}



}
