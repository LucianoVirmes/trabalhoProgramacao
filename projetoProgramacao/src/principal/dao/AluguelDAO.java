package principal.dao;

import java.util.List;

import principal.model.Aluguel;

public interface AluguelDAO extends GenericDAO<Aluguel>{

	List<Aluguel> alugueisAtivos();
	
}
