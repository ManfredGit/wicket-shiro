package com.onassignment.config;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.onassignment.page.HelloWorld;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HelloWorld.class;
	}
	
	@Override
	public void init() {
		super.init();
		
		// Allow for injecting Spring beans within Wicket components
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new WebAppSession(request);
	}
	
}