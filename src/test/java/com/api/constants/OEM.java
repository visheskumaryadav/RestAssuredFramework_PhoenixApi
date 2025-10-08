package com.api.constants;

public enum OEM {
	GOOGLE(1),
	APPLE(2);
	
	
	private int code;
	
	private OEM(int code) {
		this.code = code;
	}
	
	
	public int getCode() {
		return code;
	}
}
