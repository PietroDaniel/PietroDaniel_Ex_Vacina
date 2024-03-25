package service;

import java.util.List;

import model.entity.Vacinacao;
import model.repository.VacinacaoRepository;

public class VacinacaoService {

	private VacinacaoRepository repository = new VacinacaoRepository();

	public Vacinacao salvarVacinacao(Vacinacao novaVacinacao) {
		return repository.salvarVacinacao(novaVacinacao);
	}

	public boolean alterarVacinacao(Vacinacao vacinacaoEditada) {
		return repository.alterarVacinacao(vacinacaoEditada);
	}

	public boolean excluirVacinacao(int id) {
		return repository.excluirVacinacao(id);
	}

	public Vacinacao consultarVacinacaoPorId(int id) {
		return repository.consultarVacinacaoPorId(id);
	}

	public List<Vacinacao> consultarTodasAsVacinacoes() {
		return repository.consultarTodasAsVacinacoes();
	}
}
