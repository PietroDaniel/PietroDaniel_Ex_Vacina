package model.entity;

import java.time.LocalDate;


import model.entity.enums.Estagio;

public class Vacina {
	
	private int id;
	private String nome;
	private Pais pais;
	private Pessoa pesquisadorResponsavel;
	private Estagio estagio;
	private LocalDate dataInicioPesquisa;
	
	public Vacina(int id, String nome, Pais pais, Pessoa pesquisadorResponsavel, Estagio estagio,
			LocalDate dataInicioPesquisa) {
		super();
		this.id = id;
		this.nome = nome;
		this.pais = pais;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public Vacina() {
		super();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	
}
