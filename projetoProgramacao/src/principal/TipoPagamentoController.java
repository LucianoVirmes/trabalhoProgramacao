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
import principal.dao.AbstractFactory;
import principal.dao.TipoPagamentoDAO;
import principal.model.TipoPagamento;

public class TipoPagamentoController {

	@FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfDesconto;

    @FXML
    private TableView<TipoPagamento> tblTipoPagamento;

    @FXML
    private TableColumn<TipoPagamento, Number> tbcCodigo;

    @FXML
    private TableColumn<TipoPagamento, String> tbcDescricao;

    @FXML
    private TableColumn<TipoPagamento, Double> tbcDesconto;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnExcluir;

    private boolean editando;
    private TipoPagamento tipo;
    private TipoPagamentoDAO tipoDao = AbstractFactory.get().tipoPagamentoDao();
    
    @FXML
	private void initialize() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbcDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));
		novoTipoPagamento();
	}
    
    @FXML
    void excluir(ActionEvent event) {
    	tipoDao.excluir(tipo);
    	novoTipoPagamento();
    }

    @FXML
    void novo(ActionEvent event) {
    	novoTipoPagamento();
    }

    @FXML
    void salvar(ActionEvent event) {
    	if(populaTipo()) {
    		if (editando) {
    			tipoDao.alterar(tipo);
    		} else {
    			tipoDao.inserir(tipo);				
    		}
    	}else {
    		AlertaFactory alerta = new AlertaFactory();
    		alerta.mensagemDeAlerta("preencha todos os campos");
    	}
    	novoTipoPagamento();
    	tblTipoPagamento.refresh();
    }

    @FXML
    void selecionaTipoPagamento(MouseEvent event) {
    	if (tblTipoPagamento.getSelectionModel().getSelectedItem() != null) {
			tipo = tblTipoPagamento.getSelectionModel().getSelectedItem();
			populaTela(tipo);
			editando = true;
		}
    }
    
	public boolean populaTipo() {
		tipo.setDescricao(tfDescricao.getText());
		if(tfDescricao.getText().isEmpty()) {
			return false;
		}
		if(tfDesconto.getText().isEmpty()) {
			return false;
		}else {
			tipo.setDesconto(Double.valueOf(tfDesconto.getText()));			
		}
		return true;
	}
	
	public void populaTela(TipoPagamento tipo) {
		tfDescricao.setText(tipo.getDescricao());
		tfDesconto.setText(tipo.getDesconto().toString());
	}
    
    void novoTipoPagamento() {
    	tipo = new TipoPagamento();
		tfDescricao.clear();
		tfDesconto.clear();
		editando = false;		
		tblTipoPagamento.setItems(FXCollections.observableArrayList(tipoDao.listar()));
	}
	
}
