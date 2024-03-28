package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import model.entity.enums.Categoria;

public class Pessoa {
	
	private int id;
	private String nome;
	private String cpf;
	private Categoria tipo;
	private String sexo;
	private LocalDate dataNascimento;
	private Pais pais;
	//private ArrayList<Vacinacao> vacinacoes;

	public Pessoa() {
		super();
	}

	public Pessoa(int id, String nome, String cpf, Categoria tipo, String sexo, LocalDate dataNascimento, Pais pais,
			ArrayList<Vacinacao> vacinacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.pais = pais;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Categoria getTipo() {
		return tipo;
	}

	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
