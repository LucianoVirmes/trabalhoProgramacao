package principal;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import principal.dao.AbstractFactory;
import principal.dao.AluguelDAO;
import principal.dao.CarroDAO;
import principal.dao.ClienteDAO;
import principal.dao.TipoAluguelDAO;
import principal.model.Aluguel;
import principal.model.Carro;
import principal.model.Cliente;
import principal.model.Filial;
import principal.model.Funcionario;
import principal.model.TipoAluguel;

public class CadastroAluguelController {

    @FXML
    private DatePicker dtAluguel;
    
    @FXML
    private ComboBox<Carro> cbCarro;
    
    @FXML
    private ComboBox<Cliente> cbCliente;
    
    @FXML
    private Button btnBuscarCliente;

    @FXML
    private ComboBox<TipoAluguel> cbTipoAluguel;

    @FXML
    private Button btnBuscarTipoAluguel;
    
    @FXML
    private ComboBox<Funcionario> cbFuncionario;
    
    @FXML
    private ComboBox<Filial> cbFilial;
    
    @FXML
    private Button btnBuscarFilial;
    
    @FXML
    private Button btnBuscarCarro;
    
    @FXML
    private TextField tfKmSaida;
    
    @FXML
    private Button btnRealizarAluguel;

    private TipoAluguelDAO tipoDao = AbstractFactory.get().tipoAluguelDao();
    private CarroDAO carroDao = AbstractFactory.get().carroDao();
    private ClienteDAO clienteDao = AbstractFactory.get().clienteDao();
    private AluguelDAO aluguelDao = AbstractFactory.get().aluguelDao();
    
    private Aluguel aluguel;
    
    
	@FXML
	private void initialize() {
		populaComboCarro();
	}
	
	@FXML
    void buscarCarro(ActionEvent event) {
    	Stage stageDono = (Stage)btnBuscarCarro.getScene().getWindow();
    	CarroDialogFabrica carroDialog = new CarroDialogFabrica(stageDono);
    	Carro carro = carroDialog.showDialog();
    	if(carro != null) {
    		cbCarro.setValue(carro);
    	}
    }
    
    @FXML
    void buscarCliente(ActionEvent event) {
    	Stage stageDono = (Stage)btnBuscarCliente.getScene().getWindow();
    	ClienteDialogFabrica clienteDialog = new ClienteDialogFabrica(stageDono);
    	Cliente cliente = clienteDialog.showDialog();
    	if(cliente != null) {
    		populaComboCliente();
    		cbCliente.setValue(cliente);
    	}
    }

    @FXML
    void buscarTipoAluguel(ActionEvent event) {
    	Stage stageDono = (Stage)btnBuscarTipoAluguel.getScene().getWindow();
    	TipoDialogFabrica tipoDialog = new TipoDialogFabrica(stageDono);
    	TipoAluguel tipo = tipoDialog.showDialog();
    	if(tipo != null) {
    		populaComboTipoAluguel();
    		cbTipoAluguel.setValue(tipo);
    	}
    }

    @FXML
    void realizarAluguel(ActionEvent event) {
    	AlertaFactory alerta = new AlertaFactory();
    	if(populaAluguel()) {
    		if(LoginController.getFuncionario() == null) {
    			alerta.mensagemDeAlerta("Você deve logar como funcionario para realizar esta ação");
    		}else {
    			if(alerta.confirmaAceitar()) {
    				aluguelDao.inserir(aluguel); 
    				Carro car = aluguel.getCarro();
    				car.setDisponivel(false);
    				carroDao.alterar(car);
    			}    			
    		}
    	}else {
    		alerta.mensagemDeAlerta("preencha todos os campos");
    	}
 	
	}

    
    private boolean populaAluguel() {
    	aluguel = new Aluguel();
    	aluguel.setCarro(cbCarro.getValue());
    	if(cbCarro.getSelectionModel().getSelectedItem()==null) {
    		return false;
    	}
    	aluguel.setCliente(cbCliente.getValue());
    	if(cbCliente.getSelectionModel().getSelectedItem()==null) {
    		return false;
    	}
    	if(dtAluguel.isArmed()) {
    		aluguel.setDataAluguel(dtAluguel.getValue());
    	}else {    		
    		aluguel.setDataAluguel(LocalDate.now());
    	}
    	if(aluguel.getDataAluguel()== null) {
    		return false;
    	}
    	aluguel.setFilial(LoginController.getFuncionario().getFilial());
    	aluguel.setFuncionario(LoginController.getFuncionario());
    	aluguel.setTipoAluguel(cbTipoAluguel.getValue());
    	if(cbTipoAluguel.getSelectionModel().getSelectedItem()==null) {
    		return false;
    	}
    	if(tfKmSaida.getText().isEmpty()) {
    		return false;
    	}else {
    		aluguel.setQuilometrosSaida(Double.valueOf(tfKmSaida.getText()));
    	}
    	return true;
    }
    		
    
    /**
     * metodos de popular comboboxes
     */
    private void populaComboTipoAluguel(){
    	cbTipoAluguel.getSelectionModel().clearSelection();
		for(TipoAluguel tipo: tipoDao.listar()){
			cbTipoAluguel.getItems().add(tipo);
		}
	}
    
    private void populaComboCarro(){
    	Filial filial = LoginController.getFuncionario().getFilial(); 
		for(Carro car: carroDao.listarCarroFilial(filial.getCodigo())){
			cbCarro.getItems().add(car);
		}
	}
    
    private void populaComboCliente(){
    	cbCliente.getSelectionModel().clearSelection();
		for(Cliente cliente: clienteDao.listar()){
			cbCliente.getItems().add(cliente);
		}
	}
 
    
}
