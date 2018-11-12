package principal.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import principal.conexao.ConexaoUtil;
import principal.model.Cliente;

public class ClienteJDBC implements ClienteDAO {

	@Override
	public void inserir(Cliente dado) {
		try {
			String sql = "insert into Cliente(nome, sobrenome, dataNascimento, telefone, cpf, email, dataCadastro, cnh) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getNome());
			statement.setString(2, dado.getSobrenome());
			statement.setDate(3, Date.valueOf(dado.getDataNascimento()));
			statement.setString(4, dado.getTelefone());
			statement.setString(5, dado.getCpf());
			statement.setString(6, dado.getEmail());
			statement.setDate(7, Date.valueOf(dado.getDataDeCadastro()));
			statement.setString(8, dado.getCnh());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void alterar(Cliente dado) {
		try {
			String sql = "update Cliente set nome = ?, sobrenome= ?, dataNascimento= ?, telefone=?, "
					+ "cpf=?, email=?, dataCadastro=?, cnh = ? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getNome());
			statement.setString(2, dado.getSobrenome());
			statement.setDate(3, Date.valueOf(dado.getDataNascimento()));
			statement.setString(4, dado.getTelefone());
			statement.setString(5, dado.getCpf());
			statement.setString(6, dado.getEmail());
			statement.setDate(7, Date.valueOf(dado.getDataDeCadastro()));
			statement.setString(8, dado.getCnh());
			statement.setInt(9, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void excluir(Cliente dado) {
		try {
			String sql = "delete from Cliente where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from Cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSobrenome(rs.getString("sobrenome"));
				Date data = rs.getDate("dataNascimento");
				cliente.setDataNascimento(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				data = rs.getDate("dataCadastro");
				cliente.setDataDeCadastro(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				cliente.setCnh(rs.getString("cnh"));
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;

	}

	@Override
	public Cliente buscar(Integer codigo) {
		Cliente cliente = null;
		try {
			String sql = "select * from Cliente where codigo = ?";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				cliente = new Cliente();
				cliente.setCodigo(rs1.getInt("codigo"));
				cliente.setNome(rs1.getString("nome"));
				cliente.setSobrenome("sobrenome");
				Date data = rs1.getDate("dataNascimento");
				cliente.setDataNascimento(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				cliente.setTelefone(rs1.getString("telefone"));
				cliente.setCpf(rs1.getString("cpf"));
				cliente.setEmail(rs1.getString("email"));

				data = rs1.getDate("dataCadastro");
				cliente.setDataDeCadastro(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				cliente.setCnh(String.valueOf(rs1.getInt("cnh")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;

	}

}