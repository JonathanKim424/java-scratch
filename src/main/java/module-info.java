module com.example.comboxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires org.mybatis;

    opens com.example.comboxdemo to javafx.fxml;
    exports com.example.comboxdemo;
}