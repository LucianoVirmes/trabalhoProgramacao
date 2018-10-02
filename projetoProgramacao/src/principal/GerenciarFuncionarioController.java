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
    private ComboBox<Filial> cbFilial;

	@FXML
	private DatePicker dtpDataNasc;

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
	private TextField tfSalario;

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
		dtpDataNasc.setValue(func.getDataNascimento());
	}

	public void populaFuncionario() {
		funcionario.setNome(tfNome.getText());
		funcionario.setSobrenome(tfSobrenome.getText());
		funcionario.setCpf(tfCpf.getText());
		funcionario.setTelefone(tfTelefone.getText());
		funcionario.setEmail(tfEmail.getText());
		funcionario.setSenha(tfSenha.getText());
		funcionario.setSalario(Double.valueOf(tfSalario.getText()));
		funcionario.setDataNascimento(dtpDataNasc.getValue());
	}

	
	@FXML
	void atualizar(ActionEvent event) {
		populaFuncionario();
		AlertaFactory alerta = new AlertaFactory();
		if(alerta.confirmaAceitar()) {
			funcionarioDao.alterar(funcionario);
			tblFuncionarios.refresh();
		}
	}

	@FXML
	void buscar(ActionEvent event) {
		tblFuncionarios.setItems(buscarFuncionario());
	}

	@FXML
	void demitir(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		populaFuncionario();
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
