package com.oregano.oreganoERP.data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.oregano.oreganoERP.model.Empresa;

@Startup
@Singleton
public class EmpresaSingleton {

	@EJB
	EmpresaRepositorio empresaRepositorio;
	private Empresa empresa;
	
	@PostConstruct @Lock(LockType.WRITE)
	public void carregarDadosAtualizados () {
		this.empresa = empresaRepositorio.dadosDaEmpresa();
	}
	
	@Lock(LockType.READ)
	public Empresa dadosDaEmpresa () {
		return empresa;
	}
}
