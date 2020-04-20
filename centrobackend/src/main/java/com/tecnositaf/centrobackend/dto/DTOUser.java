package com.tecnositaf.centrobackend.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.utilities.StringUtility;

public class DTOUser {
	
	private Integer idUser;

	private String username;
	private String mail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;
	private Integer is_female;
	private Integer age;
	
	public DTOUser() {
		
	}
	
	public DTOUser(Integer idUser, String username, String mail, LocalDateTime birthday, Integer is_female, Integer age) {
		this.setIdUser(idUser);
		this.setUsername(username);
		this.setMail(mail);
		this.setBirthday(birthday);
		this.setIsFemale(is_female);
		this.setAge(age);
	}
	
	public DTOUser(String username, String mail, LocalDateTime birthday, Integer is_female, Integer age) {
		this.setUsername(username);
		this.setMail(mail);
		this.setBirthday(birthday);
		this.setIsFemale(is_female);
		this.setAge(age);
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
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age=age;
	}

	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append("{id_user=").append(idUser);
        sb.append(", username=").append(username);
        sb.append(", mail=").append(mail);
        sb.append(", birthday=").append(birthday);
        sb.append(", is_female=").append(is_female);
        sb.append(", age=").append(age);
        return sb.toString();
    }
	
	public User toUser() {
		User output = new User();			// 'this' 	=> DTOUser				'output'	=> User
		BeanUtils.copyProperties(this, output);		//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser
		String defaultPassword = StringUtility.generateCasualString();
        output.setPassword(defaultPassword);
		return output;
	}
}
