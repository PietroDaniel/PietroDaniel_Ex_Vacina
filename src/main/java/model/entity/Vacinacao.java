package model.entity;

import java.time.LocalDate;

public class Vacinacao {
	
	private int id;
	private int IdPessoa;
	private Vacina vacina;
	private LocalDate dataAplicacao;
	private int avaliacao;
	
	public Vacinacao(int id, int idPessoa, Vacina vacina, LocalDate dataAplicacao, int avaliacao) {
		super();
		this.id = id;
		IdPessoa = idPessoa;
		this.vacina = vacina;
		this.dataAplicacao = dataAplicacao;
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
		return IdPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		IdPessoa = idPessoa;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
}
