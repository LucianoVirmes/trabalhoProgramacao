package principal;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.ClienteDAO;
import principal.model.Cliente;

public class CadastroClienteController {

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
    private TextField tfCnh;
    
    @FXML
    private TextField tfEmail;


    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnNovo;
    
    private Cliente cliente;
    
    private ClienteDAO clienteDao = AbstractFactory.get().clienteDao();

    public boolean populaCliente() {
    	cliente = new Cliente();
    	cliente.setDataDeCadastro(LocalDate.now());
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
		cliente.setDataNascimento(dtNascimento.getValue());
		return true;
    }
    
    
    @FXML
    void cadastrar(ActionEvent event) {
    	AlertaFactory alerta = new AlertaFactory();
    	if(populaCliente()) {
    		if(tfCpf.getText().length() == 11) {
    			if(cliente.validaCpf()) {
    				if (alerta.confirmaAceitar()) {
    					clienteDao.inserir(cliente);
    					alerta.salvoComSucesso();
    					novoCliente();
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
    	novoCliente();
    }

    void novoCliente() {
    	cliente = new Cliente();
    	tfNome.clear();
    	tfSobrenome.clear();
    	dtNascimento.setValue(null);
    	tfEmail.clear();
    	tfCnh.clear();
    	tfCpf.clear();
    	tfTelefone.clear();
    }
    
}
