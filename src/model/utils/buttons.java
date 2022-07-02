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
package model.utils;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;
/**
 * Classe para criar botões com ícones dentro
 * @author https://felypeganzert.medium.com/javafx-bot%C3%A3o-com-%C3%ADcone-a-partir-de-svg-e-em-tableview-2122d839fa2d
 *
 */
public class buttons {
	  // Cria um botão com o ícone svg dentro
	  public static Button createIconButton(String svgAbsolutePath, int size) {
	    SVGPath path = new SVGPath();
	    path.setContent(svgAbsolutePath);
	    Bounds bounds = path.getBoundsInLocal();

	    // scale to size size x size (max)
	    double scaleFactor = size / Math.max(bounds.getWidth(), bounds.getHeight());
	    path.setScaleX(scaleFactor);
	    path.setScaleY(scaleFactor);
	    //path.getStyleClass().add(colorClassName); // define a cor do ícone

	    Button button = new Button();
	    button.setPickOnBounds(true); // Garente que o botão terá o fundo transparente
	    button.setGraphic(path); // inseri o ícone gerado pelo svg no botão
	    button.setAlignment(Pos.CENTER);
	    button.getStyleClass().add("icon-button"); // classe criada para não ter o fundo cinza
	    // necessários para o ícone ficar contido dentro do botão
	    button.setMaxWidth(size);
	    button.setMaxHeight(size);
	    button.setMinWidth(size);
	    button.setMinHeight(size);
	    button.setPrefWidth(size);
	    button.setPrefHeight(size);
	    return button;
	  }
	}

