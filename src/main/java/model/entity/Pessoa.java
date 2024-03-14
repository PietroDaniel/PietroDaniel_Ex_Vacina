package model.entity;

import java.time.LocalDate;

public class Pessoa {
	
	private int idPessoa;
	private String nome;
	private String sexo;
	private String cpf;
	private LocalDate dataNascimento;
	private String categoria;
	
	
	public Pessoa(int idPessoa, String nome, String sexo, String cpf, LocalDate dataNascimento, String categoria) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.categoria = categoria;
	}
	
	public Pessoa() {
		super();
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
}


