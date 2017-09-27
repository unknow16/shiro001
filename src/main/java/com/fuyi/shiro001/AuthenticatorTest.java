package com.fuyi.shiro001;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class AuthenticatorTest {
	
	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		PrincipalCollection principals = subject.getPrincipals();
		List list = principals.asList();
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	
	@Test(expected=UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithFail() {
		login("classpath:shiro-authenticator-all-fail.ini");
	}

	private void login(String configFile) {
		
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		subject.login(token);
	}
	
	@After
	public void tearDown() throws Exception {
		//退出时解除绑定subject到线程，否则对下次测试造成影响
		ThreadContext.unbindSubject();
	}
}
