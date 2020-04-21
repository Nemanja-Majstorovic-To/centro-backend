package com.tecnositaf.centrobackend.utilities;

import com.tecnositaf.centrobackend.dto.DTOUser;

public class UserUtility {
	
	public static boolean isValidUserForInsert(DTOUser user) {
		
		if(user.getIdUser() != null) return false;
		if(user.getUsername() == null) return false;
		if(user.getMail() == null) return false;
		if(user.getBirthday() == null) return false;	
		if(user.getIsFemale() == null) return false;	

		return true;
	}
	
	public static boolean isValidUserForUpdate(DTOUser user) {
		
		if(user.getIdUser() == null) return false;
		if(user.getUsername() == null) return false;
		if(user.getMail() == null) return false;
		if(user.getBirthday() == null) return false;	
		if(user.getIsFemale() == null) return false;	

		return true;
	}	
}
