package com.oregano.oreganoERP.data;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.model.Empresa;
import com.oregano.oreganoERP.util.UsuarioUtil;

@Stateless
public class EmpresaRepositorio {

	@Inject
	@Category("oreganoERP :: EmpresaRepositorio")
	private Logger log;
	@PersistenceContext
	private EntityManager em;
	@EJB
	UsuarioUtil usuarioUtil;

	public Empresa porId(Long id) {
		
		log.info("Recuperando os dados da empresa pelo id");

		return (Empresa) em.createQuery("from Empresa e where e.id=" + id)
						   .getSingleResult();
	}
	
	public Empresa dadosDaEmpresa( ) {
		
		log.info("Recuperando os dados da empresa");

		return (Empresa) em.createQuery("from Empresa e where e.id=1")
						   .getSingleResult();
	}
	
}
