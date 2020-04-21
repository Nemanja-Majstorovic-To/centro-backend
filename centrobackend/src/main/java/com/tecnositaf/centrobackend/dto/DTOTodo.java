package com.tecnositaf.centrobackend.dto;


import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tecnositaf.centrobackend.model.Todo;

public class DTOTodo {

	@JsonProperty("id")
	private Integer idTodo;

	@JsonProperty("userId")	
	private Integer idUserFk;
	
	private String title;
	private Boolean completed;

	public DTOTodo() {
		
	}
	
	public DTOTodo(Integer idTodo, Integer idUserFk, String title, Boolean completed) {
		this.setIdTodo(idTodo);
		this.setIdUserFk(idUserFk);
		this.setTitle(title);
		this.setCompleted(completed);	
	}
	
	@JsonProperty("id")
	public Integer getIdTodo() {
		return idTodo;
	}
	@JsonProperty("id")
	public void setIdTodo(Integer idTodo) {
		this.idTodo = idTodo;
	}
	@JsonProperty("userId")	
	public Integer getIdUserFk() {
		return idUserFk;
	}
	@JsonProperty("userId")	
	public void setIdUserFk(Integer idUserFk) {
		this.idUserFk = idUserFk;
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
        sb.append("{id=").append(idTodo);
        sb.append(", idUserFk=").append(idUserFk);
        sb.append(", title=").append(title);
        sb.append(", completed=").append(completed);
        sb.append('}');        
        return sb.toString();
    }
	
	public Todo toTodo() {
		Todo output = new Todo();			// 'this' 	=> DTOUser				'output'	=> User
		BeanUtils.copyProperties(this, output);		//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser
		output.setId(this.idTodo);
		output.setUserId(this.idUserFk);
		return output;
	}
}
