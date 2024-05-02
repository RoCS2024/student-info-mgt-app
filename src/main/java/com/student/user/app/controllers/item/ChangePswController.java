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
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class ChangePswController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField show_password1;

    @FXML
    private TextField show_newPsw;

    @FXML
    private TextField show_confirmPsw;

    @FXML
    private PasswordField currentPswField;

    @FXML
    private PasswordField confirmPswField;

    @FXML
    private PasswordField newPswField;

    @FXML
    private ToggleButton toggle_button1;

    @FXML
    private ToggleButton toggle_button2;

    @FXML
    private ToggleButton toggle_button3;

    @FXML
    private ToggleButton toggle_button4;

    @FXML
    private ToggleButton toggle_button5;

    @FXML
    private ToggleButton toggle_button6;

    @FXML
    private Button saveChangePswButton;

    private User user;

    private UserFacade userFacade = new UserFacadeImpl();

    @FXML
    protected void onSaveChangePswClicked(ActionEvent event) {
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
    }

    public void setUser(User user) {
        this.user = user;
        usernameField.setText(user.getUsername());
    }

    @FXML
    private void initialize() {
        saveChangePswButton.disableProperty().bind(
                usernameField.textProperty().isEmpty()
                        .or(currentPswField.textProperty().isEmpty())
                        .or(newPswField.textProperty().isEmpty())
                        .or(confirmPswField.textProperty().isEmpty())
        );
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

    @FXML
    void changeVisibility(ActionEvent event){
        if (toggle_button4.isSelected()){
            show_password1.setText(currentPswField.getText());
            show_password1.setVisible(true);
            currentPswField.setVisible(false);
            toggle_button4.setVisible(false);
            return;
        }
        currentPswField.setText(show_password1.getText());
        currentPswField.setVisible(true);
        show_password1.setVisible(false);
        toggle_button4.setVisible(true);
    }

    @FXML
    void changeVisibility2(ActionEvent event){
        if (toggle_button1.isSelected()){
            currentPswField.setText(show_password1.getText());
            currentPswField.setVisible(true);
            show_password1.setVisible(false);
            toggle_button4.setVisible(true);
            return;
        }
        show_password1.setText(currentPswField.getText());
        show_password1.setVisible(true);
        currentPswField.setVisible(false);
        toggle_button4.setVisible(false);
    }

    @FXML
    void changeVisibility3(ActionEvent event){
        if (toggle_button5.isSelected()){
            show_confirmPsw.setText(confirmPswField.getText());
            show_confirmPsw.setVisible(true);
            confirmPswField.setVisible(false);
            toggle_button5.setVisible(false);
            return;
        }
        confirmPswField.setText(show_confirmPsw.getText());
        confirmPswField.setVisible(true);
        show_confirmPsw.setVisible(false);
        toggle_button5.setVisible(true);
    }

    @FXML
    void changeVisibility4(ActionEvent event){
        if (toggle_button2.isSelected()){
            confirmPswField.setText(show_confirmPsw.getText());
            confirmPswField.setVisible(true);
            show_confirmPsw.setVisible(false);
            toggle_button5.setVisible(true);
            return;
        }
        show_confirmPsw.setText(confirmPswField.getText());
        show_confirmPsw.setVisible(true);
        confirmPswField.setVisible(false);
        toggle_button5.setVisible(false);
    }

    @FXML
    void changeVisibility5(ActionEvent event){
        if (toggle_button6.isSelected()){
            show_newPsw.setText(newPswField.getText());
            show_newPsw.setVisible(true);
            newPswField.setVisible(false);
            toggle_button6.setVisible(false);
            return;
        }
        newPswField.setText(show_newPsw.getText());
        newPswField.setVisible(true);
        show_newPsw.setVisible(false);
        toggle_button6.setVisible(true);
    }

    @FXML
    void changeVisibility6(ActionEvent event){
        if (toggle_button3.isSelected()){
            newPswField.setText(show_newPsw.getText());
            newPswField.setVisible(true);
            show_newPsw.setVisible(false);
            toggle_button6.setVisible(true);
            return;
        }
        show_newPsw.setText(newPswField.getText());
        show_newPsw.setVisible(true);
        newPswField.setVisible(false);
        toggle_button6.setVisible(false);
    }
}
