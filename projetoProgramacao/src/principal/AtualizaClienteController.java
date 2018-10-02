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
	private Button btnAtualizar;

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
	private DatePicker dtpDataNasc;

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

	public void populaCliente() {
		cliente.setNome(tfNome.getText());
		cliente.setSobrenome(tfSobrenome.getText());
		cliente.setCpf(tfCpf.getText());
		cliente.setTelefone(tfTelefone.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setCnh(tfCnh.getText());
		cliente.setDataNascimento(dtpDataNasc.getValue());
	}

	@FXML
	void atualizar(ActionEvent event) {
		populaCliente();
		AlertaFactory alerta = new AlertaFactory();
		if (alerta.confirmaAceitar()) {
			clienteDao.alterar(cliente);
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
