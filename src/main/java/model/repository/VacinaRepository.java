package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.entity.enums.Estagio;
import model.repository.vacinacao.Banco;

public class VacinaRepository {

	private PessoaRepository pessoaRepository;
	private PaisRepository paisRepository;
	
	public VacinaRepository() {
		this.pessoaRepository = new PessoaRepository();
		this.paisRepository = new PaisRepository();
	}
	
	public boolean ehPesquisador(int id) {
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultado = null;
		boolean ehPesquisador = false;

		String query = "SELECT tipo FROM pessoa WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			resultado = pstmt.executeQuery();

			if (resultado.next()) {
				String tipo = resultado.getString("tipo");
				ehPesquisador = "PESQUISADOR".equalsIgnoreCase(tipo);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao verificar se a pessoa é um pesquisador");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return ehPesquisador;
	}

	public Vacina salvarVacina(Vacina novaVacina) {

		if (!ehPesquisador(novaVacina.getPesquisadorResponsavel().getId())) {
			
			System.out.println("Pesquisador não encontrado!");
			return null;
		}

		Pais pais = paisRepository.consultarPaisPorId(novaVacina.getPais().getId());
		
		if (pais == null) {
			System.out.println("País não encontrado!");
			return null;
		}

		Connection conn = Banco.getConnection();
	    String query = "INSERT INTO vacina (nome, estagio, data_inicio, id_pesquisador, id_pais) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
	    
	    try {
	        pstmt.setString(1, novaVacina.getNome());
	        pstmt.setString(2, novaVacina.getEstagio().toString());
	        pstmt.setDate(3, Date.valueOf(novaVacina.getDataInicioPesquisa()));
	        pstmt.setInt(4, novaVacina.getPesquisadorResponsavel().getId());
	        pstmt.setInt(5, novaVacina.getPais().getId());
	        pstmt.execute();
	        ResultSet resultado = pstmt.getGeneratedKeys();
	        
	        if (resultado.next()) {
	            novaVacina.setId(resultado.getInt(1));
	        }
	        
	    } catch (SQLException erro) {
	        System.out.println("Erro ao salvar nova Vacina");
	        System.out.println("Erro: " + erro.getMessage());
	        return null;
	        
	    } finally {
	        Banco.closeStatement(pstmt);
	        Banco.closeConnection(conn);
	    }
	    return novaVacina;
	}

	public boolean excluirVacina(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE id =" + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return excluiu;
	}

	public ArrayList<Vacina> consultarTodasAsVacinas() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM vacina";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {

				Vacina vacina = new Vacina();

				vacina.setId(resultado.getInt("id"));
				vacina.setNome(resultado.getString("nome"));

				int idPais = resultado.getInt("id_pais");
				Pais pais = paisRepository.consultarPaisPorId(idPais);
				vacina.setPais(pais);

				int idPesquisador = resultado.getInt("id_pesquisador");
				Pessoa pesquisador = pessoaRepository.consultarPessoaPorId(idPesquisador);
				vacina.setPesquisadorResponsavel(pesquisador);

				vacina.setEstagio(Estagio.valueOf(resultado.getString("estagio").toUpperCase()));
				vacina.setDataInicioPesquisa(resultado.getDate("data_inicio").toLocalDate());

				vacinas.add(vacina);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta de todas as Vacinas");
			System.out.println(erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}

	public Vacina consultarVacinaPorId(int id) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Vacina vacina = new Vacina();
		String query = "SELECT * FROM vacina WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				vacina = new Vacina();
				vacina.setId(resultado.getInt("id"));
				vacina.setNome(resultado.getString("nome"));

				int idPesquisador = resultado.getInt("id_pesquisador");
				Pessoa pesquisador = pessoaRepository.consultarPessoaPorId(idPesquisador);
				vacina.setPesquisadorResponsavel(pesquisador);

				int idPais = resultado.getInt("id_pais");
				Pais pais = paisRepository.consultarPaisPorId(idPais);
				vacina.setPais(pais);

				vacina.setEstagio(Estagio.valueOf(resultado.getString("estagio").toUpperCase()));
				vacina.setDataInicioPesquisa(resultado.getDate("data_inicio").toLocalDate());
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar vacina com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	public boolean alterarVacina(Vacina novaVacina) {
		
		boolean alterou = false;
		String query = " UPDATE vacina " + " SET nome=?, id_pais=?, id_pesquisador=?, "
				+ "       estagio=?, data_inicio=? " + " WHERE id=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPais().getId());
			pstmt.setInt(3, novaVacina.getPesquisadorResponsavel().getId());
			pstmt.setString(4, novaVacina.getEstagio().toString());
			pstmt.setDate(5, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setInt(6, novaVacina.getId());

			alterou = pstmt.executeUpdate() > 0;

		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

}
