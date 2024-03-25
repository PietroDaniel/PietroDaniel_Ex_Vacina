package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.entity.enums.Categoria;
import model.repository.vacinacao.Banco;

public class PessoaRepository {

	public Pessoa salvarPessoa(Pessoa novaPessoa) {

		if (cpfExiste(novaPessoa.getCpf())) {
			System.out.println("CPF já utilizado.");
			return null;
		}

		String query = "INSERT INTO Pessoa (nome, cpf, data_Nascimento, tipo, sexo, id_pais) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(4, novaPessoa.getTipo().toString());
			pstmt.setString(5, novaPessoa.getSexo());
			pstmt.setInt(6, novaPessoa.getPais().getId());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova Pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaPessoa;
	}

	public boolean alterarPessoa(Pessoa novaPessoa) {

		boolean alterou = false;
		String query = " UPDATE Pessoa SET nome = ?, cpf = ?, data_Nascimento = ?, tipo = ?, sexo = ?, id_pais = ? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(4, novaPessoa.getTipo().toString());
			pstmt.setString(5, novaPessoa.getSexo());
			pstmt.setInt(6, novaPessoa.getPais().getId());
			pstmt.setInt(7, novaPessoa.getId());

			alterou = pstmt.executeUpdate() > 0;

		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	public boolean excluirPessoa(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE id =" + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return excluiu;
	}

	public ArrayList<Pessoa> consultarTodasAsPessoas() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		PaisRepository paisRepository = new PaisRepository();

		ResultSet resultado = null;
		String query = "SELECT * FROM Pessoa";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(Integer.parseInt(resultado.getString("id")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setDataNascimento(resultado.getDate("data_Nascimento").toLocalDate());
				pessoa.setTipo(Categoria.valueOf(resultado.getString("tipo").toUpperCase()));
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setPais(paisRepository.consultarPaisPorId(Integer.parseInt(resultado.getString("id_pais"))));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta de todas as Pessoas");
			System.out.println(erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}

	public Pessoa consultarPessoaPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		PaisRepository paisRepository = new PaisRepository();

		ResultSet resultado = null;
		Pessoa pessoa = null;
		String query = " SELECT * FROM Pessoa WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa = new Pessoa();
				pessoa.setId(Integer.parseInt(resultado.getString("id")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setDataNascimento(resultado.getDate("data_Nascimento").toLocalDate());
				pessoa.setTipo(Categoria.valueOf(resultado.getString("tipo").toUpperCase()));
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setPais(paisRepository.consultarPaisPorId(Integer.parseInt(resultado.getString("id_pais"))));
				if (resultado.getDate("data_Nascimento") != null) {
					pessoa.setDataNascimento(resultado.getDate("data_Nascimento").toLocalDate());
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar pessoa com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}
	
	public boolean cpfExiste(String cpf) {

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultado = null;
		String query = "SELECT COUNT(*) FROM Pessoa WHERE cpf = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cpf);
			resultado = pstmt.executeQuery();

			if (resultado.next()) {
				return resultado.getInt(1) > 0;
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao verificar existência do CPF");
			System.out.println("Erro: " + erro.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return false;
	}


}
