package com.tecnositaf.centrobackend.enumeration;

import java.util.Arrays;
import java.util.List;

public enum AlertTypeEnum {

	INCIDENTE("incidente", 1),
	NEBBIA("nebbia", 2),
	INCENDIO("incendio", 3),
	VEICOLO_CONTROMANO("veicolo contromano", 4);

	private String description;
	private int code;
	
	private AlertTypeEnum(String description , int code) {
		this.description = description;
		this.code = code;			
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}		
	
	
	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Type");
        sb.append("{description=").append(description);
        sb.append(", code=").append(code);
        sb.append('}');
        return sb.toString();
    }
	
	/****************************************************************/
	
	public static AlertTypeEnum getById(int idType) {
		List<AlertTypeEnum> alertTypeList = Arrays.asList(AlertTypeEnum.values());
		
		AlertTypeEnum result = alertTypeList.stream()
				.filter(x -> idType == x.code)
				.findFirst()
				.orElse(null);
		
		return result;
	}

}
