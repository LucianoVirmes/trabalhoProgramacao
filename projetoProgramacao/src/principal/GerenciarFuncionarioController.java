package principal;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import principal.dao.AbstractFactory;
import principal.dao.FilialDAO;
import principal.dao.FuncionarioDAO;
import principal.model.Filial;
import principal.model.Funcionario;

public class GerenciarFuncionarioController {

	@FXML
	private TextField tfBuscar;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Funcionario> tblFuncionarios;

	@FXML
	private TableColumn<Funcionario, Number> tbcCodigo;

	@FXML
	private TableColumn<Funcionario, String> tbcNome;

	@FXML
	private TableColumn<Funcionario, String> tbcSobrenome;

	@FXML
	private TableColumn<Funcionario, String> tbcCpf;
	

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnDemitir;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfSobrenome;

	@FXML
	private TextField tfTelefone;

	@FXML
	private TextField tfCpf;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfSenha;
	
	@FXML
	private DatePicker dtpDataNasc;

	@FXML
	private TextField tfSalario;
	
	@FXML
	private ComboBox<Filial> cbFilial;

	private Funcionario funcionario;

	private FuncionarioDAO funcionarioDao = AbstractFactory.get().funcionarioDao();
	private FilialDAO filialDao = AbstractFactory.get().filialDao();
	
	 private void populaCombo(){
			for(Filial filial: filialDao.listar()){
				cbFilial.getItems().add(filial);
			}
		}

	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbcSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
		tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tblFuncionarios.setItems(atualizaTabela());
		populaCombo();
	}

	@FXML
	void selecionaFuncionario(MouseEvent event) {
		if (tblFuncionarios.getSelectionModel().getSelectedItem() != null) {
			funcionario = tblFuncionarios.getSelectionModel().getSelectedItem();			
			populaTela(funcionario);
		}
	}

	public void populaTela(Funcionario func) {
		tfNome.setText(func.getNome());
		tfSobrenome.setText(func.getSobrenome());
		tfTelefone.setText(func.getTelefone());
		tfCpf.setText(func.getCpf());
		tfEmail.setText(func.getEmail());
		tfSenha.setText(func.getSenha());
		tfSalario.setText(func.getSalario().toString());
		cbFilial.setValue(func.getFilial());
		dtpDataNasc.setValue(func.getDataNascimento());
	}

	public boolean populaFuncionario() {
		funcionario.setNome(tfNome.getText());
		if(tfNome.getText().isEmpty()) {
			return false;
		}
		funcionario.setSobrenome(tfSobrenome.getText());
		if(tfSobrenome.getText().isEmpty()) {
			return false;
		}
		funcionario.setDataNascimento(dtpDataNasc.getValue());
		if(dtpDataNasc.getValue().equals(null)) {
			return false;
		}
		funcionario.setEmail(tfEmail.getText());
		if(tfEmail.getText().isEmpty()) {
			return false;
		}
		funcionario.setCpf(tfCpf.getText());
		if(tfCpf.getText().isEmpty()) {
			return false;
		}
		funcionario.setSenha(tfSenha.getText());
		if(tfSenha.getText().isEmpty()) {
			return false;
		}
		funcionario.setTelefone(tfTelefone.getText());
		if(tfTelefone.getText().isEmpty()) {
			return false;
		}
		funcionario.setFilial(cbFilial.getValue());
		if(cbFilial.getValue().equals(null)) {
			return false;
		}
		if(tfSalario.getText().isEmpty()) {
			return false;
		}else {
			funcionario.setSalario(Double.valueOf(tfSalario.getText()));
		}
		return true;

	}

	
	@FXML
	void atualizar(ActionEvent event) {
		if (tblFuncionarios.getSelectionModel().getSelectedItem() != null) {
			funcionario = tblFuncionarios.getSelectionModel().getSelectedItem();			
			AlertaFactory alerta = new AlertaFactory();
			if(populaFuncionario()) {
				if(alerta.confirmaAceitar()) {
					funcionarioDao.alterar(funcionario);
					tblFuncionarios.refresh();
				}				
			}else {
				alerta.mensagemDeAlerta("preencha todos os campos");
			}
		}
		tblFuncionarios.refresh();
	}

	@FXML
	void buscar(ActionEvent event) {
		tblFuncionarios.setItems(buscarFuncionario());
	}

	@FXML
	void demitir(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		funcionario = tblFuncionarios.getSelectionModel().getSelectedItem();
		if(alerta.confirmaExclusao()) {
		//  funcionario.setDataDemissao(LocalDate.now());
			funcionarioDao.demitirFuncionario(funcionario);
		}
	}

	private ObservableList<Funcionario> atualizaTabela() {
		funcionarios = FXCollections.observableArrayList(funcionarioDao.listar());
		return funcionarios;
	}

	private ObservableList<Funcionario> buscarFuncionario() {
		ObservableList<Funcionario> funcionarioPesquisa = FXCollections.observableArrayList();
		for (int x = 0; x < funcionarios.size(); x++) {
			if (funcionarios.get(x).getCpf().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				funcionarioPesquisa.add(funcionarios.get(x));
			}
		}
		return funcionarioPesquisa;
	}

}
