package com.tecnositaf.centrobackend.response.todo;

import com.tecnositaf.centrobackend.dto.DTOTodo;
import com.tecnositaf.centrobackend.model.Todo;
import com.tecnositaf.centrobackend.response.Response;

public class GetTodoByIdResponse extends Response {
	private DTOTodo todo;	
	
	public GetTodoByIdResponse(int code, String message) {
		super(code, message);
		this.todo = null;
	}
	public GetTodoByIdResponse(int code, String message, Todo todo) {
		super(code, message);
		this.todo = todo.toDTOTodo();	
	}

	public DTOTodo getTodo() {
		return todo;
	}
	
	public void setTodo(DTOTodo todo) {
		this.todo = todo;
	}
		
	@Override
	public String toString() {
		return "GetTodoByIdResponse {todo=" + todo + "}";
	}
}
