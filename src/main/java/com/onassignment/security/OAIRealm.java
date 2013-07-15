package com.onassignment.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.onassignment.model.User;

public class OAIRealm extends AbstractDatabaseRealm {
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		OAIToken upToken = (OAIToken) token;
		User user = retrieveUser(upToken);
		
		boolean ldapSuccess;
		// DO SOME LDAP MAGIC HERE
		if ("LDAP".equals("LDAP")) {
			ldapSuccess = true;
		} else {
			ldapSuccess = true;
		}
		
		doCredentialsMatcher(ldapSuccess);
		
		return new SimpleAuthenticationInfo(user, upToken, "OAIRealm");
	}
	
	@Override
	public boolean supports(AuthenticationToken token) {
		if (token instanceof OAIToken) {
			return true;
		}
		
		return false;
	}
	
	// Custom credentials matcher returns based on success of LDAP
	private void doCredentialsMatcher(final boolean match) {
		setCredentialsMatcher(new CredentialsMatcher() {

			@Override
			public boolean doCredentialsMatch(AuthenticationToken token,
					AuthenticationInfo info) {
				
				return match;
			}
			
		});
	}

}
