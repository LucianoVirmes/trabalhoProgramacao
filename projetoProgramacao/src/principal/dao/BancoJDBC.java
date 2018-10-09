package principal.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import principal.conexao.ConexaoUtil;
import principal.model.Banco;

public class BancoJDBC implements BancoDAO  {
	
	@Override
	public List<Banco> viewControleFuncionario() {
		List<Banco> views = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select nome, sobrenome, cpf, data_admissao,"
					+ "nome_filial,uf, ultima_modificacao from controle_de_funcionarios;");
			while (rs.next()) {
				Banco view = new Banco();
				view.setString1(rs.getString("nome"));
				view.setString2(rs.getString("sobrenome"));
				view.setString3(rs.getString("cpf"));
				Date data = rs.getDate("data_admissao");
				view.setDataInicio(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				view.setNomeFilial("nome_filial");
				view.setUf(rs.getString("uf"));
				data = rs.getDate("ultima_modificacao");
				view.setUltimaModificacao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				views.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return views;

	}
	
	@Override
	public List<Banco> viewAquisicaoVeiculos() {
		List<Banco> views = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select marca, modelo, placa, data_aquisicao,"
					+ " nome_filial, uf, ultima_modificacao from aquisicao_veiculo;");
			while (rs.next()) {
				Banco view = new Banco();
				view.setString1(rs.getString("marca"));
				view.setString2(rs.getString("modelo"));
				view.setString3(rs.getString("placa"));
				Date data = rs.getDate("data_aquisicao");
				view.setDataInicio(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				view.setNomeFilial("nome_filial");
				view.setUf(rs.getString("uf"));
				data = rs.getDate("ultima_modificacao");
				view.setUltimaModificacao(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				views.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return views;

	}
	
	@Override
	public void reajusta_taxa(Double reajuste) {
		try {
			String sql = "call reajusta_taxa(?);";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setDouble(1, reajuste);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer codigoCarroMaisAlugado() {
		Integer codigo = null;
		try {
			CallableStatement statement = ConexaoUtil.getConn().prepareCall("{ ? = call carro_mais_alugado()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				codigo = rs.getInt(1);
			}else {
				codigo = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigo;
		
	}
	
	
	
}
