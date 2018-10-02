package principal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import principal.dao.AbstractFactory;
import principal.dao.CarroDAO;
import principal.dao.FilialDAO;
import principal.model.Carro;
import principal.model.Filial;

public class GerenciarVeiculoController {

	@FXML
    private CheckBox ckbDisponivel;

    @FXML
    private ComboBox<Filial> cbFilial;
	
    @FXML
    private Button btnDesapropriar;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<Carro> tblVeiculos;

    @FXML
    private TableColumn<Carro, String> tbcPlaca;

    @FXML
    private TableColumn<Carro, String> tbcMarca;

    @FXML
    private TableColumn<Carro, String> tbcModelo;

    @FXML
    private TableColumn<Carro, Double> tbcValor;

    @FXML
    private TableColumn<Carro, Boolean> tbcDisponibilidade;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfCor;

    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfValor;

    @FXML
    private DatePicker dtpAno;

    private Carro carro;
    
    private CarroDAO carroDao = AbstractFactory.get().carroDao();
    private FilialDAO filialDao = AbstractFactory.get().filialDao();
    
    private ObservableList<Carro> carros = FXCollections.observableArrayList();
    
    @FXML
	private void initialize() {
		tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
		tbcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tbcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tbcDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("disponivel"));
		tblVeiculos.setItems(atualizaTabela());
		populaCombo();
	}
    
	private ObservableList<Carro> atualizaTabela() {
		carros = FXCollections.observableArrayList(carroDao.listar());
		return carros;
	}
	
	private ObservableList<Carro> buscarVeiculo() {
		ObservableList<Carro> carroPesquisa = FXCollections.observableArrayList();
		for (int x = 0; x < carros.size(); x++) {
			if (carros.get(x).getPlaca().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				carroPesquisa.add(carros.get(x));
			}
		}
		return carroPesquisa;
	}
	
	public void populaTela(Carro car) {
		tfMarca.setText(car.getMarca());
		tfModelo.setText(car.getModelo());
		tfCor.setText(car.getCor());
		tfPlaca.setText(car.getPlaca());
		tfValor.setText(car.getValor().toString());
		dtpAno.setValue(car.getAno());
		ckbDisponivel.setVisible(car.isDisponivel());
		cbFilial.getSelectionModel().select(car.getFilial());
		
	}

	public void populaFuncionario() {
		carro.setAno(dtpAno.getValue());
		carro.setCor(tfCor.getText());
		if(ckbDisponivel.isArmed()) {
			carro.setDisponivel(true);
		}else {
			carro.setDisponivel(false);			
		}
		carro.setMarca(tfMarca.getText());
		carro.setModelo(tfModelo.getText());
		carro.setPlaca(tfPlaca.getText());
		carro.setValor(Double.valueOf(tfValor.getText()));
		carro.setFilial(cbFilial.getValue());
	}


    
    
    @FXML
    void buscar(ActionEvent event) {
    	tblVeiculos.setItems(buscarVeiculo());
    }

    @FXML
    void desapropriar(ActionEvent event) {
    	AlertaFactory alerta = new AlertaFactory();
		populaFuncionario();
		if(alerta.confirmaExclusao()) {
			carroDao.desapropriar(carro);
		}
    }

    @FXML
    void salvar(ActionEvent event) {
    	populaFuncionario();
		AlertaFactory alerta = new AlertaFactory();
		if(alerta.confirmaAceitar()) {
			carroDao.alterar(carro);
			tblVeiculos.refresh();
		}
    }

    @FXML
    void selecionaCarro(MouseEvent event) {
    	if (tblVeiculos.getSelectionModel().getSelectedItem() != null) {
			carro = tblVeiculos.getSelectionModel().getSelectedItem();
			populaTela(carro);
		}
    }
    
    private void populaCombo(){
		for(Filial filial: filialDao.listar()){
			cbFilial.getItems().add(filial);
		}
	}
}
