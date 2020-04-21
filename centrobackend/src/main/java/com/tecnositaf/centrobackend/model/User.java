package com.tecnositaf.centrobackend.model;


import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.utilities.DateUtility;

public class User {

	private Integer idUser;

	private String username;
	private String password;
	private String mail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;
	private Integer is_female;
	
	public User() {
		
	}
	
	public User(Integer idUser, String username, String password, String mail, LocalDateTime birthday, Integer is_female) {
		this.setIdUser(idUser);
		this.setUsername(username);
		this.setPassword(password);
		this.setMail(mail);
		this.setBirthday(birthday);
		this.setIsFemale(is_female);
	}
	
	
	
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public LocalDateTime getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	
	public Integer getIsFemale() {
		return is_female;
	}
	public void setIsFemale(Integer is_female) {
		this.is_female = is_female;
	}
	
	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append("{idUser=").append(idUser);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", mail=").append(mail);
        sb.append(", birthday=").append(birthday);
        sb.append(", is_female=").append(is_female);
        return sb.toString();
    }
	
	public DTOUser toDTOUser() {
		DTOUser output = new DTOUser();						
		BeanUtils.copyProperties(this, output);		
		Integer age = DateUtility.calculateAgeOf(this.getBirthday().toLocalDate());
		output.setAge(age);

		return output;
	}
}
