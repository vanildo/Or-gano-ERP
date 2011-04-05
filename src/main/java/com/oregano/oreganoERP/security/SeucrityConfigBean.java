package com.oregano.oreganoERP.security;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;


@Startup
@Singleton
public class SeucrityConfigBean {
	
	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void configurarSeguranca ( ) {
		
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = (SecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
	}
}
