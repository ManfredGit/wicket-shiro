package com.onassignment.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class OAIToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	
	public OAIToken() {
		super();
	}

    public OAIToken(String username, String password) {
        super(username, password);
    }

}
