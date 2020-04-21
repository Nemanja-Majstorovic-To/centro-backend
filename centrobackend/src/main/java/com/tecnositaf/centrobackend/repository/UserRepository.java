package com.tecnositaf.centrobackend.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.mapper.UserMapper;
import com.tecnositaf.centrobackend.model.User;

@Repository
public class UserRepository {
	
	@Autowired 
	UserMapper userMapper;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*** READ ***/
	
	public List<User> getUserList(){				
		return userMapper.getUserList();
	}

	public User getUserById(Integer idUser) {
		return userMapper.getUserById(idUser);
	}
	
	/*** INSERT ***/
	
	public Integer addUser(User user) {
		return userMapper.addUser(user);
	}

	/*** UPDATE ***/
	
	public Integer updateUserById(User user) {
		return userMapper.updateUser(user);
		
	}
	
	/*** DELETE ***/
	
	public Integer deleteUserById(Integer idUser) {
		return userMapper.deleteUser(idUser);
	}

}
