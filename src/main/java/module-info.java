module com.example.consulta_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.consulta_javafx to javafx.fxml;
    exports com.example.consulta_javafx;
}