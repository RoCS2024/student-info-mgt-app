package com.student.user.app.controllers.item;

import com.user.management.appl.facade.user.UserFacade;
import com.user.management.appl.facade.user.impl.UserFacadeImpl;
import com.user.management.appl.model.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateAccController {


    @FXML
    private TextField usernameField;

    @FXML
    private TextField entityIdField;

    @FXML
    private Button registerButton;

    private User user;

    private UserFacade userFacade = new UserFacadeImpl();
    private String getInvalidInputMessage() {
        String alphanumericRegex = "[a-zA-Z0-9]+";

        if ( usernameField.getText().isEmpty() || entityIdField.getText().isEmpty()) {
            return "All fields must be filled.";
        }

       
        if (!usernameField.getText().matches(alphanumericRegex)) {
            return "Invalid input for Email. Please enter alphanumeric characters only.";
        }
        if (!entityIdField.getText().matches(alphanumericRegex)) {
            return "Invalid input for Email. Please enter alphanumeric characters only.";
        }
        return null;
    }
    @FXML
    protected void saveRegisterClicked(ActionEvent event) {
        try {
            String invalidInputMessage = getInvalidInputMessage();
            if (invalidInputMessage != null) {
                showAlert("Invalid Input", invalidInputMessage);
                return;
            }
            String entityId = entityIdField.getText();
            if (isEntityIdTaken(entityId)) {
                showAlert("Duplicate Entity ID", "This Entity ID is already taken.");
                return;
            }

            User addUser = new User();
            addUser.setUsername(usernameField.getText());
            addUser.setEntity_id(entityIdField.getText());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            addUser.setDate_created(timestamp);
            addUser.setDate_modified(timestamp);


                userFacade.saveUser(addUser);
            } catch (Exception ex) {
                ex.printStackTrace();

            } finally {
                if (getInvalidInputMessage() == null && !isEntityIdTaken(entityIdField.getText())) {

                try {
                    Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    previousStage.close();

                    Stage dashboardStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/MainView.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    dashboardStage.setScene(scene);
                    dashboardStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private boolean isEntityIdTaken(String entityId) {
        try {
            return userFacade.getAllUsers().stream().anyMatch(user -> user.getEntity_id().equals(entityId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void handleHaveAccount(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleRegisterNow(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
