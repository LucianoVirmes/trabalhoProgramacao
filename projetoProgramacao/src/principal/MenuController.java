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
}
/*
 * package principal;
 * 
 * import java.io.IOException;
 * 
 * import javafx.event.ActionEvent; import javafx.fxml.FXML; import
 * javafx.fxml.FXMLLoader; import javafx.scene.control.MenuItem; import
 * javafx.scene.layout.AnchorPane; import javafx.scene.layout.BorderPane;
 * 
 * public class MenuController {
 * 
 * @FXML private MenuItem mmiCurso;
 * 
 * @FXML private MenuItem mmiArea;
 * 
 * 
 * @FXML private BorderPane bpPrincipal;
 * 
 * @FXML void menuCurso(ActionEvent event) { FXMLLoader loader = new
 * FXMLLoader(); loader.setLocation(getClass().getResource("cursoForm.fxml"));
 * try { AnchorPane cursoView = (AnchorPane) loader.load();
 * bpPrincipal.setCenter(cursoView); } catch (IOException e1) {
 * e1.printStackTrace(); } }
 * 
 * @FXML void menuArea(ActionEvent event) { FXMLLoader loader = new
 * FXMLLoader(); loader.setLocation(getClass().getResource("areaForm.fxml"));
 * try { AnchorPane cursoView = (AnchorPane) loader.load();
 * bpPrincipal.setCenter(cursoView); } catch (IOException e1) {
 * e1.printStackTrace(); } } }
 */