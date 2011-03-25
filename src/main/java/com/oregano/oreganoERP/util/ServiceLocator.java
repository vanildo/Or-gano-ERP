package com.oregano.oreganoERP.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	public static Object locate(String nome) {

		InitialContext initialContext;
		Object retorno = null;
		try {
			initialContext = new InitialContext();
			retorno = initialContext.lookup(nome);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return retorno;
	}

}
