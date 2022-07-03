/*******************************************************************************
Autor: Cláudia Inês Sales Freitas
Componente Curricular: MI de Programação II
Concluido em: 26/06/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

module GerenciadorComercialDeRestaurantes {

	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires itextpdf;
	requires org.junit.jupiter.api;
	requires junit;
	requires org.junit.platform.suite.api;

	opens application to javafx.graphics, javafx.fxml, javafx.controls,itextpdf;
	opens gui.controller to javafx.graphics, javafx.fxml, javafx.controls,itextpdf;
	opens model.gerenciadorTeste to org.junit.jupiter.api,junit;

	exports gui.controller;	
	
	exports model.entities;
	exports model.utils;
	exports model.enums;
	exports model.exceptions;
	exports model.gerenciadorTeste;
	
}
