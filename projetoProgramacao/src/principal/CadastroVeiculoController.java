package principal;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.CarroDAO;
import principal.dao.FilialDAO;
import principal.model.Carro;
import principal.model.Filial;

public class CadastroVeiculoController {


	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfModelo;

	@FXML
	private TextField tfValor;

	@FXML
	private TextField tfCor;

	@FXML
	private DatePicker dtAno;

	@FXML
	private TextField tfPlaca;

	@FXML
	private CheckBox cbkDisponivel;

	@FXML
	private ComboBox<Filial> cbFilial;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnNovo;

	private Carro carro;
	private CarroDAO carroDao = AbstractFactory.get().carroDao();
	private FilialDAO filialDao = AbstractFactory.get().filialDao();

	@FXML
	private void initialize() {
		populaCombo();
	}

	private void populaCombo() {
		for (Filial filial : filialDao.listar()) {
			cbFilial.getItems().add(filial);
		}
	}

	@FXML
	void cadastrar(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		if(populaCarro()) {
			if(alerta.confirmaAceitar()) {
				carroDao.inserir(carro);
				novoCarro();
			}			
		}else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
	}

	@FXML
	void novo(ActionEvent event) {
		novoCarro();
	}

	/**
	 * popula carro com as entradas do usuario
	 */
	public boolean populaCarro() {
		carro = new Carro();
		carro.setAno(dtAno.getValue());
		if(!dtAno.hasProperties()) {
			return false;
		}
		carro.setCor(tfCor.getText());
		if(tfCor.getText().isEmpty()) {
			return false;
		}
		if(cbkDisponivel.isSelected()) {
			carro.setDisponivel(true);
		}else {
			carro.setDisponivel(false);
		}
		carro.setMarca(tfMarca.getText());
		if(tfMarca.getText().isEmpty()) {
			return false;
		}
		carro.setModelo(tfModelo.getText());
		if(tfModelo.getText().isEmpty()) {
			return false;
		}
		carro.setPlaca(tfPlaca.getText());
		if(tfPlaca.getText().isEmpty()) {
			return false;
		}
		carro.setDataDeAquisicao(LocalDate.now());
		carro.setDataDeDesapropriacao(null);
		carro.setFilial(cbFilial.getValue());
		if(!cbFilial.hasProperties()) {
			return false;
		}
		if(tfValor.getText().isEmpty()) {
			return false;
		}else {
			carro.setValor(Double.valueOf(tfValor.getText()));
		}	
		return true;
	}

	public void novoCarro() {
		carro = new Carro();
		tfCor.clear();
		tfMarca.clear();
		tfModelo.clear();
		tfPlaca.clear();
		tfValor.clear();
		if (cbkDisponivel.isSelected()) {
			cbkDisponivel.setSelected(false);
			;
		}
		cbFilial.getSelectionModel().clearSelection();
	}

}
