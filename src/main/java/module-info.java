module com.mycompany.locadoraatendimento {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.locadoraatendimento to javafx.fxml;
    exports com.mycompany.locadoraatendimento;
}
