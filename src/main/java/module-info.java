module com.fatec.atendimentolocalhost {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.fatec.atendimentolocalhost to javafx.fxml;
    exports com.fatec.atendimentolocalhost;
}
