package com.oregano.oreganoERP.ui;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oregano.oreganoERP.model.Usuario;
import com.oregano.oreganoERP.ui.windows.ERPWindow;
import com.oregano.oreganoERP.ui.windows.LoginWindow;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;


@SuppressWarnings("serial")
@SessionScoped
public class OreganoErpApplication extends Application implements
		HttpServletRequestListener {

	private static ThreadLocal<OreganoErpApplication> currentApplication = new ThreadLocal<OreganoErpApplication>();
	private Usuario usuario;

	
	@Override
	public void init() {
		
		setInstance( this );
		setMainWindow(new LoginWindow());
	}

	//TODO implementar a rotina de autenticacao real e criar um exceção para a falha de login
	public void authenticate(String login, String password) throws Exception {

		if ( !"user".equals ( login ) && !"qwerty".equals ( password )) {
            throw new Exception ("Login failed !");
        }
        
        this.usuario = new Usuario();
        loadProtectedResources();

	}

	private void loadProtectedResources() {
		
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


}
