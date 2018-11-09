package principal.dao;

import principal.model.Carro;

import java.util.List;

public interface CarroDAO extends GenericDAO<Carro> {

	List<Carro> carrosDisponiveis();
	
	void desapropriar(Carro dado);

	List<Carro> listarCarroFilial(Integer idFilial);
}
