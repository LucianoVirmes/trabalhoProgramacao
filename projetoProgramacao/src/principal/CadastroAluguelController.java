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
import principal.dao.FilialDAO;
import principal.dao.FuncionarioDAO;
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
    private TextField tfKmSaida;
    
    @FXML
    private Button btnRealizarAluguel;

    private TipoAluguelDAO tipoDao = AbstractFactory.get().tipoAluguelDao();
    private CarroDAO carroDao = AbstractFactory.get().carroDao();
    private ClienteDAO clienteDao = AbstractFactory.get().clienteDao();
    private FilialDAO filialDao = AbstractFactory.get().filialDao();
    private FuncionarioDAO funcionarioDao = AbstractFactory.get().funcionarioDao();
    private AluguelDAO aluguelDao = AbstractFactory.get().aluguelDao();
    
    private Aluguel aluguel;
    
    // funcionario determinado por login
    private static Funcionario funcionarioLogado;
    
    public static void setFuncionario(Funcionario func) {
    	funcionarioLogado = func;
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
    void buscarFilial(ActionEvent event) {
    	Stage stageDono = (Stage)btnBuscarFilial.getScene().getWindow();
    	FilialDialogFabrica filialDialog = new FilialDialogFabrica(stageDono);
    	Filial filial = filialDialog.showDialog();
    	if(filial != null) {
    		populaComboFilial();
    		populaComboCarro(filial);
    		populaComboFuncionario(filial);
    		cbFilial.setValue(filial);
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
    		if(alerta.confirmaAceitar()) {
    			aluguelDao.inserir(aluguel); 
    			Carro car = aluguel.getCarro();
    			car.setDisponivel(false);
    			carroDao.alterar(car);
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
    	aluguel.setFilial(funcionarioLogado.getFilial());
    	if(cbFilial.getSelectionModel().getSelectedItem()==null) {
    		return false;
    	}
    	aluguel.setFuncionario(funcionarioLogado);
    	if(cbFuncionario.getSelectionModel().getSelectedItem()==null) {
    		return false;
    	}
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
    
    private void populaComboCarro(Filial filial){
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
    
    private void populaComboFilial(){
    	cbFilial.getSelectionModel().clearSelection();
		for(Filial filial: filialDao.listar()){
			cbFilial.getItems().add(filial);
		}
		cbFilial.setValue(funcionarioLogado.getFilial());
	}
    
    private void populaComboFuncionario(Filial filial){
		for(Funcionario funcionario: funcionarioDao.listarFuncionarioFilial(filial.getCodigo())){
			cbFuncionario.getItems().add(funcionario);
		}
		cbFuncionario.setValue(funcionarioLogado);
	}
 
    
}
