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
import principal.model.Carro;

public class CarroJDBC implements CarroDAO{

	@Override
	public void inserir(Carro dado) {
		try {
			String sql = "insert into Carro(marca, modelo, valor, cor, ano, placa, disponivel, dataAquisicao,  codFilial) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getMarca());
			statement.setString(2, dado.getModelo());
			statement.setDouble(3, dado.getValor());
			statement.setString(4, dado.getCor());
			statement.setDate(5, Date.valueOf(dado.getAno()));
			statement.setString(6, dado.getPlaca());
			statement.setBoolean(7, dado.isDisponivel());
			statement.setDate(8, Date.valueOf(dado.getDataDeAquisicao()));
			statement.setInt(9, dado.getFilial().getCodigo());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void alterar(Carro dado) {
		try {
			String sql = "update Carro set marca = ?, modelo= ?, valor= ?, cor=?, "
					+ "ano=?, placa=?, disponivel=?, codFilial = ? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setString(1, dado.getMarca());
			statement.setString(2, dado.getModelo());
			statement.setDouble(3, dado.getValor());
			statement.setString(4, dado.getCor());
			statement.setDate(5, Date.valueOf(dado.getAno()));
			statement.setString(6, dado.getPlaca());
			statement.setBoolean(7, dado.isDisponivel());
			statement.setInt(8, dado.getFilial().getCodigo());
			statement.setInt(9, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void excluir(Carro dado) {
		try {
			String sql = "delete from Carro where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Carro> listar() {
		List<Carro> carros = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from Carro c where c.dataDesapropriacao IS null;");
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setCodigo(rs.getInt("codigo"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
				carro.setValor(rs.getDouble("valor"));
				carro.setCor(rs.getString("cor"));
				Date data = rs.getDate("ano");
				carro.setAno(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				carro.setPlaca(rs.getString("placa"));
				carro.setDisponivel(rs.getBoolean("disponivel"));
				Date data1 = rs.getDate("dataAquisicao");
				carro.setDataDeAquisicao(
						Instant.ofEpochMilli(data1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				Date data2 = rs.getDate("dataDesapropriacao");
				if(data2 != null) {
					carro.setDataDeDesapropriacao(
							Instant.ofEpochMilli(data2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				}
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				carro.setFilial(filialDao.buscar(rs.getInt("codFilial")));
				
				carros.add(carro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carros;

	}

	@Override
	public Carro buscar(Integer codigo) {
		Carro carro = null;
		try {
			String sql = "select * from Carro c where c.dataDesapropriacao IS null and codigo = ?;";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				carro = new Carro();
				carro.setCodigo(rs1.getInt("codigo"));
				carro.setMarca(rs1.getString("marca"));
				carro.setModelo(rs1.getString("modelo"));
				carro.setValor(rs1.getDouble("valor"));
				carro.setCor(rs1.getString("cor"));

				Date data = rs1.getDate("ano");
				carro.setAno(Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				carro.setPlaca(rs1.getString("placa"));

				carro.setDisponivel(rs1.getBoolean("disponivel"));
				Date data1 = rs1.getDate("dataAquisicao");
				carro.setDataDeAquisicao(
						Instant.ofEpochMilli(data1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				Date data2 = rs1.getDate("dataDesapropriacao");
				if(data2 != null) {
					carro.setDataDeDesapropriacao(
							Instant.ofEpochMilli(data2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());					
				}
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				carro.setFilial(filialDao.buscar(rs1.getInt("codFilial")));
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carro;

	}

	@Override
	public List<Carro> carrosDisponiveis() {
		List<Carro> carros = new ArrayList<>();
		try {
			
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from Carro where disponivel = true");
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setCodigo(rs.getInt("codigo"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
				carro.setValor(rs.getDouble("valor"));
				carro.setCor(rs.getString("cor"));
				Date data = rs.getDate("ano");
				carro.setAno(Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				carro.setPlaca(rs.getString("placa"));
				carro.setDisponivel(rs.getBoolean("disponivel"));
				Date data1 = rs.getDate("dataAquisicao");
				carro.setDataDeAquisicao(
						Instant.ofEpochMilli(data1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				Date data2 = rs.getDate("dataDesapropriacao");
				if(data2 != null) {
					carro.setDataDeDesapropriacao(
							Instant.ofEpochMilli(data2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
					
				}
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				carro.setFilial(filialDao.buscar(rs.getInt("codFilial")));
				carros.add(carro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carros;

	}

	@Override
	public void desapropriar(Carro dado) {
		try {
			String sql = "update Carro set dataDesapropriacao = now() where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Carro> listarCarroFilial(Integer idFilial) {
		List<Carro> carros = new ArrayList<>();
		try {
			String sql = "select * from Carro c where c.dataDesapropriacao IS null and disponivel = true and codFilial = ?";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, idFilial);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setCodigo(rs.getInt("codigo"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
				carro.setValor(rs.getDouble("valor"));
				carro.setCor(rs.getString("cor"));
				Date data = rs.getDate("ano");
				carro.setAno(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				carro.setPlaca(rs.getString("placa"));
				carro.setDisponivel(rs.getBoolean("disponivel"));
				Date data1 = rs.getDate("dataAquisicao");
				carro.setDataDeAquisicao(
						Instant.ofEpochMilli(data1.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				Date data2 = rs.getDate("dataDesapropriacao");
				if(data2 != null) {
					carro.setDataDeDesapropriacao(
							Instant.ofEpochMilli(data2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				}
				FilialDAO filialDao = AbstractFactory.get().filialDao();
				carro.setFilial(filialDao.buscar(rs.getInt("codFilial")));
				
				carros.add(carro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carros;

	}


}
