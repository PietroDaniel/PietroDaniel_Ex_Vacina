package model.service;

import java.util.List;

import exception.PessoaException;
import model.entity.Pessoa;
import model.repository.PessoaRepository;


public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();

	public Pessoa salvar(Pessoa novaPessoa) throws PessoaException {
		
		return repository.salvar(novaPessoa);
/*		if (validarPessoa (novaPessoa)) {
		} else {
			throw new PessoaException ("Erro ao tentar criar um novo registro");
		}*/
	}

	public boolean atualizar(Pessoa PessoaEditada) {
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
	
	public boolean validarPessoa(Pessoa pessoa) {
		boolean valido = false;	
		if(pessoa.getNome().equalsIgnoreCase(null) || pessoa.getNome().equalsIgnoreCase("")) {
		} else if(pessoa.getCategoria().equalsIgnoreCase(null) || pessoa.getCategoria().equalsIgnoreCase("")) {
		} else if(pessoa.getSexo().equalsIgnoreCase(null) || pessoa.getSexo().equalsIgnoreCase("")) {
		} else if(pessoa.getCpf().equalsIgnoreCase(null) || pessoa.getCpf().equalsIgnoreCase("")) {
		} else if(pessoa.getDataNascimento().equals(null)) {
		} else {
			valido = true;
		}
		
		return valido;
	}


}
