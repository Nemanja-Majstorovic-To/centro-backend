package com.tecnositaf.centrobackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.tecnositaf.centrobackend.model.Todo;

@Repository
public class TodoRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${service.rest.todo.url}")
	String SERVICE_REST_TODO_URL;
	
	public Todo findByIdTodo(Integer idTodo) {
		return restTemplate.getForObject(SERVICE_REST_TODO_URL+"/"+idTodo , Todo.class);
	}
	
}
