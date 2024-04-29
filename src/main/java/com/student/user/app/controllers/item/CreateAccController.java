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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Timestamp;

public class CreateAccController {

    @FXML
    private TextField idField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField entityIdField;

    @FXML
    private Button registerButton;

    private User user;

    private UserFacade userFacade = new UserFacadeImpl();

    @FXML
    protected void saveRegisterClicked(ActionEvent event) {
        try {
            if (!isValidId(idField.getText())) {
                showAlert("Invalid Input", "ID field should only accept numbers.");
                return;
            }

            User addUser = new User();
            addUser.setId(Integer.parseInt(idField.getText()));
            addUser.setUsername(usernameField.getText());
            addUser.setEntity_id(entityIdField.getText());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            addUser.setDate_created(timestamp);
            addUser.setDate_modified(timestamp);

            userFacade.saveUser(addUser);

            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();
            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (NumberFormatException ex) {
            showAlert("Invalid Input", "Invalid format for ID. Please enter numbers only.");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidId(String id) {
        String regex = "^[0-9]*$";
        return id.matches(regex);
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
