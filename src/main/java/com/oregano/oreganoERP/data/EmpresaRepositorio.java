package com.oregano.oreganoERP.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.model.Empresa;

@Stateless
public class EmpresaRepositorio {

	@Inject
	@Category("oreganoERP :: EmpresaRepositorio")
	private Logger log;
	@PersistenceContext
	private EntityManager em;

	public Empresa porId(final Long id) {
		
		log.info("Recuperando os dados da empresa pelo id");

		return em.find(Empresa.class, id);
	}
	
	public Empresa dadosDaEmpresa( ) {
		
		log.info("Recuperando os dados da empresa");

		return em.find(Empresa.class, 1L);
	}
	
}
