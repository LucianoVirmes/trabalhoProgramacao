package principal.dao;

import java.util.List;

import principal.model.Funcionario;

public interface FuncionarioDAO extends GenericDAO<Funcionario>{

	void demitirFuncionario(Funcionario dado);
	
	Funcionario verificaEmail(String email);

	List<Funcionario> listarFuncionarioFilial(Integer codigo);
	
}
