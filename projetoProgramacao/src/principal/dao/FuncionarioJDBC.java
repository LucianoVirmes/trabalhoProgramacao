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
import principal.model.Funcionario;

public class FuncionarioJDBC implements FuncionarioDAO {

	@Override
	public void inserir(Funcionario dado) {
		try {
			String sql = "insert into Funcionario(nome, sobrenome, dataNascimento, telefone, cpf, email, senha, salario, dataAdmissao, codFilial) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getNome());
			statement.setString(2, dado.getSobrenome());
			statement.setDate(3, Date.valueOf(dado.getDataNascimento()));
			statement.setString(4, dado.getTelefone());
			statement.setString(5, dado.getCpf());
			statement.setString(6, dado.getEmail());
			statement.setString(7, dado.getSenha());
			statement.setDouble(8, dado.getSalario());
			statement.setDate(9, Date.valueOf(dado.getDataAdmissao()));
			statement.setInt(10, dado.getFilial().getCodigo());
			statement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void alterar(Funcionario dado) {
		try {
			String sql = "update Funcionario set nome = ?, sobrenome= ?, dataNascimento= ?, telefone=?, "
					+ "cpf=?, email=?, senha=?, salario = ?, codFilial =?, dataAdmissao = ?, dataDemissao = ? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getNome());
			statement.setString(2, dado.getSobrenome());
			statement.setDate(3, Date.valueOf(dado.getDataNascimento()));
			statement.setString(4, dado.getTelefone());
			statement.setString(5, dado.getCpf());
			statement.setString(6, dado.getEmail());
			statement.setString(7, dado.getSenha());
			statement.setDouble(8, dado.getSalario());
			statement.setInt(9, dado.getFilial().getCodigo());
			statement.setDate(10, Date.valueOf(dado.getDataAdmissao()));
			statement.setDate(11, Date.valueOf(dado.getDataDemissao()));
			statement.setInt(9, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(Funcionario dado) {
		try {
			String sql = "delete from Funcionario where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
//			ResultSet rs = statement.executeQuery("select * from Funcionario f join ControleFuncionarios cf on f.codigo = cf.codFuncionario " + 
//					"where cf.dataDemissao IS null;");
			ResultSet rs = statement.executeQuery("select * from Funcionario f where f.dataDemissao IS null;");
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(rs.getInt("codigo"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				Date data = rs.getDate("dataNascimento");
				funcionario.setDataNascimento(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setSalario(rs.getDouble("salario"));
				data = rs.getDate("dataAdmissao");
				funcionario.setDataAdmissao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				data = rs.getDate("dataDemissao");
				funcionario.setDataDemissao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				funcionario.setFilial(filialDao.buscar(rs.getInt("codFilial")));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;

	}

	@Override
	public Funcionario buscar(Integer codigo) {
		Funcionario funcionario = null;
		try {
			String sql = "select * from Funcionario f where f.dataDemissao = null and codigo = ?;";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				funcionario = new Funcionario();
				funcionario.setCodigo(rs1.getInt("codigo"));
				funcionario.setNome(rs1.getString("nome"));
				funcionario.setSobrenome(rs1.getString("sobrenome"));
				Date data = rs1.getDate("dataNascimento");
				funcionario.setDataNascimento(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				funcionario.setTelefone(rs1.getString("telefone"));
				funcionario.setCpf(rs1.getString("cpf"));
				funcionario.setEmail(rs1.getString("email"));
				funcionario.setSenha(rs1.getString("senha"));
				funcionario.setSalario(rs1.getDouble("salario"));
				data = rs1.getDate("dataAdmissao");
				funcionario.setDataAdmissao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				data = rs1.getDate("dataDemissao");
				funcionario.setDataDemissao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				funcionario.setFilial(filialDao.buscar(rs1.getInt("codFilial")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
		
	}

	@Override
	public void demitirFuncionario(Funcionario dado) {
		try {
		//	String sql = "update ControleFuncionarios set dataDemissao = ? where codFuncionario = ?";
			String sql = "update Funcionario set dataDemissao = null where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
		//	statement.setDate(1, Date.valueOf(LocalDate.now()));
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Funcionario verificaEmail(String email) {
		Funcionario funcionario = null;
		try {
			String sql = "select * from Funcionario where f.dataDemissao = null and email = ?;";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				funcionario = new Funcionario();
				funcionario.setCodigo(rs1.getInt("codigo"));
				funcionario.setNome(rs1.getString("nome"));
				funcionario.setSobrenome(rs1.getString("sobrenome"));
				Date data = rs1.getDate("dataNascimento");
				funcionario.setDataNascimento(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				funcionario.setTelefone(rs1.getString("telefone"));
				funcionario.setCpf(rs1.getString("cpf"));
				funcionario.setEmail(rs1.getString("email"));
				funcionario.setSenha(rs1.getString("senha"));
				funcionario.setSalario(rs1.getDouble("salario"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

//	@Override
//	public Integer retornaCodigo(Funcionario funcionario) {
//		for(Funcionario func : listar()) {
//			if(func.getCpf().equals(funcionario.getCpf())) {
//				return func.getCodigo();
//			}
//		}			
//		return null;
//	}

}
