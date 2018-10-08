package principal;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DevolverVeiculoController {

    @FXML
    private TableView<?> tblCarro;

    @FXML
    private TableColumn<?, ?> tbcCodigo;

    @FXML
    private TableColumn<?, ?> tbcCarro;

    @FXML
    private TableColumn<?, ?> tbcTipoAluguel;

    @FXML
    private TableColumn<?, ?> tbcFuncionario;

    @FXML
    private TextField tfKmChegada;
    @FXML
    private TextField tfValor;
    @FXML
    private ComboBox<?> cbTipoPagamento;

}
