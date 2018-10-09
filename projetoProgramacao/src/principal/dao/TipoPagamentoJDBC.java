package principal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import principal.conexao.ConexaoUtil;
import principal.model.TipoPagamento;

public class TipoPagamentoJDBC implements TipoPagamentoDAO {

	@Override
	public void inserir(TipoPagamento dado) {
		try {
			String sql = "insert into TipoPagamento(descricao, desconto) values (?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getDescricao());
			statement.setDouble(2, dado.getDesconto());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(TipoPagamento dado) {	
		try {
			String sql = "update TipoPagamento set descricao= ?, desconto= ? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getDescricao());
			statement.setDouble(2, dado.getDesconto());
			statement.setInt(3, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(TipoPagamento dado) {
		try {
			String sql = "delete from TipoPagamento where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TipoPagamento> listar() {
		List<TipoPagamento> tipos = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from TipoPagamento");
			while (rs.next()) {
				TipoPagamento tipo = new TipoPagamento();
				tipo.setCodigo(rs.getInt("codigo"));
				tipo.setDescricao(rs.getString("descricao"));
				tipo.setDesconto(rs.getDouble("desconto"));
				tipos.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipos;

	}

	@Override
	public TipoPagamento buscar(Integer codigo) {
		TipoPagamento tipo = null;
		try {
			String sql = "select * from TipoPagamento where codigo = ?";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				tipo = new TipoPagamento();
				tipo.setCodigo(rs1.getInt("codigo"));
				tipo.setDescricao(rs1.getString("descricao"));
				tipo.setDesconto(rs1.getDouble("desconto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;

	}

}
