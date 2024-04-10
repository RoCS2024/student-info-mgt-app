package com.student.user.app.controllers.list;

public class StudentList {
    private String studentId;
    private String name;
    private String action;

    public StudentList(String studentId, String name, String action) {
        this.studentId = studentId;
        this.name = name;
        this.action = action;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
