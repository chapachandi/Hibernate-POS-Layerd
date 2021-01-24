package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainFormController {
    public AnchorPane root;
    public Button btnCustomer;
    public Button openItemForm;
    public Button btnOpenCustomerForm;

    public void openCustomerForm(MouseEvent mouseEvent) throws IOException {
        initUi("../view/CustomerForm.fxml");
    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void openItemForm(MouseEvent mouseEvent) throws IOException {
        setUI("ItemForm.fxml");
    }

    public void openOrderForm(MouseEvent mouseEvent) {
    }

    public void openPlaceOrderForm(MouseEvent mouseEvent) {
    }

    private void setUI(String location) throws IOException {


    }
}
