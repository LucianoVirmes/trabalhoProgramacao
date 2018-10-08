package principal;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReajustaDialogFabrica {

	private Stage stageDono;

	public ReajustaDialogFabrica(Stage stageDono) {
		this.stageDono = stageDono;
	}

	public Boolean showDialog() {
		Boolean boleana = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ReajustaTaxaDialog.fxml"));
		try {
			AnchorPane reajustaDialog = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("reajusta");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stageDono);
			Scene scene = new Scene(reajustaDialog);
			dialogStage.setScene(scene);
			ReajustaDialogController controller = loader.getController();
			controller.setStageDialog(dialogStage);
			dialogStage.showAndWait();
			boleana = controller.getBoleana();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return boleana;

	}



}
