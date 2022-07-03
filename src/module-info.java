/*******************************************************************************
Autor: Cl�udia In�s Sales Freitas
Componente Curricular: MI de Programa��o II
Concluido em: 26/06/2022
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
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
