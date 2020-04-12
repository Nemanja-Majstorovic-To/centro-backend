package com.tecnositaf.centrobackend.enumeration;

public enum ResponseErrorEnum {

	ERR_1  (1,"Empty/Invalid Field"),
	ERR_2  (2,"Database action error"),
	ERR_3  (3,"No Result Found"),
	ERR_500 (500,"UnexpectedError");

	
	public int id;
	public String description;
	
	
	ResponseErrorEnum(int id, String descrpiton){
		this.id=id;
		this.description = descrpiton;
	}

	
	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}			
	
	@Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Type");
        sb.append("{description=").append(description);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}