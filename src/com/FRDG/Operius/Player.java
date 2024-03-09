package com.FRDG.Operius;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Player {
    private String name;
    private char symbol;
    private Polygon arrow;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.arrow = createArrowHead();
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public Polygon getArrow() {
        return arrow;
    }

    private Polygon createArrowHead() {
        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(new Double[]{
                0.0, 0.0,
                50.0, -50.0,
                100.0, 0.0,
                50.0, -30.0});
        arrow.setStroke(Color.web("#B9C6B2"));
        arrow.setStrokeWidth(2.5);
        arrow.setFill(Color.web("#FE9F5F",0.9)); // Set the fill color to null (transparent)
        return arrow;
    }
}
