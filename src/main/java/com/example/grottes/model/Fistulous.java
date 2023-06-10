package com.example.grottes.model;

import com.example.grottes.ConcretionApplication;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Fistulous {
    private int position;
    private int height;
    private Line draw;

    public Fistulous(int position, int height) {
        this.position = position;
        this.height = height;
        this.draw = new Line(this.position, 0, this.position, this.height);
        this.draw.setStrokeWidth(8);
        this.draw.setStroke(Color.BROWN);
    }

    public void draw() {
        Platform.runLater(() -> {
            ConcretionApplication.root.getChildren().add(this.draw);
        });
    }

    public void increaseHeight(int height) {
        this.height += height;
        this.draw.setEndY(this.height);
    }

    public int getHeight() {
        return height;
    }

    public int getPosition() {
        return position;
    }

    public Line getDraw() {
        return draw;
    }
}
