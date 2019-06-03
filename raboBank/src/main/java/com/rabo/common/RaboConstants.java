package com.rabo.common;

public enum RaboConstants {
	MSG("message"),
	ERROR("Exception"),
	REDIRECT("redirect:uploadStatus"),
	UNSUPPORTED_FILEFORMAT("Only csv or xml file allowed!!"),
	FILE_TYPE_XML("text/xml"),
	FILE_APP_XML("application/xml"),
	FILE_TYPE_CSV("application/vnd.ms-excel"),
	FILE_Text_CSV("text/csv"),
	    UNKNOWN("");
	

	    private String value;

	    RaboConstants(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
}
