package principal;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import principal.dao.AbstractFactory;
import principal.dao.AluguelDAO;
import principal.dao.TipoPagamentoDAO;
import principal.model.Aluguel;
import principal.model.Devolucao;
import principal.model.TipoPagamento;

public class DevolverVeiculoController {

	@FXML
	private TableView<Aluguel> tblAluguel;

	@FXML
	private TableColumn<Aluguel, Number> tbcCodigo;

	@FXML
	private TableColumn<Aluguel, String> tbcCarro;

	@FXML
	private TableColumn<Aluguel, String> tbcTipoAluguel;
	
	@FXML
	private TableColumn<Aluguel, String> tbcCliente;

	@FXML
	private TableColumn<Aluguel, String> tbcFuncionario;
	
	@FXML
	private TextField tfPlaca;
	
	@FXML
	private TextField tfKmChegada;

	@FXML
	private Label lblValor;	
	
	@FXML
    private Button btnDevolver;

	@FXML
	private ComboBox<TipoPagamento> cbTipoPagamento;
	@FXML
	private Button btnBuscar;

	private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
	
	private TipoPagamentoDAO tipoDao = AbstractFactory.get().tipoPagamentoDao();
	
	private Devolucao devolucao;

	public boolean populaAluguel() {
		devolucao = new Devolucao();
		
		if(tblAluguel.getSelectionModel().getSelectedItem() == null){
			return false;
		}else {
			devolucao.setAluguel(tblAluguel.getSelectionModel().getSelectedItem());
		}
		if(tfKmChegada.getText().isEmpty()) {
			return false;
		}else {
			devolucao.setQuilometroChegada(Double.valueOf(tfKmChegada.getText()));
		}
		devolucao.setTipoPagamento(cbTipoPagamento.getValue());
		if(!cbTipoPagamento.isArmed()) {
			return false;
		}
		devolucao.setDataChegada(LocalDate.now());
		return true;
	}
	
	
	@FXML
	private void initialize() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcCarro.setCellValueFactory(new PropertyValueFactory<>("carro"));
		tbcTipoAluguel.setCellValueFactory(new PropertyValueFactory<>("tipoAluguel"));
		tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tbcFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
		tblAluguel.setItems(atualizaTabela());
		populaCombo();
	}

	@FXML
	void buscar(ActionEvent event) {
		tblAluguel.setItems(buscarAluguel());
	}

	private ObservableList<Aluguel> buscarAluguel() {
		ObservableList<Aluguel> AluguelPesquisa = FXCollections.observableArrayList();
		for (int x = 0; x < alugueis.size(); x++) {
			if (alugueis.get(x).getCarro().getPlaca().toLowerCase().contains(tfPlaca.getText().toLowerCase())) {
				AluguelPesquisa.add(alugueis.get(x));
			}
		}
		return AluguelPesquisa;
	}

	public ObservableList<Aluguel> atualizaTabela() {
		AluguelDAO aluguelDao = AbstractFactory.get().aluguelDao();
		alugueis = FXCollections.observableArrayList(aluguelDao.listar());
		return alugueis;
	}
	
	private void populaCombo(){
		for(TipoPagamento tipo: tipoDao.listar()){
			cbTipoPagamento.getItems().add(tipo);
		}
	}
	
	@FXML
    void devolverVeiculo(ActionEvent event) {

    }
	
	
}
