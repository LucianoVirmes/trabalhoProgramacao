package principal.dao;

import principal.model.Funcionario;

public interface FuncionarioDAO extends GenericDAO<Funcionario>{

	void demitirFuncionario(Funcionario dado);
	
	Funcionario verificaEmail(String email);
	
}
