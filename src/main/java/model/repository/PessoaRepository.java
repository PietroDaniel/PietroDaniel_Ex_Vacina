package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;

public class PessoaRepository implements BaseRepository<Pessoa> {

	public boolean cpfExiste(String cpf) {

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultado = null;
		
		// Define a consulta SQL para contar o número de registros que têm o mesmo CPF
		// fornecido:
		String query = "SELECT COUNT(*) FROM Pessoa WHERE cpf = ?";

		try {
			pstmt = conn.prepareStatement(query);
			
			// Define o valor do primeiro parâmetro da consulta SQL como o CPF fornecido:
			pstmt.setString(1, cpf);
			
			// Executa a consulta e armazena o resultado no ResultSet:
			resultado = pstmt.executeQuery();

			// Verifica se há resultados e retorna true se houver ao menos um registro com o
			// CPF:
			if (resultado.next()) {
				// Se a contagem for maior que 0, significa que o CPF já existe no banco:
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

	public Pessoa salvar(Pessoa novaPessoa) {

		if (cpfExiste(novaPessoa.getCpf())) {
			System.out.println("CPF já utilizado.");
			return null; // Ou lançar uma exceção
		}

		String query = "INSERT INTO Pessoa (nome, cpf, data_Nascimento, tipo, sexo) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setInt(4, novaPessoa.getTipo());
			pstmt.setString(5, novaPessoa.getSexo());

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

	@Override
	public boolean alterar(Pessoa novaPessoa) {

		// Obter a pessoa atual do banco de dados para comparação
		Pessoa pessoaAtual = consultarPorId(novaPessoa.getId());

		if (pessoaAtual != null && !pessoaAtual.getCpf().equals(novaPessoa.getCpf())) {
			if (cpfExiste(novaPessoa.getCpf())) {
				System.out.println("CPF já utilizado.");
				return false; // Ou lançar uma exceção
			}
		}

		boolean alterou = false;
		String query = " UPDATE Pessoa SET nome = ?, cpf = ?, data_Nascimento = ?, tipo = ?, sexo = ? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setInt(4, novaPessoa.getTipo());
			pstmt.setString(5, novaPessoa.getSexo());
			pstmt.setInt(6, novaPessoa.getId());

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

	@Override
	public boolean excluir(int id) {
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

	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

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
				pessoa.setTipo(resultado.getInt("tipo"));
				pessoa.setSexo(resultado.getString("sexo"));
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

	@Override
	public Pessoa consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Pessoa pessoa = new Pessoa();
		String query = " SELECT * FROM Pessoa WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa.setId(Integer.parseInt(resultado.getString("id")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setDataNascimento(resultado.getDate("data_Nascimento").toLocalDate());
				pessoa.setTipo(resultado.getInt("tipo"));
				pessoa.setSexo(resultado.getString("sexo"));
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

}
