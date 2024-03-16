package model.entity;

import java.time.LocalDate;
import java.util.List;

public class Pessoa {
	
	private int id;
	private String nome;
	private String cpf;
	private int tipo;
	private String sexo;
	private LocalDate dataNascimento;
	private List<Vacinacao> vacinacoes;
	
	public Pessoa(int id, String nome, String cpf, int tipo, String sexo, LocalDate dataNascimento,
			List<Vacinacao> vacinacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.vacinacoes = vacinacoes;
	}

	public Pessoa() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
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

	public List<Vacinacao> getVacinacoes() {
		return vacinacoes;
	}

	public void setVacinacoes(List<Vacinacao> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
	
	
	
}


