package com.tecnositaf.centrobackend.response.user;

import java.util.ArrayList;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.Response;

public class GetUsersResponse extends Response {
	private ArrayList<DTOUser> users;	
	private int size;
	
	
	public GetUsersResponse(int code, String message) {
		super(code, message);
		this.users = null;
		this.size = 0;
	}
	public GetUsersResponse(int code, String message, ArrayList<User> users, int size) {
		super(code, message);
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);	
		this.size = size;
	}
	public GetUsersResponse(int code, String message, ArrayList<User> users) {
		super(code, message);
		this.users = new ArrayList<DTOUser>();	
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
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
		return "GetUsersResponse {users=" + users + ", size=" + size + "}";
	}
}
