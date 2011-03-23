package com.oregano.oreganoERP.ui.containers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.data.ContatoRepositorio;
import com.oregano.oreganoERP.model.Contato;
import com.vaadin.data.util.BeanItemContainer;


public class ContatoContainer extends BeanItemContainer<Contato> implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	@Category("oreganoERP :: ContatoContainer")
	private Logger logger;

	@EJB
	ContatoRepositorio repositorio;



	public ContatoContainer( ) throws InstantiationException,
	   								  IllegalAccessException {
		super(Contato.class);

	}

	public ContatoContainer listarContatos () {

		List<Contato> contatos = repositorio.todosContatos();
		ContatoContainer container = null;

		try {
			container = new ContatoContainer();

			for (Contato c : contatos) {
				container.addBean(c);
			}

		} catch (InstantiationException e) {
			logger.error("Problema instanciando o contexto inicial", e);
		} catch (IllegalAccessException e) {
			logger.error("Problema de Acesso Ilegal", e);
		}

		return container;
	}

}
