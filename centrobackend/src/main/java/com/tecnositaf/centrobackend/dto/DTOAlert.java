package com.tecnositaf.centrobackend.dto;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.model.Alert;

public class DTOAlert {

	private Integer idAlert;
	private Integer idDeviceFk;
	private Timestamp timestamp;
	private Integer idType;
	private Integer storageYears;
	
	public DTOAlert() {
		
	}
	
	public DTOAlert(Integer idAlert, Integer idDeviceFk, Timestamp ts, Integer idType, Integer storageYears) {
		this.setIdAlert(idAlert);
		this.setIdDeviceFk(idDeviceFk);
		this.setTimestamp(ts);
		this.setType(idType);
		this.setStorageYears(storageYears);
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
        sb.append(", timestamp=").append(timestamp.toString());
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
