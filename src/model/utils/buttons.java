package model.utils;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;

public class buttons {
	  // Cria um bot�o com o �cone svg dentro
	  public static Button createIconButton(String svgAbsolutePath, int size) {
	    SVGPath path = new SVGPath();
	    path.setContent(svgAbsolutePath);
	    Bounds bounds = path.getBoundsInLocal();

	    // scale to size size x size (max)
	    double scaleFactor = size / Math.max(bounds.getWidth(), bounds.getHeight());
	    path.setScaleX(scaleFactor);
	    path.setScaleY(scaleFactor);
	    //path.getStyleClass().add(colorClassName); // define a cor do �cone

	    Button button = new Button();
	    button.setPickOnBounds(true); // Garente que o bot�o ter� o fundo transparente
	    button.setGraphic(path); // inseri o �cone gerado pelo svg no bot�o
	    button.setAlignment(Pos.CENTER);
	    button.getStyleClass().add("icon-button"); // classe criada para n�o ter o fundo cinza
	    // necess�rios para o �cone ficar contido dentro do bot�o
	    button.setMaxWidth(size);
	    button.setMaxHeight(size);
	    button.setMinWidth(size);
	    button.setMinHeight(size);
	    button.setPrefWidth(size);
	    button.setPrefHeight(size);
	    return button;
	  }
	}

