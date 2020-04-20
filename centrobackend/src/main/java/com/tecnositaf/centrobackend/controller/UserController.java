package com.tecnositaf.centrobackend.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.user.DeleteUserResponse;
import com.tecnositaf.centrobackend.response.user.InsertNewUserResponse;
import com.tecnositaf.centrobackend.response.user.PutUserResponse;
import com.tecnositaf.centrobackend.response.user.GetUserByIdResponse;
import com.tecnositaf.centrobackend.response.user.GetUsersResponse;
import com.tecnositaf.centrobackend.service.UserService;
import com.tecnositaf.centrobackend.utilities.UserUtility;

@RestController
@RequestMapping("/user")
public class UserController {

final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<GetUsersResponse> getUser() {
		logger.info("---------- GET /user ----------");
		
		ArrayList<User> users = userService.getUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetUsersResponse(0, "SUCCESS", users)
		);
	}
	
	@GetMapping("/{idUser}")
	public ResponseEntity<GetUserByIdResponse> getUser(@PathVariable Integer idUser) {
		logger.info("---------- GET /user/{idUser} ----------");
		User user = userService.getUserById(idUser);
		
		if(user == null) {
			logger.info("INPUT VALIDATION ERROR - no user found with id => " + idUser.intValue());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new GetUserByIdResponse(0, "SUCCESS", user)
		);
	}

	@PostMapping
	public ResponseEntity<InsertNewUserResponse> createUser(@RequestBody DTOUser dtoUser) {
		logger.info("---------- POST /user----------");

		if( !UserUtility.isValidUserForInsert(dtoUser) ){		
			logger.info("INPUT VALIDATION ERROR - user => " + dtoUser.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}
		
		User user = dtoUser.toUser();
	
		int numRowsInserted = userService.insertNewUser(user);
		logger.info("numRowsInserted => " + numRowsInserted);
		
		ArrayList<User> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(
			new InsertNewUserResponse(0, "SUCCESS", user, users)
		);
	}

	@PutMapping	
	public ResponseEntity<PutUserResponse> updateUser(@RequestBody DTOUser dtoUser) {
		logger.info("---------- PUT /user ----------");

		if(!UserUtility.isValidUserForUpdate(dtoUser)){
			logger.info("INPUT VALIDATION ERROR - user missing one or more fields => " + dtoUser.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}
		
		User searchedUser = userService.getUserById(dtoUser.getIdUser());
		if(searchedUser == null) {
			logger.info("INPUT VALIDATION ERROR - user not found for update => " + dtoUser.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}
		
		searchedUser = dtoUser.toUser();
		int numRowsUpdated = userService.updateUser(searchedUser);;
		logger.info("numRowsUpdated => " + numRowsUpdated);
		
		ArrayList<User> users = userService.getUsers();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new PutUserResponse(0, "SUCCESS", searchedUser, users)
		);
	}
	
	@DeleteMapping("/{idUser}")
	public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable Integer idUser) {
		logger.info("---------- DELETE /user ----------");
		User user = userService.getUserById(idUser);
		
		if(user == null) {
			logger.info("INPUT VALIDATION ERROR - no user found with id => " + idUser.intValue());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_3);
		}

		int numRowsDeleted = userService.deleteUser(idUser);
		logger.info("numRowsDeleted => " + numRowsDeleted);
		
		ArrayList<User> users = userService.getUsers();	
		return ResponseEntity.status(HttpStatus.OK).body(
				new DeleteUserResponse(0, "SUCCESS", user, users)
		);
	}
	
	
}
