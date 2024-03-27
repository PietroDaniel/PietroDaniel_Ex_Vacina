package controller;

import java.util.List;

import exception.VacinacaoException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Vacinacao;
import service.VacinacaoService;

@Path("/vacinacao")

public class VacinacaoController {
	
	private VacinacaoService vacinacaoService = new VacinacaoService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacinacao salvarVacinacao(Vacinacao novaVacinacao) throws VacinacaoException {
	    return vacinacaoService.salvarVacinacao(novaVacinacao);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterarVacinacao(Vacinacao vacinacaoEditada) {
	    return vacinacaoService.alterarVacinacao(vacinacaoEditada);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean excluirVacinacao(@PathParam("id") int id){
		 return vacinacaoService.excluirVacinacao(id);
	}
	
	@GET
	@Path("/{id}")
	public Vacinacao consultarVacinacaoPorId(@PathParam("id") int id) {
	    return vacinacaoService.consultarVacinacaoPorId(id);
	}

	@GET
	@Path("/todas")
	public List<Vacinacao> consultarTodasAsVacinacoes() {
	    return vacinacaoService.consultarTodasAsVacinacoes();
	}
}