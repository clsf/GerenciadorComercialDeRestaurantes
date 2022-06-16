module GerenciadorComercialDeRestaurantes {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires itextpdf;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls,itextpdf;
	opens gui to javafx.graphics, javafx.fxml, javafx.controls,itextpdf;

	
	
	exports gui;
	exports model.entities;
	exports model.utils;
	exports model.enums;
	exports model.exceptions;
	
}
