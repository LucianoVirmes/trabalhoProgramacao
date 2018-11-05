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
import principal.model.Aluguel;

public class AluguelJDBC implements AluguelDAO {

	@Override
	public void inserir(Aluguel dado) {
		try {
			String sql = "insert into Aluguel (dataAluguel, quilometroSaida, codTipoAluguel, codCliente, codCarro, "
					+ "codFuncionario, codFilial) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setDate(1, Date.valueOf(dado.getDataAluguel()));
			statement.setDouble(2, dado.getQuilometrosSaida());
			statement.setInt(3, dado.getTipoAluguel().getCodigo());
			statement.setInt(4, dado.getCliente().getCodigo());
			statement.setInt(5, dado.getCarro().getCodigo());
			statement.setInt(6, dado.getFuncionario().getCodigo());
			statement.setInt(7, dado.getFilial().getCodigo());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Aluguel dado) {
		try {
			String sql = "update Aluguel set dataAluguel = ?, quilometroSaida= ?, codTipoAluguel= ?, codCliente=?, "
					+ "codCarro=?, codFuncionario=?, codFilial=? where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);

			statement.setDate(1, Date.valueOf(dado.getDataAluguel()));
			statement.setDouble(2, dado.getQuilometrosSaida());
			statement.setInt(3, dado.getTipoAluguel().getCodigo());
			statement.setInt(4, dado.getCliente().getCodigo());
			statement.setInt(5, dado.getCarro().getCodigo());
			statement.setInt(6, dado.getFuncionario().getCodigo());
			statement.setInt(7, dado.getFilial().getCodigo());
			statement.setInt(8, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(Aluguel dado) {
		try {
			String sql = "delete from aluguel where codigo = ?";
			PreparedStatement statement = ConexaoUtil.getConn().prepareStatement(sql);
			statement.setInt(1, dado.getCodigo());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Aluguel> listar() {
		List<Aluguel> alugueis = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from Aluguel");
			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setCodigo(rs.getInt("codigo"));

				Date data = rs.getDate("dataAluguel");
				aluguel.setDataAluguel(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				TipoAluguelJDBC tipoAluguelJDBC = new TipoAluguelJDBC();
				aluguel.setTipoAluguel(tipoAluguelJDBC.buscar(rs.getInt("codTipoAluguel")));
				ClienteJDBC clienteJDBC = new ClienteJDBC();
				aluguel.setCliente(clienteJDBC.buscar(rs.getInt("codCliente")));
				CarroJDBC carroJDBC = new CarroJDBC();
				aluguel.setCarro(carroJDBC.buscar(rs.getInt("codCarro")));
				FuncionarioJDBC funcionarioJDBC = new FuncionarioJDBC(); 
				aluguel.setFuncionario(funcionarioJDBC.buscar(rs.getInt("codFuncionario")));
				FilialJDBC filialJDBC = new FilialJDBC();
				aluguel.setFilial(filialJDBC.buscar(rs.getInt("codFilial")));

				alugueis.add(aluguel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alugueis;

	}

	
	@Override
	public Aluguel buscar(Integer codigo) {
		Aluguel aluguel = null;
		try {
			String sql = "select * from Aluguel where codigo = ?";
			PreparedStatement ps = ConexaoUtil.getConn().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				aluguel = new Aluguel();
				aluguel.setCodigo(rs1.getInt("codigo"));
				Date data = rs1.getDate("dataAluguel");
				aluguel.setDataAluguel(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
				aluguel.setQuilometrosSaida(rs1.getDouble("quilometroSaida"));
				TipoAluguelJDBC tipoAluguelJDBC = new TipoAluguelJDBC();
				aluguel.setTipoAluguel(tipoAluguelJDBC.buscar(rs1.getInt("codTipoAluguel")));
				ClienteJDBC clienteJDBC = new ClienteJDBC();
				aluguel.setCliente(clienteJDBC.buscar(rs1.getInt("codCliente")));
				CarroJDBC carroJDBC = new CarroJDBC();
				aluguel.setCarro(carroJDBC.buscar(rs1.getInt("codCarro")));
				FuncionarioJDBC funcionarioJDBC = new FuncionarioJDBC(); 
				aluguel.setFuncionario(funcionarioJDBC.buscar(rs1.getInt("codFuncionario")));
				FilialJDBC filialJDBC = new FilialJDBC();
				aluguel.setFilial(filialJDBC.buscar(rs1.getInt("codFilial")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aluguel;

	}

	@Override
	public List<Aluguel> alugueisAtivos() {
		List<Aluguel> alugueis = new ArrayList<>();
		try {
			Statement statement = ConexaoUtil.getConn().createStatement();
			ResultSet rs = statement.executeQuery("select * from Aluguel a join Carro c on a.codCarro = c.codigo\n" + 
					"where c.disponivel is false;");
			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setCodigo(rs.getInt("codigo"));

				Date data = rs.getDate("dataAluguel");
				aluguel.setDataAluguel(
						Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());

				TipoAluguelJDBC tipoAluguelJDBC = new TipoAluguelJDBC();
				aluguel.setTipoAluguel(tipoAluguelJDBC.buscar(rs.getInt("codTipoAluguel")));
				ClienteJDBC clienteJDBC = new ClienteJDBC();
				aluguel.setCliente(clienteJDBC.buscar(rs.getInt("codCliente")));
				CarroJDBC carroJDBC = new CarroJDBC();
				aluguel.setCarro(carroJDBC.buscar(rs.getInt("codCarro")));
				FuncionarioJDBC funcionarioJDBC = new FuncionarioJDBC(); 
				aluguel.setFuncionario(funcionarioJDBC.buscar(rs.getInt("codFuncionario")));
				FilialJDBC filialJDBC = new FilialJDBC();
				aluguel.setFilial(filialJDBC.buscar(rs.getInt("codFilial")));

				alugueis.add(aluguel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alugueis;


	}


}
