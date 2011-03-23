package com.oregano.oreganoERP.ui.windows;

import com.oregano.oreganoERP.ui.forms.ContatoForm;
import com.oregano.oreganoERP.ui.forms.ContatoList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class ERPWindow extends Window {

	private static final long serialVersionUID = 1L;


	public ERPWindow() {

		setName("Oregano ERP :: Aplicação Principal");

		Button button = new Button("Click Me");
		button.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				addComponent(new Label("Thank you for clicking"));
			}
		});
		addComponent(button);
		
		Label label = new Label("Bem-Vindo a agenda");

		Button botao = new Button("Mostrar contatos");

		addComponent(label);
		addComponent(botao);
		addComponent(new ContatoList());
		addComponent(new ContatoForm(null));
	}
}
