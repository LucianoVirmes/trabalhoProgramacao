package principal;

import java.util.Optional;

import javafx.scene.control.ButtonType;

public class AlertaFactory {
	
	/**
	 * metodo de opcao ao usuario, se ele realmente deseja continuar como o 
	 * processo de exclusao
	 * 
	 * @return Alerta com botoes
	 */
	public boolean confirmaExclusao() {
		Alerta alerta = new Alerta(); 
		Optional<ButtonType> result = alerta.criarAlert_DoisBotoes("Deseja realmente excluir?", ButtonType.CANCEL, ButtonType.OK).showAndWait();
		alerta.tipoBotaoCancela(ButtonType.CANCEL, "cancela");
		alerta.tipoBotaoAceita(ButtonType.OK, "aceitar");
		return ButtonType.OK.equals(result.get());
	}
	
	/**
	 * metodo de opcao ao usuario, se ele realmente deseja continuar como o 
	 * processo de aceitação
	 * 
	 * @return Alerta com botoes
	 */
	public boolean confirmaAceitar() {
		Alerta alerta = new Alerta(); 
		Optional<ButtonType> result = alerta.criarAlert_DoisBotoes("Deseja confirmar?", ButtonType.CANCEL, ButtonType.OK).showAndWait();
		alerta.tipoBotaoCancela(ButtonType.CANCEL, "não");
		alerta.tipoBotaoAceita(ButtonType.OK, "sim");
		return ButtonType.OK.equals(result.get());
	}
	
	/**
	 * metodo que mostra ao usuario uma inconsistencia com algum ação desejada
	 * Por parametro irá o tipo dessa inconsistencia
	 * @return Alerta com botoes
	 */
	public boolean mensagemDeAlerta(String tipoDeInconsistencia) {
		Alerta alerta = new Alerta(); 
		Optional<ButtonType> result = alerta.criarAlert_UmBotao("ERRO: " + tipoDeInconsistencia, ButtonType.OK).showAndWait();		
		alerta.tipoBotaoAceita(ButtonType.OK, "OK");
		return ButtonType.OK.equals(result.get());
	}

}
