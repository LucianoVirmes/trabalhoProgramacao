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
    

	// funcionario determinado por login
    private static Funcionario funcionarioLogado;
    
    public static Funcionario getFuncionario() {
    	return funcionarioLogado;
    }
    
    /**
     * verifica se o email e senha sao compativeis
     * ou se é admin admin, se for realiza login. 
     * @param event
     */
    @FXML
    void entrar(ActionEvent event) {
    	Funcionario funcio = new Funcionario();
    	AlertaFactory alerta = new AlertaFactory();
    	if(tfEmail.getText().equals("admin") && pfSenha.getText().equals("admin")) {
    		Main.changeScreen(TipoTela.MENU);
    	}
    	else if (funcionarioDao.verificaEmail(tfEmail.getText()) != null) {
    		funcio = funcionarioDao.verificaEmail(tfEmail.getText());
    		if(funcio.getSenha().equals(pfSenha.getText())){
    			funcionarioLogado = funcio;
    			Main.changeScreen(TipoTela.MENU);
    		}else {
    			alerta.mensagemDeAlerta("Senha incompatível");
    		}
    	}else {
    		alerta.mensagemDeAlerta("email incompatível");
    	}
     }
    
    
}
