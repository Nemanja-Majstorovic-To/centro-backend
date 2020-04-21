package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Todo;
import com.tecnositaf.centrobackend.response.todo.GetTodoByIdResponse;
import com.tecnositaf.centrobackend.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {

final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<GetTodoByIdResponse> getTodo(@PathVariable Integer id) {
		logger.info("---------- GET /todo/{id} ----------");
		Todo todo = todoService.getTodoById(id);
				
		if(todo == null) {
			logger.info("INPUT VALIDATION ERROR - no todo found with id => " + id);
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetTodoByIdResponse(0, "SUCCESS", todo)
		);
	}
}
