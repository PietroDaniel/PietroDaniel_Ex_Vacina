package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.repository.vacinacao.Banco;

public class PaisRepository {

	public Pais cadastrarPais(Pais novoPais) {

		String query = " INSERT INTO pais (nome, sigla) VALUES (?, ?) ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setString(1, novoPais.getNome());
			pstmt.setString(2, novoPais.getSigla());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novoPais.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo País");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novoPais;
	}

	public Pais consultarPaisPorId(int id) {

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Pais pais = null;
		String query = " SELECT * FROM pais WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pais = new Pais();
				pais.setId(Integer.parseInt(resultado.getString("id")));
				pais.setNome(resultado.getString("nome"));
				pais.setSigla(resultado.getString("sigla"));
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar país com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pais;
	}

	public boolean excluirPais(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean alterarPais(Pais entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Pais> consultarTodosOsPaises() {
		// TODO Auto-generated method stub
		return null;
	}

}
