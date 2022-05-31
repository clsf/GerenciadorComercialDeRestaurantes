module GerenciadorComercialDeRestaurantes {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls;
	opens gui to javafx.graphics, javafx.fxml, javafx.controls;
	
	
	exports gui;
	exports model.entities;
	
}
