package com.tecnositaf.centrobackend.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.repository.UserRepository;

@Service
public class UserService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	/*** READ ***/
	
	public ArrayList<User> getUsers() {
		return (ArrayList<User>) userRepository.getUserList();		
	}
	
	public User getUserById(Integer idUser) {
		return userRepository.getUserById(idUser);
	}
	
	/*** INSERT ***/	

	public int insertNewUser(User user) {
		
		if( userRepository.addUser(user) == null) {
			logger.error("ERROR insert user failure - " + user.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return 1;
	}
	
	/*** UPDATE ***/
	
	public int updateUser(User user) {
		
		if( userRepository.updateUserById(user) == null ) {
			logger.error("ERROR update user failure - " + user.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		
		return 1;
	}
	
	/*** DELETE ***/
	
	public int deleteUser(Integer idUser) {
		return userRepository.deleteUserById(idUser);		
	}
	
}
