package com.example.grottes.schedule;

import com.example.grottes.model.Drop;
import com.example.grottes.model.Cave;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DropScheduler {
    public static Timer scheduleDropGeneration() {
        TimerTask task = new TimerTask() {
            public void run() {
                double nbDrop = new Random().nextInt(0, 3);

                for (int i = 0; i < nbDrop; i++) {
                    Drop drop = new Drop(new Random().nextInt(1, com.example.grottes.scene.Cave.width));
                    Cave.getInstance().addDrop(drop);
                }
            }
        };

        Timer taskManager = new Timer();
        taskManager.scheduleAtFixedRate(task, 0, 1000);

        return taskManager;
    }


}
