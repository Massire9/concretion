package com.example.grottes;

import com.example.grottes.scene.Cave;
import com.example.grottes.schedule.DropScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class HelloApplication extends Application {
    public static Map<String, Timer> tasks = new HashMap<>();
    public static Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) {
        int width = Cave.width;
        int height = Cave.height;
        int caveHeight = 200;


        // Create the upper part of the cave
        Rectangle upperCave = new Rectangle(0, 0, width, caveHeight);
        upperCave.setFill(Color.GRAY);

        // Create the lower part of the cave
        Rectangle lowerCave = new Rectangle(0, caveHeight, width, height - caveHeight);
        lowerCave.setFill(Color.BLACK);

        root.getChildren().addAll(upperCave, lowerCave);

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
        tasks.put("drop_generation", DropScheduler.scheduleDropGeneration());
    }
    public static void main(String[] args) {
        launch(args);
    }
}