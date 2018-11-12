package principal.dao;

import java.util.List;

import principal.model.Banco;

public interface BancoDAO {

	List<Banco> viewControleFuncionario();
	List<Banco> viewAquisicaoVeiculos();
	void reajusta_taxa(Double reajuste);
	Integer codigoCarroMaisBarato(Integer codFilial);
}
