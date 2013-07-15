package com.onassignment.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.onassignment.model.dao.UserDao;
import com.onassignment.model.User;
import com.onassignment.model.Permission;
import com.onassignment.model.Role;

public abstract class AbstractDatabaseRealm extends AuthorizingRealm{
	
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected abstract AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		
		if (user == null) {
			throw new AuthorizationException("No users were found to authenticate.");
		}
		
		// Roles/Permissions are fetched eagerly and exist on the user object
		Set<org.apache.shiro.authz.Permission> securityPermissions = new HashSet<org.apache.shiro.authz.Permission>();
		Set<String> securityRoles = new HashSet<String>();
		
		for (Role role : user.getRoles()) {
			securityRoles.add(role.getRole()); // Add Role string name to Set
			
			for (Permission permission : role.getPermissions()) {
				String permissionStr = permission.getDomain() + ":" + permission.getAction();
				
				if (!securityPermissions.contains(permissionStr)) {
					securityPermissions.add(new WildcardPermission(permissionStr)); // Add Shiro Permissions
				}
			}
		}
		
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		auth.setRoles(securityRoles);
		auth.setObjectPermissions(securityPermissions);
		
		return auth;
	}
	
	protected User retrieveUser(UsernamePasswordToken upToken) {
		User user = getUserDao().loadByLoginId(upToken.getUsername());
		
		if (user == null) {
			throw new AuthenticationException("No such username or password exists in the system.");
		}
		
		return user;
	}
	
	public abstract boolean supports(AuthenticationToken token);
	
}
