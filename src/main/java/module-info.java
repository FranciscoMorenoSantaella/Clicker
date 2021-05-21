module santaellamorenofrancisco.Clicker {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires com.jfoenix;
	requires javafx.graphics;
	requires javafx.base;
	
    opens santaellamorenofrancisco.Clicker to javafx.fxml,java.lang;
    opens santaellamorenofrancisco.Clicker.Modelo to javafx.base;
    exports santaellamorenofrancisco.Clicker;
}