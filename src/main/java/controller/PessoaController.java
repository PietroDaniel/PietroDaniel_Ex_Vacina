package controller;

import java.util.List;

import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Pessoa;
import service.PessoaService;

@Path("/pessoa")
public class PessoaController {

	private PessoaService service = new PessoaService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa novoPessoa) throws PessoaException {
		return service.salvar(novoPessoa);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Pessoa PessoaEditado) throws PessoaException {
		return service.alterar(PessoaEditado);
	}

	@DELETE
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return service.excluir(id);
	}

	@GET
	@Path("/{id}")
	public Pessoa consultarPorId(@PathParam("id") int id) {
		return service.consultarPessoaPorId(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> consultarTodas() {
		return service.consultarTodas();
	}

}
