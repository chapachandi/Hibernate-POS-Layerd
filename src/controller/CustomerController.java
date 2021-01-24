package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import business.BOFactory;
import business.BOType;
import business.custom.impl.CustomerBOImpl;
import dto.CustomerDTO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CustomerTM;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerController {
    public AnchorPane root;
    public TableView tableCustomer;

    @FXML
    private Label lblID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colOperate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnDelete;

    public TableView<CustomerTM> tblCustomer;
    public ObservableList<CustomerTM> items;
    public CustomerTM value;
    public Label lblBack;
    public Connection connection;


    CustomerBOImpl customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);


    public void initialize() throws Exception{
        lblID.setText(generateNewID());

        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));

//        btnSave.setDisable(true);
//        btnDelete.setDisable(true);

        try {
            List<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress(),customer.getSalary()));
            }
        } catch (Exception ex) {
            Logger.getLogger("controller").log(Level.SEVERE, null,ex);
        }

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM selectedCustomer) {

                if (selectedCustomer == null) {
                    // Clear Selection
                    return;
                }

                lblID.setText(selectedCustomer.getId());
                txtName.setText(selectedCustomer.getName());
                txtAddress.setText(selectedCustomer.getAddress());
//                txtSalary.setText(selectedCustomer.getSalary());

//                btnSave.setDisable(false);
//                btnDelete.setDisable(false);

            }
        });

    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (lblID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerTM ID is empty", ButtonType.OK).showAndWait();
            lblID.requestFocus();
            return;
        } else if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerTM Name is empty", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        } else if (txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerTM Address is empty", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }

        if (tblCustomer.getSelectionModel().isEmpty()) {

            ObservableList<CustomerTM> items = tblCustomer.getItems();
            for (CustomerTM customerTM : items) {
                if (customerTM.getId().equals(lblID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate CustomerTM IDs are not allowed").showAndWait();
                    lblID.requestFocus();
                    return;
                }
            }


            CustomerDTO customerDTO = new CustomerDTO(lblID.getText(), txtName.getText(), txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
            try {
                boolean result = customerBO.saveCustomer(customerDTO);
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer has been saved successfully", ButtonType.OK).showAndWait();

                    CustomerTM customerTM = new CustomerTM(lblID.getText(), txtName.getText(), txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
                    tblCustomer.getItems().add(customerTM);
                    tblCustomer.scrollTo(customerTM);

                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save the customer, try again", ButtonType.OK).showAndWait();
                }

            } catch (Exception e) {
                Logger.getLogger("controller").log(Level.SEVERE, null,e);
            }

        } else {

            try {
                boolean result = customerBO.updateCustomer(new CustomerDTO(lblID.getText(), txtName.getText(), txtAddress.getText(),Double.parseDouble(txtSalary.getText())));

                if (result) {

                    new Alert(Alert.AlertType.INFORMATION, "Customer has been updated successfully").show();
                    CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
                    selectedCustomer.setName(txtName.getText());
                    selectedCustomer.setAddress(txtAddress.getText());
                    tblCustomer.refresh();

                } else {

                    new Alert(Alert.AlertType.ERROR, "Failed to update the customer, try again").show();
                }
            } catch (Exception e) {
                Logger.getLogger("controller").log(Level.SEVERE, null,e);
            }

        }

        reset();
    }

    public String generateNewID(){
        String s = null;
        try {
            s = customerBO.newCustomerID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        reset();
    }

    private void reset() {
        lblID.setText(generateNewID());
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
        lblID.requestFocus();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }


    public void navigateToHome(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"))));

    }

    public void backToHomeOnAction(MouseEvent mouseEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this customer?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            int selectedRow = tblCustomer.getSelectionModel().getSelectedIndex();

            try {
                boolean result = customerBO.removeCustomer(lblID.getText());

                if (result) {
                    tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
                    reset();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the customer, try again").show();
                }
            } catch (Exception e) {
                Logger.getLogger("controller").log(Level.SEVERE, null,e);
            }

        }

    }
}
