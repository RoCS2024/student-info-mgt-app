package com.student.user.app.controllers.dashboard;
import com.student.information.management.appl.facade.student.StudentFacade;
import com.student.information.management.appl.facade.student.impl.StudentFacadeImpl;
import com.student.information.management.appl.model.student.Student;


import com.student.user.app.controllers.item.UpdateStudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;


import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class StudentListController implements Initializable {

    @FXML
    TableView table;

    @FXML
    private ComboBox<String> comboBox;

    private StudentFacade studentFacade = new StudentFacadeImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.getItems().clear();

        List<Student> students = studentFacade.getAllStudents();
        table.getItems().addAll(students);

        ObservableList<Student> data = FXCollections.observableArrayList(students);
        table.setItems(data);

        TableColumn studentIdColumn = new TableColumn("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentIdColumn.getStyleClass().addAll("student-id-column");

        TableColumn lastNameColumn = new TableColumn(" Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.getStyleClass().addAll("lastName-column");

        TableColumn firstNameColumn = new TableColumn(" First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.getStyleClass().addAll("firstName-column");

        TableColumn middleNameColumn = new TableColumn(" Middle Name");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleNameColumn.getStyleClass().addAll("middleName-column");

        TableColumn actionColumn = new TableColumn("ACTION");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(" "));
        actionColumn.getStyleClass().addAll("action-column");

        Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFactory
                = //
                new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Student, String> param) {
                        final TableCell<Student, String> cell = new TableCell<Student, String>() {
                            final Button editButton = new Button();

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    editButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/editbutton.png"))));
                                    editButton.setOnAction(event -> {
                                        Student updateStudent = getTableView().getItems().get(getIndex());
                                        showStudentUpdate(updateStudent, (ActionEvent) event);
                                    });

                                    HBox hbox = new HBox(editButton);
                                    hbox.setSpacing(10);
                                    hbox.setAlignment(Pos.BASELINE_CENTER);
                                    setGraphic(hbox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(cellFactory);
        table.getColumns().addAll(studentIdColumn, lastNameColumn, firstNameColumn, middleNameColumn, actionColumn);
    }


    @FXML
    protected void handleIconUserList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconStudList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StudentList.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleAddStudList(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddStud.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleIconLogout(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showStudentUpdate(Student updateStudent, ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            Stage editStudent = new Stage();
            editStudent.initStyle(StageStyle.UNDECORATED);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/UpdateStud.fxml"));
            AnchorPane studentViewLayout = new AnchorPane();
            studentViewLayout = loader.load();
            UpdateStudentController updateStudController = loader.getController();
            updateStudController.setStudent(updateStudent);
            Scene scene = new Scene(studentViewLayout);
            editStudent.setScene(scene);
            editStudent.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




