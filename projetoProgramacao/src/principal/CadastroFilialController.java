package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.FilialDAO;
import principal.model.Filial;

public class CadastroFilialController {

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfCidade;

	@FXML
	private TextField tfUf;

	private Filial filial;

	private FilialDAO filialDao = AbstractFactory.get().filialDao();

	boolean populaFilial() {
		filial = new Filial();
		filial.setNome(tfNome.getText());
		if(tfNome.getText().isEmpty()) {
			return false;
		}
		filial.setCidade(tfCidade.getText());
		if(tfCidade.getText().isEmpty()) {
			return false;
		}
		filial.setUf(tfUf.getText());
		if(tfUf.getText().isEmpty()) {
			return false;
		}
		return true;
	}

	void novaFilial() {
		filial = new Filial();
		tfNome.clear();
		tfUf.clear();
		tfCidade.clear();
	}

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnNovo;

	@FXML
	void cadastrar(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		if(populaFilial()) {
			if (alerta.confirmaAceitar()) {
				filialDao.inserir(filial);
			}					
		}else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
	}

	@FXML
	void novo(ActionEvent event) {
		novaFilial();
	}

}
