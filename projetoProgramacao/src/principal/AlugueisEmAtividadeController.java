package principal;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import principal.dao.AbstractFactory;
import principal.dao.AluguelDAO;
import principal.model.Aluguel;
import principal.model.Carro;
import principal.model.Cliente;
import principal.model.TipoAluguel;

public class AlugueisEmAtividadeController {

	@FXML
	private TextField tfPlaca;

	@FXML
	private Button tbnBuscar;

	@FXML
	private TableView<Aluguel> tblAlugueis;

	@FXML
	private TableColumn<Aluguel, Number> tbcCodigo;

	@FXML
	private TableColumn<Aluguel, Carro> tbcPlaca;

	@FXML
	private TableColumn<Aluguel, LocalDate> tbcData;

	@FXML
	private TableColumn<Aluguel, TipoAluguel> tbcTipo;

	@FXML
	private TableColumn<Aluguel, Cliente> tbcCliente;

	@FXML
	private TableColumn<Aluguel, Number> tbcSaida;
	
	private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
	
	

	@FXML
	private void initialize() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcPlaca.setCellValueFactory(new PropertyValueFactory<>("carro"));
		tbcData.setCellValueFactory(new PropertyValueFactory<>("dataAluguel"));
		tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoAluguel"));
		tbcCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tbcSaida.setCellValueFactory(new PropertyValueFactory<>("quilometrosSaida"));
		tblAlugueis.setItems(atualizaTabela());
	}
	
	private ObservableList<Aluguel> buscarAluguel() {
		ObservableList<Aluguel> aluguelPesquisa = FXCollections.observableArrayList();
		for(int x =0; x<alugueis.size(); x++) {
			if(alugueis.get(x).getCarro().getPlaca().toLowerCase().contains(tfPlaca.getText().toLowerCase())) {
				aluguelPesquisa.add(alugueis.get(x));
			}
		}
		return aluguelPesquisa;
	}
	
    @FXML
    void buscar(ActionEvent event) {
    	tblAlugueis.setItems(buscarAluguel());
    }
	
	public ObservableList<Aluguel> atualizaTabela(){
		AluguelDAO aluguelDao = AbstractFactory.get().aluguelDao();
		alugueis = FXCollections.observableArrayList(aluguelDao.listar());
		return alugueis;
	}
	
}
