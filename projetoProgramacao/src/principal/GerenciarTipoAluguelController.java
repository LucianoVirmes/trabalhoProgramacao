package principal;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import principal.dao.AbstractFactory;
import principal.dao.TipoAluguelDAO;
import principal.model.TipoAluguel;

public class GerenciarTipoAluguelController {

	@FXML
	private TextField tfDescricao;

	@FXML
	private TextField tfValor;
	
    @FXML
    private TextField tfTaxa;

	@FXML
	private TableView<TipoAluguel> tblTipoAluguel;

	@FXML
	private TableColumn<TipoAluguel, Number> tbcCodigo;

	@FXML
	private TableColumn<TipoAluguel, String> tbcDescricao;

	@FXML
	private TableColumn<TipoAluguel, Double> tbcValor;
	
    @FXML
    private TableColumn<TipoAluguel, Double> tbcTaxa;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnExcluir;

    @FXML
    private Button btnReajustar;

	private TipoAluguel tipo;
	
	private boolean editando;
	
	private TipoAluguelDAO tipoDao = AbstractFactory.get().tipoAluguelDao();
	
	
	@FXML
	private void initialize() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tbcTaxa.setCellValueFactory(new PropertyValueFactory<>("taxa"));
		novoTipoAluguel();
	}
	
	@FXML
	void excluir(ActionEvent event) {
		tipoDao.excluir(tipo);
		novoTipoAluguel();
	}

	@FXML
	void novo(ActionEvent event) {
		novoTipoAluguel();
	}

	@FXML
	void salvar(ActionEvent event) {
		populaTipo();
		if (editando) {
			tipoDao.alterar(tipo);
		} else {
			tipoDao.inserir(tipo);				
		}
		novoTipoAluguel();
		tblTipoAluguel.refresh();

	}

	@FXML
	void selecionaTipoAluguel(MouseEvent event) {
		if (tblTipoAluguel.getSelectionModel().getSelectedItem() != null) {
			tipo = tblTipoAluguel.getSelectionModel().getSelectedItem();
			populaTela(tipo);
			editando = true;
		}
	}
	
	public void populaTipo() {
		tipo.setDescricao(tfDescricao.getText());
		tipo.setValor(Double.valueOf(tfValor.getText()));
		tipo.setTaxa(Double.valueOf(tfTaxa.getText()));
	}
	
	public void populaTela(TipoAluguel tipo) {
		tfDescricao.setText(tipo.getDescricao());
		tfValor.setText(tipo.getValor().toString());
		tfTaxa.setText(tipo.getTaxa().toString());
	}
	
	void novoTipoAluguel() {
		tfDescricao.clear();
		tfValor.clear();
		tfTaxa.clear();
		tipo = new TipoAluguel();
		editando = false;		
		tblTipoAluguel.setItems(FXCollections.observableArrayList(tipoDao.listar()));
	}
	
    @FXML
    void reajustar(ActionEvent event) {
    	Stage stageDono = (Stage)btnReajustar.getScene().getWindow();
    	ReajustaDialogFabrica reajustaDialog = new ReajustaDialogFabrica(stageDono);
    	reajustaDialog.showDialog();
    }
	

}
