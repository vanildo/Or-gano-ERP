package com.oregano.oreganoERP.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.model.Contato;

@Stateless
public class ContatoRepositorio {
	

	@Inject
	@Category("oreganoERP :: ContatoRepositorio")
	private Logger log;
	@PersistenceContext
	private EntityManager em;
	

	public Contato findById(Long id) {

		return em.find(Contato.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Contato> todosContatos() {

		return em.createQuery("select c from Contato c").getResultList();
	}

	public Contato save(Contato contato) {

		log.info("Dentro do repositorio, guardando " + contato.getNome());
		em.persist(contato);

		return contato;
	}

	public void update(Contato contato) {

		Contato c = em.merge(contato);
		em.persist(c);

	}
}
