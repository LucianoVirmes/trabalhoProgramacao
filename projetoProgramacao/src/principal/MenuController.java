package principal;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuController {

	@FXML
	private Menu mniCadastroFilial;
	@FXML
	private Menu mniCadastroVeiculo;
	@FXML
	private BorderPane bpPrincipal;

	@FXML
	void abreTelaCadastroFilial(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroFilial.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void abreTelaCadastroVeiculo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroVeiculo.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaCadastroAluguel(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroAluguel.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaCadastroCliente(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroCliente.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaCadastroFuncionario(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroFuncionario.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/* Aba gerenciar */
	@FXML
	void abreTelaGerenciarFuncionario(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarFuncionario.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaGerenciarVeiculo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarVeiculo.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaGerenciarAluguel(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarTipoAluguel.fxml"));
		try {
			AnchorPane cursoView = (AnchorPane) loader.load();
			bpPrincipal.setCenter(cursoView);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaAtualizaCliente(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AtualizarCliente.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void abreTelaCarrosDisponiveis(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarrosDisponiveis.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
    @FXML
    void abreTelaAlugueisEmAtividade(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("AlugueisEmAtividade.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void abreTelaDevolucao(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Devolucao.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
}
