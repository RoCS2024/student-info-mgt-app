package com.student.user.app.controllers.item;

import com.student.information.management.StudentInfoMgtApplication;
import com.student.information.management.appl.facade.student.StudentFacade;
import com.student.information.management.appl.facade.student.impl.StudentFacadeImpl;
import com.student.information.management.appl.model.student.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
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
    private TextArea address;

    private Student student;

    @FXML
    private Button studentAddButton;


    StudentInfoMgtApplication app = new StudentInfoMgtApplication();
    StudentFacade studentFacade = app.getStudentFacade();

    @FXML
    protected void onAddStudClicked(ActionEvent event) {
        Map<String, String> invalidFields = getInvalidFields();

        if (!invalidFields.isEmpty()) {
            displayError("Invalid input in the following fields:", invalidFields);
            return;
        }
        Student addStudent = new Student();
        addStudent.setStudentId(studentId.getText());
        addStudent.setLastName(lastName.getText());
        addStudent.setFirstName(firstName.getText());
        addStudent.setMiddleName(middleName.getText());
        addStudent.setSex(sex.getValue());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(birthday.getValue().toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long time = date.getTime();
        addStudent.setBirthday(new Timestamp(time));
        addStudent.setReligion(religion.getText());
        addStudent.setEmail(email.getText());
        addStudent.setContactNumber(contactNo.getText());
        addStudent.setAddress(address.getText());
        try {
            studentFacade.addStudent(addStudent);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

                Stage dashboardStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/StudentList.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);


                dashboardStage.initStyle(StageStyle.UNDECORATED);
                dashboardStage.show();

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    protected void handleCancelAddStudentButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        showDashboard1();
    }

    private void showDashboard1() {
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        sex.getItems().addAll("Male", "Female");
    }

    private Map<String, String> getInvalidFields() {
        Map<String, String> invalidFields = new HashMap<>();

        if (!isValidInput("Student ID", studentId.getText())) {
            invalidFields.put("Student ID", studentId.getText());
        }
        if (!isValidInput("Last Name", lastName.getText())) {
            invalidFields.put("Last Name", lastName.getText());
        }
        if (!isValidInput("First Name", firstName.getText())) {
            invalidFields.put("First Name", firstName.getText());
        }
        if (!isValidInput("Middle Name", middleName.getText())) {
            invalidFields.put("Middle Name", middleName.getText());
        }
        if (!isValidInput("Religion", religion.getText())) {
            invalidFields.put("Religion", religion.getText());
        }
        if (!isValidInput("Email", email.getText())) {
            invalidFields.put("Email", email.getText());
        }
        if (!isValidInput("Contact Number", contactNo.getText())) {
            invalidFields.put("Contact Number", contactNo.getText());
        }
        if (!isValidInput("Address", address.getText())) {
            invalidFields.put("Address", address.getText());
        }

        return invalidFields;
    }

    private boolean isValidInput(String fieldName, String input) {
        switch (fieldName) {
            case "Email":
                return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", input);

            case "Address":
                return Pattern.matches("^[a-zA-Z0-9.,\\- ]+$", input);

            case "Student ID":
                return Pattern.matches("^[a-zA-Z0-9\\-]+$", input);

            case "Last Name":
            case "First Name":
            case "Middle Name":
            case "Religion":
                return Pattern.matches("^[a-zA-Z0-9 ]+$", input);

            case "Contact Number":
                return Pattern.matches("^[0-9]{11}$", input);


            default:
                return false;
        }
    }


    private void displayError(String header, Map<String, String> invalidFields) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(header);
        StringBuilder content = new StringBuilder();
        invalidFields.forEach((field, value) -> content.append(field).append(": ").append(value).append("\n"));
        alert.setContentText(content.toString());
        alert.showAndWait();
    }
}
