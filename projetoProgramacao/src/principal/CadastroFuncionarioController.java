package principal;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.FilialDAO;
import principal.dao.FuncionarioDAO;
import principal.model.Filial;
import principal.model.Funcionario;

public class CadastroFuncionarioController {

	@FXML
	private TextField tfNome;
	
	@FXML
	private TextField tfSobrenome;

	@FXML
	private DatePicker dtNascimento;
	
	@FXML
	private TextField tfTelefone;
	
	@FXML
	private TextField tfCpf;
	
	@FXML
	private TextField tfEmail;
	
	@FXML
	private TextField tfSalario;
	
	@FXML
	private TextField tfSenha;
	
	@FXML
	private ComboBox<Filial> cbFilial;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnCadastro;

	private Funcionario funcionario;
	private FuncionarioDAO funcionarioDao = AbstractFactory.get().funcionarioDao();
	private FilialDAO filialDao = AbstractFactory.get().filialDao();

	@FXML
	private void initialize() {
		populaCombo();
		novoFuncionario();
	}

	private void populaCombo() {
		for (Filial filial : filialDao.listar()) {
			cbFilial.getItems().add(filial);
		}
	}

	boolean populaFuncionario() {
		funcionario.setDataAdmissao(LocalDate.now());
		funcionario.setNome(tfNome.getText());
		if(tfNome.getText().isEmpty()) {
			return false;
		}
		funcionario.setSobrenome(tfSobrenome.getText());
		if(tfSobrenome.getText().isEmpty()) {
			return false;
		}
		funcionario.setDataNascimento(dtNascimento.getValue());
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
		if(cbFilial.getSelectionModel().getSelectedItem()==null) {
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
	void cadastrar(ActionEvent event) {
		AlertaFactory alerta = new AlertaFactory();
		if(populaFuncionario()) {
			if(tfCpf.getText().length() == 11) {
				if(funcionario.validaCpf()) {
					if (alerta.confirmaAceitar()) {
						funcionarioDao.inserir(funcionario);
					}
				}else {
					alerta.mensagemDeAlerta("CPF inválido");
				}			
			}else {
				alerta.mensagemDeAlerta("Digite o CPF completo sem pontuação");
			}
		}else {
			alerta.mensagemDeAlerta("preencha todos os campos");
		}
		
	}

	@FXML
	void novo(ActionEvent event) {
		novoFuncionario();
	}

	void novoFuncionario() {
		funcionario = new Funcionario();
		tfNome.clear();
		tfSobrenome.clear();
		dtNascimento.setValue(null);
		tfEmail.clear();
		tfSenha.clear();
		tfSalario.clear();
		tfCpf.clear();
		tfTelefone.clear();
		cbFilial.getSelectionModel().clearSelection();
	}
}
