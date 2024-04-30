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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class ChangePswController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField currentPswField;

    @FXML
    private PasswordField newPswField;

    @FXML
    private PasswordField confirmPswField;

    @FXML
    private Button saveChangePswButton;

    private User user;

    private UserFacade userFacade = new UserFacadeImpl();

    private String getInvalidInputMessage() {
        String alphanumericRegex = "[a-zA-Z0-9]+";


        if (!usernameField.getText().matches(alphanumericRegex)) {
            return "Invalid input for Email. Please enter alphanumeric characters only.";
        }
        return null;
    }

    @FXML
    protected void onSaveChangePswClicked(ActionEvent event) {
        try {
            String invalidInputMessage = getInvalidInputMessage();
            if (invalidInputMessage != null) {
                showAlert("Invalid Input", invalidInputMessage);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        User updatePsw = new User();
        updatePsw.setUsername(usernameField.getText());
        updatePsw.setPassword(newPswField.getCharacters().toString());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        updatePsw.setDate_modified(timestamp);

        try {
            userFacade.updatePassword(updatePsw);
        } catch(Exception ex) {
            ex.printStackTrace();;
        }
        finally {
            try {
                Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                previousStage.close();

                Stage dashboardStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/UserList.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);
                dashboardStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUser(User user) {
        this.user = user;
        usernameField.setText(user.getUsername());
    }


    @FXML
    protected void handleCancelChangePsw(MouseEvent event) {
        try {
            Stage previousStage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage2.close();

            Stage dashboardStage2 = new Stage();
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/views/UserList.fxml"));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2);
            dashboardStage2.setScene(scene2);
            dashboardStage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
