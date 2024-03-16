package model.entity;

import java.time.LocalDate;

public class Vacinacao {
	
	private int id;
	private int idPessoa;
	private Vacina vacina;
	private LocalDate data;
	private int avaliacao;
	
	public Vacinacao(int id, int idPessoa, Vacina vacina, LocalDate data, int avaliacao) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.vacina = vacina;
		this.data = data;
		this.avaliacao = avaliacao;
	}

	public Vacinacao() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
}
