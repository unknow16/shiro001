package com.fuyi.shiro001;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Main {

	public static void main(String[] args) {
		// 1. 获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory(
				"classpath:shiro-jdbc-realm.ini");

		// 2. 得到SecurityManager实例，并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3. 得到Subject及创建用户名密码验证Token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			// 4. 登录，及去身份认证
			subject.login(token);
			System.out.println("登录成功");
		} catch (AuthenticationException e) {
			// 5. 验证失败 TODO something
			e.printStackTrace();
			System.out.println("登录失败");
		}

		// 6. 断言用户已登录
		Assert.assertEquals(true, subject.isAuthenticated());

		// 7. 退出
		subject.logout();
		
	}

}
