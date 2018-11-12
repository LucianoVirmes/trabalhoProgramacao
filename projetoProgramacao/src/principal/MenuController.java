package principal;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import principal.conexao.ConexaoUtil;

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
		if(LoginController.getFuncionario() == null) {
			try {
				AnchorPane View = (AnchorPane) loader.load();
				bpPrincipal.setCenter(View);
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}else {
			AlertaFactory alerta = new AlertaFactory();
			alerta.mensagemDeAlerta("Você não tem acesso a esta tela");
		}
	}

	@FXML
	void abreTelaCadastroVeiculo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroVeiculo.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaCadastroAluguel(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		AlertaFactory alerta = new AlertaFactory();
		loader.setLocation(getClass().getResource("CadastroAluguel.fxml"));
		if(LoginController.getFuncionario() == null) {
    		alerta.mensagemDeAlerta("Você deve logar como funcionario para realizar esta ação");
		}else{
			try {
				AnchorPane View = (AnchorPane) loader.load();
				bpPrincipal.setCenter(View);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}	
	}

	@FXML
	void abreTelaCadastroCliente(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroCliente.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaCadastroFuncionario(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CadastroFuncionario.fxml"));
		if(LoginController.getFuncionario() == null) {
			try {
				AnchorPane View = (AnchorPane) loader.load();
				bpPrincipal.setCenter(View);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else {
			AlertaFactory alerta = new AlertaFactory();
			alerta.mensagemDeAlerta("Você não tem acesso a esta tela");
		}
	}

	/* Aba gerenciar */
	@FXML
	void abreTelaGerenciarFuncionario(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarFuncionario.fxml"));
		if(LoginController.getFuncionario() == null) {
			try {
				AnchorPane View = (AnchorPane) loader.load();
				bpPrincipal.setCenter(View);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else {
			AlertaFactory alerta = new AlertaFactory();
			alerta.mensagemDeAlerta("Você não tem acesso a esta tela");
		}
	}

	@FXML
	void abreTelaGerenciarVeiculo(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarVeiculo.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	void abreTelaGerenciarAluguel(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GerenciarTipoAluguel.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
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
    void saiDoMenu(ActionEvent event) {
    	Main.changeScreen(TipoTela.LOGIN);
    	LoginController.setFuncionario(null);
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
    
    @FXML
    void abreTelaGerenciarTipoPagamento(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("TipoPagamento.fxml"));
		try {
			AnchorPane View = (AnchorPane) loader.load();
			bpPrincipal.setCenter(View);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void relatorioAlugueisPorCarro(ActionEvent event) {
    	URL url = getClass().getResource("/relatorios/relatorioAlugueisPorCarro.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager
					 .fillReport(
							 url.getPath(),
							 null,
							 ConexaoUtil.getConn());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void relatorioAquisicaoVeiculos(ActionEvent event) {
    	URL url = getClass().getResource("/relatorios/relatorioAquisicaoVeiculos.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager
					 .fillReport(
							 url.getPath(),
							 null,
							 ConexaoUtil.getConn());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void relatorioCarrosAtivosDisponiveis(ActionEvent event) {
    	URL url = getClass().getResource("/relatorios/relatorioCarrosAtivosDisponiveis.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager
					 .fillReport(
							 url.getPath(),
							 null,
							 ConexaoUtil.getConn());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void relatorioControleFuncionario(ActionEvent event) {
    	URL url = getClass().getResource("/relatorios/relatorioControleFuncionario.jasper");
		try {
			JasperPrint jasperPrint = JasperFillManager
					 .fillReport(
							 url.getPath(),
							 null,
							 ConexaoUtil.getConn());
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void visiteSite(ActionEvent event) {
    	 try {
    		 	Desktop.getDesktop().browse(new URI("http://bestcaralugueis.epizy.com/index.html"));
         	} catch (IOException e1) {
             e1.printStackTrace();
         	} catch (URISyntaxException e1) {
             e1.printStackTrace();
         	}
    }
    
    
}
