package com.example.grottes.model;
import com.example.grottes.HelloApplication;
import javafx.application.Platform;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Goutte d'eau
 *
 * */
public class Drop {
    private double diameter = 3.0;
    private int position;
    private double weight;

    private Circle circle;


    // Poids minimum d'une goutte en grammes
    final static double maxWeight = 0.08;

    public Drop(int position) {
        this.weight = new Random().nextDouble(0.1, 0.5);
        this.position = new Random().nextInt(position);
        this.circle = new Circle(this.position, 0, this.weight + 10);
        Platform.runLater(() -> HelloApplication.root.getChildren().add(this.circle));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
}
