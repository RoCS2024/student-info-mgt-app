package com.student.user.app.controllers.item;

import com.student.information.management.StudentInfoMgtApplication;
import com.student.information.management.appl.facade.student.StudentFacade;
import com.student.information.management.appl.facade.student.impl.StudentFacadeImpl;
import com.student.information.management.appl.model.student.Student;
import com.student.information.management.data.student.dao.StudentDao;
import com.student.information.management.data.student.dao.impl.StudentDaoImpl;
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

    private String getInvalidInputMessage() {
        String regex = "[a-zA-Z0-9]+@gmail\\.com";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -17);
        Date minAllowedBirthday = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date selectedBirthday = null;
        try {
            selectedBirthday = dateFormat.parse(birthday.getValue().toString());
        } catch (ParseException e) {
            return "Invalid input for Birthday. Please select a valid date.";
        }
        if (selectedBirthday.after(minAllowedBirthday)) {
            return "Invalid input for Birthday. Please select a date at least 17 years ago.";
        }
        if (!email.getText().matches(regex)) {
            return "Invalid input for Email. Please enter alphanumeric characters only.";
        }
        return null;
    }
    @FXML
    protected void onAddStudClicked(ActionEvent event) {
        try {
            String invalidInputMessage = getInvalidInputMessage();
            if (invalidInputMessage != null) {
                showAlert("Invalid Input", invalidInputMessage);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    }private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

}
