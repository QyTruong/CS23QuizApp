module com.pqt.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;

    opens com.pqt.quizapp to javafx.fxml;
    exports com.pqt.quizapp;
}
