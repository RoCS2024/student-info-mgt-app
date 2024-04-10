module com.student.user.app.studentuserapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires umsv2;
    requires sims;

   requires org.apache.logging.log4j.slf4j2.impl;

    opens com.student.user.app to javafx.fxml;
    opens com.student.user.app.controllers.item to javafx.fxml;
    opens com.student.user.app.controllers.dashboard to javafx.fxml;
    opens com.student.user.app.controllers.main to javafx.fxml;

    exports com.student.user.app;
    exports com.student.user.app.controllers.item;
    exports com.student.user.app.controllers.dashboard;
    exports com.student.user.app.controllers.main;

}