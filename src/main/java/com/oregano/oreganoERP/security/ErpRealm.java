package com.oregano.oreganoERP.security;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.oregano.oreganoERP.data.UsuarioRepositorio;
import com.oregano.oreganoERP.model.Perfil;
import com.oregano.oreganoERP.model.Usuario;
import com.oregano.oreganoERP.util.ServiceLocator;


public class ErpRealm extends AuthorizingRealm {

	@Inject
	private UsuarioRepositorio uRepositorio;

	public ErpRealm() {
		setName("erpRealm"); // This name must match the name in the User
								// class's getPrincipals() method
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA");
		setCredentialsMatcher(matcher);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		loadRepositorio();
		Long userId = (Long) principals.fromRealm(getName()).iterator().next();
		Usuario user = uRepositorio.porId(userId);
		
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Perfil perfil : user.getPerfis()) {
				info.addRole(perfil.getNome());
				info.addStringPermissions(perfil.getPermissoes());
			}
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		
		loadRepositorio();
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		Usuario user = uRepositorio.peloLogin( token.getUsername() );

		if (user != null) {
			System.out.println("senha: " + user.getSenha());
			return new SimpleAuthenticationInfo(user.getId(), user.getSenha(),
					user.getNome());
		} else {
			return null;
		}
	}
	
	private void loadRepositorio() {
		
		if ( this.uRepositorio == null ) {
			this.uRepositorio = (UsuarioRepositorio)  ServiceLocator.locate("java:module/UsuarioRepositorio");
		}
	}

}
