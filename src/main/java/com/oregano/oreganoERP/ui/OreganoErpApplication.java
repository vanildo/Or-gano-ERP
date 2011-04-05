package com.oregano.oreganoERP.ui;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.data.EmpresaSingleton;
import com.oregano.oreganoERP.data.UsuarioRepositorio;
import com.oregano.oreganoERP.exceptions.InvalidLoginException;
import com.oregano.oreganoERP.model.Empresa;
import com.oregano.oreganoERP.model.Usuario;
import com.oregano.oreganoERP.ui.windows.ERPWindow;
import com.oregano.oreganoERP.ui.windows.LoginWindow;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;


@SuppressWarnings("serial")
@SessionScoped
public class OreganoErpApplication extends Application implements
		HttpServletRequestListener {

	
	@Inject @Category("oreganoERP :: Main Application")
	private Logger log;
	@Inject
	private UsuarioRepositorio usuarioRepositorio;
	@Inject
	private EmpresaSingleton empresaSingleton;
	private static ThreadLocal<OreganoErpApplication> currentApplication = new ThreadLocal<OreganoErpApplication>();
	private Usuario usuario;
	private Empresa empresa;

	
	@Override
	public void init() {
		
		setTheme("ReindeerMods");
		setInstance( this );
		setMainWindow(new LoginWindow());
	}

	public void authenticate(String login, String senha) throws InvalidLoginException {
		
//		Usuario usuario = usuarioRepositorio.autenticar(login, senha);
		
		UsernamePasswordToken token = new UsernamePasswordToken(login, senha, false);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
        	log.error("Tentativa de login invalida: ", e);
           throw new InvalidLoginException();
        }
        
		log.info("Login efetuado com sucesso...");
        this.usuario = usuarioRepositorio.peloLogin(login);
        loadProtectedResources();

	}

	private void loadProtectedResources() {
		
		this.empresa = empresaSingleton.dadosDaEmpresa();
		currentApplication.get().setMainWindow(new ERPWindow());
	}
	
	private static void setInstance(OreganoErpApplication application) {
		if (getInstance() == null) { 			
		      currentApplication.set(application); 		
		    } 
		
	}

	public static OreganoErpApplication getInstance() {
		return (OreganoErpApplication) currentApplication.get();
	}

	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		
		OreganoErpApplication.setInstance(this);
		
	}

	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		
		currentApplication.remove();
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}


}
