package principal.dao;

public class JDBCFactory implements DaoFactory{

	@Override
	public ClienteDAO clienteDao() {
		return new ClienteJDBC();
	}

	@Override
	public AluguelDAO aluguelDao() {
		return new AluguelJDBC();
	}

	@Override
	public CarroDAO carroDao() {
		return new CarroJDBC();
	}

	@Override
	public DevolucaoDAO devolucaoDao() {
		return new DevolucaoJDBC();
	}

	@Override
	public FilialDAO filialDao() {
		return new FilialJDBC();
	}

	@Override
	public FuncionarioDAO funcionarioDao() {
		return new FuncionarioJDBC();
	}

	@Override
	public TipoAluguelDAO tipoAluguelDao() {
		return new TipoAluguelJDBC();
	}

	@Override
	public TipoPagamentoDAO tipoPagamentoDao() {
		return new TipoPagamentoJDBC();
	}

	@Override
	public ViewDAO viewDao() {
		return new ViewJDBC();
	}

}
