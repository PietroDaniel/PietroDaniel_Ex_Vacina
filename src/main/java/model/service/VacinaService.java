package model.service;

import java.util.List;


import model.entity.Vacina;
import model.repository.VacinaRepository;

public class VacinaService {

    private VacinaRepository repository = new VacinaRepository();

    public Vacina salvarVacina(Vacina novaVacina) {
        return repository.salvarVacina(novaVacina);
    }

    public boolean excluirVacina(int id) {
        return repository.excluirVacina(id);
    }
    
    public List<Vacina> consultarTodasAsVacinas() {
    	return repository.consultarTodasAsVacinas();
    }
    
    public Vacina consultarVacinaPorId(int id) {
    	return repository.consultarVacinaPorId(id);
    }
    
    public boolean alterarVacina(Vacina novaVacina) {
		return repository.alterarVacina(novaVacina);
	}
}
