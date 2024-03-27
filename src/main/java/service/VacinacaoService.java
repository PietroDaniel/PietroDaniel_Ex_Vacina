package service;

import java.time.LocalDate;
import java.util.List;

import exception.VacinacaoException;
import model.entity.Vacinacao;
import model.repository.VacinacaoRepository;

public class VacinacaoService {
	
	private static final int NOTA_MAXIMA = 5;
	private VacinacaoRepository repository = new VacinacaoRepository();

	public Vacinacao salvarVacinacao(Vacinacao novaVacinacao) throws VacinacaoException {
		

		if(novaVacinacao.getIdPessoa() == 0 
				|| novaVacinacao.getVacina() == null
				|| novaVacinacao.getVacina().getId() == 0) {
			throw new VacinacaoException("Informe a o id da pessoa e a vacina da aplicação");
		}
		
		novaVacinacao.setDataAplicacao(LocalDate.now());
		
		if(novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}
		
		
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
