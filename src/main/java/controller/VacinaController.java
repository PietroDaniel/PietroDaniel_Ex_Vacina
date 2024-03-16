package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Vacina;
import model.service.VacinaService;

@Path("/vacina")
public class VacinaController {

	private VacinaService service = new VacinaService();

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean alterarVacina(Vacina novaVacina){
		 return service.alterarVacina(novaVacina);
	}
	
	
	@GET
	@Path("/{id}")
	public Vacina consultarVacinaPorId(@PathParam("id") int id) {
		return service.consultarVacinaPorId(id);
	}
	
	@POST
	@Path("/salvarVacina")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Vacina cadastrar(Vacina novaVacina) {
		return service.salvarVacina(novaVacina);
	}

	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluirVacina(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vacina> consultarTodasAsVacinas() {
		return service.consultarTodasAsVacinas();
	}
	
}
