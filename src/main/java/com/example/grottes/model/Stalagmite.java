package com.example.grottes.model;

import com.example.grottes.ConcretionApplication;
import javafx.application.Platform;
import javafx.scene.shape.Line;

public class Stalagmite {
    private int position;
    private int height;

    private Line line;

    public Stalagmite(int position, int height) {
        this.position = position;
        this.height = height;
        this.line = new Line(this.position, 0, this.position, this.height);
    }

    public void increaseHeight(int height) {
        this.height += height;
        this.line.setEndY(this.height);

    }

    public int getHeight() {
        return height;
    }

    public int getPosition() {
        return position;
    }

    public Line getLine() {
        return line;
    }
    public void draw() {
        Platform.runLater(() -> {
            ConcretionApplication.root.getChildren().add(this.line);
        });
    }
}
