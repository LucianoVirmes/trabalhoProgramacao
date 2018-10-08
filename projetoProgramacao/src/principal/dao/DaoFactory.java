package principal.dao;

public interface DaoFactory {

	ClienteDAO clienteDao();
	
	AluguelDAO aluguelDao();
	
	CarroDAO carroDao();
	
	DevolucaoDAO devolucaoDao();
	
	FilialDAO filialDao();
	
	FuncionarioDAO funcionarioDao();
	
	TipoAluguelDAO tipoAluguelDao();
	
	TipoPagamentoDAO tipoPagamentoDao();
	
	ViewDAO viewDao();
	
}
