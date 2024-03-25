package service;

import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {
	
	private PaisRepository paisRepository = new PaisRepository();
	
	public Pais cadastrarPais(Pais novoPais) {
		return paisRepository.cadastrarPais(novoPais);
	}
	
	public Pais consultarPaisPorId (int id) {
		return paisRepository.consultarPaisPorId(id);
	}

}
