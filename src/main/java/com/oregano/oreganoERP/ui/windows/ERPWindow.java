package com.oregano.oreganoERP.ui.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class ERPWindow extends Window {

	private static final long serialVersionUID = 1L;

	private Window window;

	public ERPWindow() {

		setName("My Vaadin Application");

		Button button = new Button("Click Me");
		button.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				window.addComponent(new Label("Thank you for clicking"));
			}
		});
		window.addComponent(button);
	}
}
