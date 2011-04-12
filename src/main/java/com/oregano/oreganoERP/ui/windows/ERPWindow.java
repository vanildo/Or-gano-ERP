package com.oregano.oreganoERP.ui.windows;

import com.oregano.oreganoERP.ui.OreganoErpApplication;
import com.oregano.oreganoERP.ui.lists.ContatoList;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


public class ERPWindow extends Window {

	private static final long serialVersionUID = 1L;
	private HorizontalSplitPanel conteudoPrincipal = new HorizontalSplitPanel();
	private HorizontalLayout toolbar = new HorizontalLayout();
	

	public ERPWindow() {
		
		setStyleName("blue");
		setName("Oregano ERP :: Aplicação Principal");
		setHeight(100, UNITS_PERCENTAGE);
		
		Button button = new Button("Clique aqui...");
		button.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				addComponent(new Label("Somos a empresa " + OreganoErpApplication.getInstance().getEmpresa().getNome()));
			}
		});
		
		toolbar.addComponent(button);
		toolbar.setHeight(100,Sizeable.UNITS_PERCENTAGE);
		
		conteudoPrincipal.setHeight(100,Sizeable.UNITS_PERCENTAGE);
		conteudoPrincipal.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		conteudoPrincipal.setFirstComponent(new ContatoList());
		conteudoPrincipal.setSecondComponent(new ContatoList());
		
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSizeFull();
		verticalLayout.addComponent(toolbar);
		verticalLayout.addComponent(conteudoPrincipal);
		verticalLayout.setExpandRatio(conteudoPrincipal, 1);
		
		
		addComponent(verticalLayout);
		
//		addComponent(new ContatoForm());
	}
}
