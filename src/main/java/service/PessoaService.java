package service;

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
		return repository.salvarPessoa(novaPessoa);
	}

	public boolean alterar(Pessoa PessoaEditada) {
		return repository.alterarPessoa(PessoaEditada);
	}

	public boolean excluir(int id) {
		return repository.excluirPessoa(id);
	}

	public Pessoa consultarPorId(int id) {
		return repository.consultarPessoaPorId(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodasAsPessoas();
	}

}
