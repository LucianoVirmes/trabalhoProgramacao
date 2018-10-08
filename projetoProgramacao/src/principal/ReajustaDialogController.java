package principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import principal.dao.AbstractFactory;
import principal.dao.BancoDAO;

public class ReajustaDialogController {
	
    @FXML
    private TextField tfPorcentagem;

    @FXML
    private Button btnReajustar;
    
    private Stage dialogStage;
    
    private Boolean booleana;

    private BancoDAO bancoDao = AbstractFactory.get().bancoDao();
    
    @FXML
    void reajustar(ActionEvent event) {
    	if(tfPorcentagem.getText() != null) {
    		AlertaFactory alerta = new AlertaFactory();
    		if(alerta.confirmaAceitar()) {
    			bancoDao.reajusta_taxa(Double.valueOf(tfPorcentagem.getText()));
    			dialogStage.close();
    		}
    	}
    }
	
	public void setStageDialog(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public Boolean getBoleana() {
		return this.booleana;
	}

}
