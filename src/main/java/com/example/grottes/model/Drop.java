package com.example.grottes.model;

import com.example.grottes.ConcretionApplication;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Goutte d'eau
 */
public class Drop {
    private double diameter = 3.0;
    private int x;
    private int y;
    private double weight;

    private Circle circle;


    // Poids minimum d'une goutte en grammes
    final static double maxWeight = 0.08;

    public Drop(int position) {
        this.weight = new Random().nextDouble(0.1, 0.5);
        this.x = new Random().nextInt(position);
        this.y = 0;
        this.circle = new Circle(this.x, y, this.weight + 4);
        this.circle.setStroke(Color.LIGHTBLUE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increaseSize(double weight) {
        this.weight += weight;
    }

    public double getDiameter() {
        return diameter;
    }

    public boolean isMaxWeight() {
        return this.weight >= maxWeight;
    }

    public void draw() {
        Platform.runLater(() -> {
            ConcretionApplication.root.getChildren().add(this.circle);
        });
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public Circle getCircle() {
        return this.circle;
    }


    public void fall() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().add(
                new javafx.animation.KeyFrame(
                        javafx.util.Duration.millis(1000),
                        new javafx.animation.KeyValue(this.circle.centerYProperty(), ConcretionApplication.root.getHeight())
                )
        );
        timeline.play();
    }
}
