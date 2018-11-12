package principal;

import java.time.LocalDate;
import java.util.Locale;

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
import javafx.scene.input.MouseEvent;
import principal.dao.AbstractFactory;
import principal.dao.AluguelDAO;
import principal.dao.CarroDAO;
import principal.dao.DevolucaoDAO;
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
	private Label lblCodigo;
	
	@FXML
    private Button btnDevolver;
	
	@FXML
	private Button btnCalcula;
	
	@FXML
	private ComboBox<TipoPagamento> cbTipoPagamento;
	
	@FXML
	private Button btnBuscar;

	private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
	
	private TipoPagamentoDAO tipoDao = AbstractFactory.get().tipoPagamentoDao();
	private DevolucaoDAO devolucaoDao = AbstractFactory.get().devolucaoDao();
	private AluguelDAO aluguelDao = AbstractFactory.get().aluguelDao();
	private CarroDAO carroDao = AbstractFactory.get().carroDao();
	
	private Devolucao devolucao;

	public boolean populaDevolucao() {
		boolean retorno = true;
		
		Aluguel aluguel = new Aluguel();
		devolucao = new Devolucao();
		
		aluguel = aluguelDao.buscar(Integer.valueOf(lblCodigo.getText()));
		devolucao.setAluguel(aluguel);
		if(lblCodigo.getText().isEmpty()) {
			retorno = false;
		}
		devolucao.setTipoPagamento(cbTipoPagamento.getValue());
		devolucao.setDataChegada(LocalDate.now());
		if(tfKmChegada.getText().isEmpty()) {
			retorno = false;
		}
		devolucao.setQuilometroChegada(Double.valueOf(tfKmChegada.getText()));
		return retorno;
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
		alugueis = FXCollections.observableArrayList(aluguelDao.alugueisAtivos());
		return alugueis;
	}
	
	private void populaCombo(){
		for(TipoPagamento tipo: tipoDao.listar()){
			cbTipoPagamento.getItems().add(tipo);
		}
	}
	
	void valorTotal(){
		AlertaFactory alerta = new AlertaFactory();
		if(devolucao.calculaQuilometros() == null) {
			alerta.mensagemDeAlerta("Quilometragem de chegada inválida");
		}else {
			double valor = (((devolucao.getAluguel().getCarro().getValor() * devolucao.calculaQuilometros())/100) +
					devolucao.getAluguel().getTipoAluguel().getValor()) * devolucao.getAluguel().getTipoAluguel().getTaxa();
			valor = valor - ((devolucao.getTipoPagamento().getDesconto() * valor)/100);
			devolucao.setValorTotal(valor);	
			lblValor.setText(String.format(Locale.US, "%.2f", valor));		
		}
	}
	
	@FXML
    void selecionaAluguel(MouseEvent event) {
    	Aluguel aluguel = new Aluguel();
    	if (tblAluguel.getSelectionModel().getSelectedItem() != null) {
			aluguel = tblAluguel.getSelectionModel().getSelectedItem();
			lblCodigo.setText(aluguel.getCodigo().toString());
		}
    }
	
    @FXML
    void calculaValor(ActionEvent event) {
    	AlertaFactory alerta = new AlertaFactory();
    	if(populaDevolucao()) {
			valorTotal();
		}else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
    }

    @FXML
    void devolverVeiculo(ActionEvent event) {
    	AlertaFactory alerta = new AlertaFactory();
		if(populaDevolucao()) {
			if(alerta.confirmaAceitar()) {
				valorTotal();
				devolucaoDao.inserir(devolucao);
				devolucao.getAluguel().getCarro().setDisponivel(true);
				carroDao.alterar(devolucao.getAluguel().getCarro());
			}
		}
		else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
    }
	
	
}
