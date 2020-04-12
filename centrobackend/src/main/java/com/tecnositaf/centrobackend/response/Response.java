package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;

public class Response {

	private int code;
	private String message;
	
	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Response(ResponseErrorEnum responseErrorEnum) {
		super();
		this.code = responseErrorEnum.id;
		this.message = responseErrorEnum.description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response {code=" + code + ", message=" + message + "}";
	} 
	
}
