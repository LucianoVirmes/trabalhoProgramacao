package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import principal.dao.AbstractFactory;
import principal.dao.FuncionarioDAO;
import principal.model.Funcionario;

public class LoginController {

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private Button btnEntrar;
    
    private FuncionarioDAO funcionarioDao = AbstractFactory.get().funcionarioDao(); 
    
    /**
     * verifica se o email e senha sao compativeis
     * ou se é admin admin, se for realiza login. 
     * @param event
     */
    @FXML
    void entrar(ActionEvent event) {
    	Funcionario f = new Funcionario();
    	AlertaFactory alerta = new AlertaFactory();
    	if(tfEmail.getText().equals("admin") && pfSenha.getText().equals("admin")) {
    		Main.changeScreen(TipoTela.MENU);
    	}
    	else if (funcionarioDao.verificaEmail(tfEmail.getText()) != null) {
    		f = funcionarioDao.verificaEmail(tfEmail.getText());
    		CadastroAluguelController.setFuncionario(f);
    		if(f.getSenha().equals(pfSenha.getText())){
    			Main.changeScreen(TipoTela.MENU);
    		}else {
    			alerta.mensagemDeAlerta("Senha incompatível");
    		}
    	}else {
    		alerta.mensagemDeAlerta("email incompatível");
    	}
     }
    
    
}
