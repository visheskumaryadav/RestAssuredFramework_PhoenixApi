package com.api.constants;

public enum Model {
	NEXUS_2_BLUE(1), GALLAXY(2);

	int code;

	Model(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
