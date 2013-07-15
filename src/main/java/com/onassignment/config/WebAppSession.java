package com.onassignment.config;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.onassignment.model.User;

public class WebAppSession extends WebSession {
	private static final long serialVersionUID = 1L;
	private User user;
	
	public WebAppSession(Request request) {
		super(request);
	}
	
	public static WebAppSession get() {
		return (WebAppSession) Session.get();
	}
	
	public boolean isAuthenticated() {
		// A user is not authenticated
		if (user == null) {
			return false;
		}
		
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
