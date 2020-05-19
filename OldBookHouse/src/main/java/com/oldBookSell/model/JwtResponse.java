package com.oldBookSell.model;

import java.io.Serializable;

/**
 * This is JwtResponse Class that is use to get the jwt token
 * @author  Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
