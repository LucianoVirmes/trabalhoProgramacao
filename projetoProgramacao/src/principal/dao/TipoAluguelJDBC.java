package principal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import principal.conexao.ConexaoUtil;
import principal.model.TipoAluguel;

public class TipoAluguelJDBC implements TipoAluguelDAO{

	@Override
	public void inserir(TipoAluguel dado) {
		try {
			String sql = "insert into TipoAluguel(descricao, valor, taxa) values (?,?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getDescricao());
			statement.setDouble(2, dado.getValor());
			statement.setDouble(3, dado.getTaxa());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(TipoAluguel dado) {
		try {
			String sql = "update TipoAluguel set descricao= ?, valor= ?, taxa= ? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getDescricao());
			statement.setDouble(2, dado.getValor());
			statement.setDouble(3, dado.getTaxa());
			statement.setInt(4, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(TipoAluguel dado) {
		try {
			String sql = "update TipoAluguel set inativo = false where codigo = ?;";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TipoAluguel> listar() {
		List<TipoAluguel> tipos = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from TipoAluguel where inativo = true;");
			while (rs.next()) {
				TipoAluguel tipo = new TipoAluguel();
				tipo.setCodigo(rs.getInt("codigo"));
				tipo.setDescricao(rs.getString("descricao"));
				tipo.setValor(rs.getDouble("valor"));
				tipo.setTaxa(rs.getDouble("taxa"));
				tipos.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipos;

	}

	@Override
	public TipoAluguel buscar(Integer codigo) {
		TipoAluguel tipo = null;
		try {
			String sql = "select * from TipoAluguel where codigo = ? and inativo = true";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				tipo = new TipoAluguel();
				tipo.setCodigo(rs1.getInt("codigo"));
				tipo.setDescricao(rs1.getString("descricao"));
				tipo.setValor(rs1.getDouble("valor"));
				tipo.setTaxa(rs1.getDouble("taxa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;

	}


}
