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

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO pessoa (nome, cpf, dataNascimento, sexo, categoria) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(4, novaPessoa.getSexo());
			pstmt.setString(5, novaPessoa.getCategoria());
			//pstmt.setInt(5, novaPessoa.getIdPessoa());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaPessoa.setIdPessoa(resultado.getInt(1));
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
	public boolean excluir(int idPessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE idPessoa =" + idPessoa;
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

	@Override
	public boolean alterar(Pessoa novaPessoa) {
		boolean alterou = false;
		String query = " UPDATE pessoa SET nome = ?, cpf = ? ,dataNascimento = ?, sexo = ?, categoria = ?"
				+ " WHERE idPessoa=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			pstmt.setString(1, novaPessoa.getNome());
			pstmt.setString(2, novaPessoa.getCpf());
			pstmt.setDate(3, Date.valueOf(novaPessoa.getDataNascimento()));
			pstmt.setString(3, novaPessoa.getSexo());
			pstmt.setString(4, novaPessoa.getCategoria());
			pstmt.setInt(5, novaPessoa.getIdPessoa());

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
	public Pessoa consultarPorId(int idPessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Pessoa pessoa = new Pessoa();
		String query = " SELECT * FROM carta WHERE idPessoa = " + idPessoa;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("ID")));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setDataNascimento(resultado.getDate("DataNascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("SEXO"));
				pessoa.setCategoria(resultado.getString("Categoria"));
				if (resultado.getDate("DATA_NASCIMENTO") != null) {
					pessoa.setDataNascimento(resultado.getDate("DataNascimento").toLocalDate());
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar carta com id (" + idPessoa + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}

	@Override
	public ArrayList<Pessoa> consultarTodos() {

		ArrayList<Pessoa> Pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(Integer.parseInt(resultado.getString("idPessoa")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setDataNascimento(resultado.getDate("DataNascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCategoria(resultado.getString("Categoria"));
				Pessoas.add(pessoa);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as Pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return Pessoas;
	}
}
