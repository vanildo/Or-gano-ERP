package com.oregano.oreganoERP.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Perfil {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String nome;
    private String descricao;
    private Set<String> permissoes;
    private Integer version;
    
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String name) {
		this.nome = name;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String description) {
		this.descricao = description;
	}
	
	@ElementCollection
	@Column(name="permissao")
	public Set<String> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(Set<String> permissoes) {
		this.permissoes = permissoes;
	}
	
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
}
