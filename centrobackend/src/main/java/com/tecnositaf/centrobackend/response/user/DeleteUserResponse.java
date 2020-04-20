package com.tecnositaf.centrobackend.response.user;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.Response;

public class DeleteUserResponse extends Response {
	private DTOUser userToDelete;
	private ArrayList<DTOUser> users;	
	private int size;
	
	public DeleteUserResponse(int code, String message) {
		super(code, message);
		this.userToDelete = null;
		this.users = null;
		this.size = 0;
	}

	public DeleteUserResponse(int code, String message, User userToDelete, ArrayList<User> users, int size) {
		super(code, message);
		this.userToDelete = userToDelete.toDTOUser();
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = size;
	}
	public DeleteUserResponse(int code, String message, User userToDelete, ArrayList<User> users) {
		super(code, message);
		this.userToDelete = userToDelete.toDTOUser();
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
	}

	
	
	public DTOUser getUserToDelete() {
		return userToDelete;
	}

	public void setUserToDelete(DTOUser userToDelete) {
		this.userToDelete = userToDelete;
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
		return "DeleteUserResponse {userToDelete=" + userToDelete + ", users=" + users + ", size=" + size + "}";
	}
}
