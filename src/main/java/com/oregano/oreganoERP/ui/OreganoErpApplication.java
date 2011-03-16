package com.oregano.oreganoERP.ui;

import javax.enterprise.context.SessionScoped;

import com.oregano.oreganoERP.model.Usuario;
import com.oregano.oreganoERP.ui.windows.ERPWindow;
import com.oregano.oreganoERP.ui.windows.LoginWindow;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@SessionScoped
public class OreganoErpApplication extends Application implements
		ApplicationContext.TransactionListener {

	private static ThreadLocal<OreganoErpApplication> currentApplication = new ThreadLocal<OreganoErpApplication>();
	@SuppressWarnings("unused")
	private Usuario usuario;

	@Override
	public void init() {

		setMainWindow(new LoginWindow());

	}

	//TODO implementar a rotina de autenticacao real
	public void authenticate(String login, String password) throws Exception {
        if ( !"user".equals ( login ) && !password.equals ( "qwerty" ))
        {
            throw new Exception ("Login failed !");
        }
        
        usuario = new Usuario();
        loadProtectedResources();

	}

	private void loadProtectedResources() {

		setMainWindow(new ERPWindow());
	}

	public static OreganoErpApplication getInstance() {
		return currentApplication.get();
	}

	@Override
	public void transactionStart(Application application, Object transactionData) {

		if (application == OreganoErpApplication.this) {
			currentApplication.set(this);
		}

	}

	@Override
	public void transactionEnd(Application application, Object transactionData) {

		if (application == OreganoErpApplication.this) {
			currentApplication.set(null);
			currentApplication.remove();
		}
	}

}
