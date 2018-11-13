package principal;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	/**
	 * variavel tipo alerta
	 */
	private Alert alerta;

	/** 
	 * 
	 * Cria um alert com 2 botoes e uma mensagem 
	 * Para dar uma opção de ao usuario
	 * 
	 * @param mensagem - mensagem que deseja mostrar 
	 * @param calcela - tipo do botao de negação
	 * @param aceita - tipo do botao para aceitar
	 * @return Alert
	 */
	public Alert criarAlert_DoisBotoes(String mensagem, ButtonType calcela, ButtonType aceita) {
		alerta = new Alert(AlertType.CONFIRMATION, mensagem, calcela, aceita);
		tipoBotaoAceita(aceita, "Sim");
		tipoBotaoCancela(calcela, "Não");
		return alerta;
	}
	
	/**
	 * Cria um alert com 1 botao e uma mensagem 
	 * Para mostrar um erro ou inconsistencia ao usuario 
	 * 
	 * @param mensagem - mensagem que deseja mostrar 
	 * @param aceita - tipo do botao para aceitar
	 * @return Alert
	 */
	public Alert criarAlert_UmBotaoErro(String mensagem, ButtonType aceita) {
		alerta = new Alert(AlertType.WARNING, mensagem, aceita);
		tipoBotaoAceita(aceita, "Sim");
		return alerta;
	}
	
	public Alert criarAlert_UmBotao(String mensagem, ButtonType aceita) {
		alerta = new Alert(AlertType.NONE, mensagem, aceita);
		tipoBotaoAceita(aceita, "OK");
		return alerta;
	}
	
	/**
	 * 
	 * @param tipo - tipo de botao
	 * @param mensagem - mensagem do botao
	 * @return Button
	 */
	public Button tipoBotaoCancela(ButtonType tipo, String mensagem) {
		Button cancelButton = (Button) alerta.getDialogPane().lookupButton(tipo);
		cancelButton.setText(mensagem);
		return cancelButton;
	}
	
	/**
	 * 
	 * @param tipo - tipo de botao
	 * @param mensagem - mensagem do botao
	 * @return Button
	 */
	public Button tipoBotaoAceita(ButtonType tipo, String mensagem) {
		Button okButton = (Button) alerta.getDialogPane().lookupButton(tipo);
		okButton.setDefaultButton(false);
		okButton.setText(mensagem);
		return okButton;
	}
	
	
	
}
