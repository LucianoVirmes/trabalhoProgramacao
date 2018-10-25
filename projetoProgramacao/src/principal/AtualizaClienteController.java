package principal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import principal.dao.AbstractFactory;
import principal.dao.ClienteDAO;
import principal.model.Cliente;

public class AtualizaClienteController {


	@FXML
	private TextField tfBuscar;

	@FXML
	private Button btnBuscar;

	@FXML
	private TableView<Cliente> tblClientes;

	@FXML
	private TableColumn<Cliente, String> tbcCnh;

	@FXML
	private TableColumn<Cliente, String> tbcNome;

	@FXML
	private TableColumn<Cliente, String> tbcSobrenome;

	@FXML
	private TableColumn<Cliente, String> tbcCpf;

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
	private TextField tfCnh;

	@FXML
	private DatePicker dtpDataNasc;
	
	@FXML
	private Button btnAtualizar;

	private Cliente cliente;

	private ClienteDAO clienteDao = AbstractFactory.get().clienteDao();
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		tbcCnh.setCellValueFactory(new PropertyValueFactory<>("cnh"));
		tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tbcSobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
		tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tblClientes.setItems(atualizaTabela());
	}

	private ObservableList<Cliente> atualizaTabela() {
		clientes = FXCollections.observableArrayList(clienteDao.listar());
		return clientes;
	}

	public void populaTela(Cliente cliente) {
		tfNome.setText(cliente.getNome());
		tfSobrenome.setText(cliente.getSobrenome());
		tfTelefone.setText(cliente.getTelefone());
		tfCpf.setText(cliente.getCpf());
		tfEmail.setText(cliente.getEmail());
		tfCnh.setText(cliente.getCnh());
		dtpDataNasc.setValue(cliente.getDataNascimento());
	}

	public boolean populaCliente() {
		cliente.setNome(tfNome.getText());
		if(tfNome.getText().isEmpty()) {
			return false;
		}
		cliente.setSobrenome(tfSobrenome.getText());
		if(tfSobrenome.getText().isEmpty()) {
			return false;
		}
		cliente.setCpf(tfCpf.getText());
		if(tfCpf.getText().isEmpty()) {
			return false;
		}
		cliente.setTelefone(tfTelefone.getText());
		if(tfTelefone.getText().isEmpty()) {
			return false;
		}
		cliente.setEmail(tfEmail.getText());
		if(tfEmail.getText().isEmpty()) {
			return false;
		}
		cliente.setCnh(tfCnh.getText());
		if(tfCnh.getText().isEmpty()) {
			return false;
		}
		cliente.setDataNascimento(dtpDataNasc.getValue());
		if(dtpDataNasc.getValue().equals(null)) {
			return false;
		}
		return true;
	}
	public void limpaTela() {
		tfNome.clear();
		tfSobrenome.clear();
		tfCpf.clear();
		tfTelefone.clear();
		tfEmail.clear();
		tfCnh.clear();
		dtpDataNasc.setValue(null);
	}
	@FXML
	void atualizar(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		if(populaCliente()) {
			if(cliente.validaCpf()) {
				if (alerta.confirmaAceitar()) {
					clienteDao.alterar(cliente);
					limpaTela();
				}
			}			
		}else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
	}

	@FXML
	void buscar(ActionEvent event) {
		tblClientes.setItems(buscarCliente());
	}

	private ObservableList<Cliente> buscarCliente() {
		ObservableList<Cliente> clientePesquisa = FXCollections.observableArrayList();
		for (int x = 0; x < clientes.size(); x++) {
			if (clientes.get(x).getCpf().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				clientePesquisa.add(clientes.get(x));
			}
		}
		return clientePesquisa;
	}

	@FXML
	void selecionaCliente(MouseEvent event) {
		if (tblClientes.getSelectionModel().getSelectedItem() != null) {
			cliente = tblClientes.getSelectionModel().getSelectedItem();
			populaTela(cliente);
		}
	}
}
