package com.tecnositaf.centrobackend.response.user;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.Response;

public class GetUserByIdResponse extends Response {
	private DTOUser user;	
	
	
	public GetUserByIdResponse(int code, String message) {
		super(code, message);
		this.user = null;

	}
	
	public GetUserByIdResponse(int code, String message, User user) {
		super(code, message);
		this.user = user.toDTOUser();	
		
	}
	
	public DTOUser getUser() {
		return user;
	}
	public void setUser(DTOUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "GetUserResponse {user=" + user;
	}
}
