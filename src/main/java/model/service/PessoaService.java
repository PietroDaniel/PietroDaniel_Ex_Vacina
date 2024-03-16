package model.service;

import java.util.List;

import exception.PessoaException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;


public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();

	
	public boolean cpfExiste(String cpf) throws PessoaException {
		return repository.cpfExiste(cpf);
	}
	
	public Pessoa salvar(Pessoa novaPessoa) throws PessoaException {
		return repository.salvar(novaPessoa);
	}

	public boolean alterar(Pessoa PessoaEditada) {
		return repository.alterar(PessoaEditada);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public Pessoa consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}
	
	
	
}
