package com.oregano.oreganoERP.ui.forms;

import org.jboss.logging.Logger;

import com.oregano.oreganoERP.model.Contato;
import com.oregano.oreganoERP.ui.containers.ContatoContainer;
import com.oregano.oreganoERP.util.ServiceLocator;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;



public class ContatoList extends Table {

	private static final long serialVersionUID = 1L;
	
	ContatoContainer contatoContainer;
	private Logger logger = Logger.getLogger(ContatoList.class);
	

	public ContatoList( ) {
		
		logger.info("entrando no contato list");
		
		contatoContainer = (ContatoContainer)  ServiceLocator.locate("java:module/ContatoContainer");

		setSizeFull();
		logger.info("setando a datasource no contato list");
		setContainerDataSource(contatoContainer.listarContatos());

		setVisibleColumns(new String[] {"nome","email","telefone"});
        setColumnHeaders(new String[] { "Nome", "e-mail", "telefone" });

     // turn on column reordering and collapsing
        setColumnReorderingAllowed(true);
        setColumnCollapsingAllowed(true);

		/*
		 * Make table selectable, react immediatedly to user events, and pass
		 * events to the controller (our main application)
		 */
		setSelectable(true);
		setImmediate(true);
		//addListener((ValueChangeListener) app);
		/* We don't want to allow users to de-select a row */
		setNullSelectionAllowed(false);

		// customize email column to have mailto: links using column generator
		addGeneratedColumn("email", new ColumnGenerator() {

			private static final long serialVersionUID = 1L;

			public Component generateCell(Table source, Object itemId,
					Object columnId) {
				Contato c = (Contato) itemId;
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + c.getEmail()));
				l.setCaption(c.getEmail());
				return l;
			}
		});

	}

}
