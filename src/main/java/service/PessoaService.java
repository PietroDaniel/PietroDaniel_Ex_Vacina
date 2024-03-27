package service;

import java.util.List;

import exception.PessoaException;
import exception.VacinacaoException;
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

	public Pessoa consultarPessoaPorId(int id) {
		return repository.consultarPessoaPorId(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodasAsPessoas();
	}

	private void validarCamposObrigatorios(Pessoa p) throws VacinacaoException {
		String mensagemValidacao = "";
		if (p.getNome() == null || p.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (p.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if (p.getCpf() == null || p.getCpf().isEmpty() || p.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF";
		}
		if (p.getSexo() == null || (!p.getSexo().equalsIgnoreCase("F") && !p.getSexo().equalsIgnoreCase("M"))) {
			mensagemValidacao += " - informe o sexo";
		}

		if (!p.getTipo().equals("PESQUISADOR") && !p.getTipo().equals("VOLUNTARIO")
				&& !p.getTipo().equals("PUBLICO_GERAL")) {
			mensagemValidacao += " - informe o tipo";
		}

		if (p.getPais() == null) {
			mensagemValidacao += " - informe o país de origem";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new VacinacaoException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}
}