package com.student.user.app.controllers.list;

import java.time.LocalDate;

public class UserList {
    private int userid;

    private String username;

    private String entityId;

    private LocalDate dateCreated;

    private LocalDate dateModified;

    private String action;

    public UserList(int userid, String username, String entityId, LocalDate dateCreated, LocalDate dateModified, String action) {
        this.userid = userid;
        this.username = username;
        this.entityId = entityId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.action = action;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
