package principal.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import principal.conexao.ConexaoUtil;
import principal.model.View;

public class BancoJDBC implements BancoDAO  {
	
	public List<View> viewControleFuncionario() {
		List<View> views = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select nome, sobrenome, cpf, data_admissao,"
					+ "nome_filial,uf, ultima_modificacao from controle_de_funcionarios;");
			while (rs.next()) {
				View view = new View();
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
	
	
	public List<View> viewAquisicaoVeiculos() {
		List<View> views = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select marca, modelo, placa, data_aquisicao,"
					+ " nome_filial, uf, ultima_modificacao from aquisicao_veiculo;");
			while (rs.next()) {
				View view = new View();
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

	

}
