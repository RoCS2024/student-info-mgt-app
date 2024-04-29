package com.student.user.app.controllers.item;

import com.student.information.management.StudentInfoMgtApplication;
import com.student.information.management.appl.facade.student.StudentFacade;
import com.student.information.management.appl.facade.student.impl.StudentFacadeImpl;
import com.student.information.management.appl.model.student.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML TextArea address;

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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
    protected void onUpdateStudOClicked(ActionEvent event){
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

        LocalDate selectedDate = birthday.getValue();
        if (selectedDate != null) {
            try {
                LocalDateTime localDateTime = selectedDate.atStartOfDay();
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                updateStudent.setBirthday(timestamp);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid date format: " + selectedDate);
                e.printStackTrace();
            }
        }

        try {
            studentFacade.updateStudent(updateStudent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

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

}
