module org.hugo.practicahibernatecoches {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.hugo.practicahibernatecoches to javafx.fxml;
    exports org.hugo.practicahibernatecoches;
}