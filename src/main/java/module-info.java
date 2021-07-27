module com.nickrexrode.DesktopAssistant {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires org.yaml.snakeyaml;

    opens com.nickrexrode to javafx.fxml;
    opens com.nickrexrode.gui to javafx.fxml;
    exports com.nickrexrode;

}