package com.tecnositaf.centrobackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.model.Todo;
import com.tecnositaf.centrobackend.repository.TodoRepository;

@Service
public class TodoService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private TodoRepository todoRepository;
	
	/*** READ BY Todo ID ***/
	
	public Todo getTodoById(Integer idTodo) {
		return todoRepository.findByIdTodo(idTodo);
	}
}
