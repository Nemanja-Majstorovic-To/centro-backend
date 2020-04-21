package com.tecnositaf.centrobackend.response.user;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.Response;

public class PutUserResponse extends Response {
	private DTOUser userUpdated;
	private ArrayList<DTOUser> users;	
	private int size;
	
	public PutUserResponse(int code, String message) {
		super(code, message);
		this.userUpdated = null;
		this.users = null;
		this.size = 0;
	}

	public PutUserResponse(int code, String message, User userUpdated, ArrayList<User> users, int size) {
		super(code, message);
		this.userUpdated = userUpdated.toDTOUser();
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = size;
	}
	public PutUserResponse(int code, String message, User userUpdated, ArrayList<User> users) {
		super(code, message);
		this.userUpdated = userUpdated.toDTOUser();
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
	}

	
	
	public DTOUser getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(DTOUser userUpdated) {
		this.userUpdated = userUpdated;
	}

	public ArrayList<DTOUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<DTOUser> users) {
		this.users = users;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	@Override
	public String toString() {
		return "PutUserResponse {userUpdated=" + userUpdated + ", users=" + users + ", size=" + size + "}";
	}
}
