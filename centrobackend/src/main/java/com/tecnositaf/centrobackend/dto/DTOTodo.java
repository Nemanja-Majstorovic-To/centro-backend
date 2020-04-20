package com.tecnositaf.centrobackend.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.model.Todo;

public class DTOTodo {

	private Integer id;

	private Integer userId;
	private String title;
	private Boolean completed;
	
	public DTOTodo() {
		
	}
	
	public DTOTodo(Integer id, Integer userId, String title, Boolean completed) {
		this.setId(id);
		this.setUserId(userId);
		this.setTitle(title);
		this.setCompleted(completed);	
	}
	
	public DTOTodo(Integer idDeviceFk, LocalDate ts, Integer idType) {
		this.setId(id);
		this.setUserId(userId);
		this.setTitle(title);
		this.setCompleted(completed);	
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	
	/*************************************************************************************************/
	/*************************************************************************************************/
	/*************************************************************************************************/

	@Override
	public String toString() {
	    final StringBuilder sb = new StringBuilder();
        sb.append("DTOTodo");
        sb.append("{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", completed=").append(completed);
        sb.append('}');        
        return sb.toString();
    }
	
	public Todo toTodo() {
		Todo output = new Todo();			// 'this' 	=> DTOUser				'output'	=> User
		BeanUtils.copyProperties(this, output);		//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser
		return output;
	}
}
