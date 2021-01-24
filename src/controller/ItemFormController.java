package controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;

        import java.awt.event.MouseEvent;

public class ItemFormController {

    public AnchorPane root;
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<?> tableItem;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colOperate;

    @FXML
    private Button btnSearch;

    @FXML
    private Label lblItemCode;

    @FXML
    void backToHome(MouseEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void getDaataOnAction(MouseEvent event) {

    }

    public void backToHome(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void getDataOnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }
}
