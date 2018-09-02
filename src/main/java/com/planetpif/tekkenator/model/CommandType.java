package com.planetpif.tekkenator.model;

public enum CommandType {
	UNKNOWN(-1), 
	HELP(0), 
	FRAMEDATA(1), 
	LEGEND(2), 
	FORMATREQUEST(3);

/*	@Override
	public String toString() {

		return "" + code;
	}*/

	private int code;

	private CommandType(int c) {
		code = c;
	}

	public int getCode() {
		return code;

	}
}
