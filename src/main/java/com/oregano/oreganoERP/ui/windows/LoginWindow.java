package com.oregano.oreganoERP.ui.windows;

import com.oregano.oreganoERP.ui.OreganoErpApplication;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class LoginWindow extends Window {

	private static final long serialVersionUID = 1L;

	private Button btnLogin = new Button("Logar");
	private TextField login = new TextField("Login: ");
	private PasswordField password = new PasswordField("Senha: ");

	public LoginWindow() {
		
		super("Por favor autentique-se!");
		
		setStyleName("blue");
		setName("login");
		setHeight(100,UNITS_PERCENTAGE);
		
		btnLogin.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(Button.ClickEvent event) {
				try {
					OreganoErpApplication application = OreganoErpApplication.getInstance();
					application.authenticate( (String) login.getValue(), (String) password.getValue());
					open(new ExternalResource(OreganoErpApplication
							.getInstance().getURL()));
				} catch (Exception e) {
					showNotification(e.toString());
				}
			}
		});
		initUI();
	}

	private void initUI() {
		addComponent(new Label(
				"Por favor realize seu login para pode usar a aplicação."));
		addComponent(new Label());
		addComponent(login);
		addComponent(password);
		addComponent(btnLogin);
	}
}
