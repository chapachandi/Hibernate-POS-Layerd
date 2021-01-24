package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;

public class PlaceOrderFormController {

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPlaceOrder;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblOrderDate;

    @FXML
    private ComboBox<?> cmbCustomerID;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void OrderIdOnAction(MouseEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void lblPlaceOrder_Action(MouseEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void OrderIdOnAction(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void lblPlaceOrder_Action(javafx.scene.input.MouseEvent mouseEvent) {
    }
}

