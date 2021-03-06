package com.oregano.oreganoERP.data;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.log.Category;

import com.oregano.oreganoERP.exceptions.InvalidLoginException;
import com.oregano.oreganoERP.model.Usuario;
import com.oregano.oreganoERP.util.UsuarioUtil;

@Stateless
public class UsuarioRepositorio {

	@Inject
	@Category("oreganoERP :: UsuarioRepositorio")
	private Logger log;
	@PersistenceContext
	private EntityManager em;
	@EJB
	UsuarioUtil usuarioUtil;

	public Usuario autenticar(final String login, final String senha) throws InvalidLoginException {

		try {

			Usuario usuario = (Usuario) em.createQuery(
					"from Usuario u where u.login='" + login + "'")
					.getSingleResult();

			String password = usuarioUtil.generateSHA1Hash(senha);

			if (usuario.getSenha().equals(password)) {
				return usuario;
			}

		} catch (NoResultException e) {
			log.info("****** Login invalido para o usuario " + login);
			throw new InvalidLoginException();
		}

		return null;
	}
	
	public Usuario porId (final Long id) {
		
		return em.find(Usuario.class, id);
	}
	
	public Usuario peloLogin (final String login) {
		
		Usuario usuario;
		try {

			usuario = (Usuario) em.createQuery(
					"from Usuario u where u.login='" + login + "'")
					.getSingleResult();

		} catch (NoResultException e) {
			log.info("****** Usuario nao encontrado pelo Login: " + login);
			return null;
		}

		return usuario;
	}
}
