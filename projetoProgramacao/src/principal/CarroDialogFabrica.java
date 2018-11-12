package principal;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import principal.model.Carro;

public class CarroDialogFabrica {
	
	private Stage stageDono;

	public CarroDialogFabrica(Stage stageDono) {
		this.stageDono = stageDono;
	}

	public Carro showDialog() {
		Carro carro = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CarrosDisponiveisNaFilial.fxml"));
		try {
			AnchorPane carroDialog = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("seleciona carro");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(carroDialog);
			dialogStage.setScene(scene);
			CarroDialogController controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();
			carro = controller.getCarro();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return carro;

	}
	
}
