package com.student.user.app.controllers.item;

import com.student.information.management.StudentInfoMgtApplication;
import com.student.information.management.appl.facade.student.StudentFacade;
import com.student.information.management.appl.model.student.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.Timestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;


public class UpdateStudentController {

    @FXML
    private TextField studentId;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField middleName;

    @FXML
    private ComboBox<String> sex;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField religion;

    @FXML
    private TextField email;

    @FXML
    private TextField contactNo;

    @FXML
    TextArea address;

    private Student student;

    @FXML
    private Button studentUpdateButton;


    StudentInfoMgtApplication app = new StudentInfoMgtApplication();

    StudentFacade studentFacade = app.getStudentFacade();

    public void setStudent(Student student) {
        this.student = student;
        studentId.setText(student.getStudentId());
        lastName.setText(student.getLastName());
        firstName.setText(student.getFirstName());
        middleName.setText(student.getMiddleName());
        sex.setValue(student.getSex());

        religion.setText(student.getReligion());
        email.setText(student.getEmail());
        contactNo.setText(student.getContactNumber());
        address.setText(student.getAddress());

        Timestamp studentTimestamp = student.getBirthday();
        LocalDate studentLocalDate = studentTimestamp.toLocalDateTime().toLocalDate();
        birthday.setValue(studentLocalDate);

    }

    @FXML
    protected void handleCancelUpdateViewButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        showDashboard();
    }

    private void showDashboard() {
        try {
            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/StudentList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onUpdateStudOClicked(ActionEvent event) {
        if (!isValidInput()) {
            showErrorAlert("Invalid input", "Non-alphanumeric characters are not allowed.");
            return;
        }
        Student updateStudent = new Student();
        updateStudent.setStudentId(studentId.getText());
        updateStudent.setLastName(lastName.getText());
        updateStudent.setFirstName(firstName.getText());
        updateStudent.setMiddleName(middleName.getText());
        updateStudent.setSex(sex.getValue());
        updateStudent.setReligion(religion.getText());
        updateStudent.setEmail(email.getText());
        updateStudent.setContactNumber(contactNo.getText());
        updateStudent.setAddress(address.getText());

        String enteredEmail = email.getText();
        if (!isValidEmail(enteredEmail)) {
            showErrorAlert("Invalid Email Address " ," Please enter a valid email address.");
            return;
        }
        String contactNumber = contactNo.getText().trim();
        if (!isValidContactNumber(contactNumber)) {
            showErrorAlert("Invalid Contact Number " ,"Please enter a 11-digit contact number.");
            return;
        }
        updateStudent.setEmail(enteredEmail);

        updateStudent.setContactNumber(contactNo.getText());
        updateStudent.setAddress(address.getText());

        LocalDate selectedDate = birthday.getValue();
        if (selectedDate != null) {
            try {
                LocalDate minDate = LocalDate.now().minusYears(17);
                if (selectedDate.isAfter(minDate)) {
                    showErrorAlert("Invalid date format " , " Please select a date from 17 years ago or earlier.");
                    return;
                }
                LocalDateTime localDateTime = selectedDate.atStartOfDay();
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                updateStudent.setBirthday(timestamp);
            } catch (IllegalArgumentException e) {
                showErrorAlert("Invalid date format " ,"Please select a valid date format.");
                e.printStackTrace();
            }
        }

        try {
            studentFacade.updateStudent(updateStudent);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                previousStage.close();

                Stage dashboardStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/StudentList.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);
                dashboardStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isValidInput() {
        return isValidAlphanumeric(lastName.getText()) &&
                isValidAlphanumeric(firstName.getText()) &&
                isValidAlphanumeric(middleName.getText()) &&
                isValidAlphanumeric(religion.getText());
    }

    private boolean isValidAlphanumeric(String input) {
        return input != null && input.matches("[a-zA-Z0-9]+");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidContactNumber(String contactNumber) {
        return contactNumber.matches("\\d{11}");
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}