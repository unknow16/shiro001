package com.fuyi.shiro001.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

	public String getName() {
		return "myrealm2";
	}

	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String) token.getPrincipal(); //传递过来的用户名  == 身份标识
		String password = new String((char[]) token.getCredentials()); //传递过来的密码 == 凭证证明
		if(!"wang".equals(username)) {
			throw new UnknownAccountException();
		}
		if(!"123".equals(password)) {
			throw new IncorrectCredentialsException();
		}
		
		System.out.println("realm2");
		//身份认证通过，返回一个AuthenticationInfo实现
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
