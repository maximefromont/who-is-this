module com.app.wit {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.app.wit to javafx.fxml;
    exports com.app.wit;
    exports com.app.wit.View;
    opens com.app.wit.View to javafx.fxml;
}