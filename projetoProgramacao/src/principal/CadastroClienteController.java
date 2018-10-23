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

    void populaCliente() {
    	cliente = new Cliente();
    	cliente.setNome(tfNome.getText());
    	cliente.setSobrenome(tfSobrenome.getText());
    	cliente.setDataNascimento(dtNascimento.getValue());
    	cliente.setDataDeCadastro(LocalDate.now());
    	cliente.setEmail(tfEmail.getText());
    	cliente.setCnh(tfCnh.getText());
    	cliente.setCpf(tfCpf.getText());
    	cliente.setTelefone(tfTelefone.getText());
    }
    
    
    @FXML
    void cadastrar(ActionEvent event) {
    	populaCliente();
    	AlertaFactory alerta = new AlertaFactory();
    	if(tfCpf.getText().length() == 11) {
    		if(cliente.validaCpf()) {
    			if (alerta.confirmaAceitar()) {
    				clienteDao.inserir(cliente);
    			}
    		}else {
    			alerta.mensagemDeAlerta("CPF inválido");
    		}    		
    	}else {
    		alerta.mensagemDeAlerta("Digite o CPF completo sem pontuação");
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
