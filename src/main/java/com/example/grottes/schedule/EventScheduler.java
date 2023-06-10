package com.example.grottes.schedule;

import com.example.grottes.model.Cave;
import com.example.grottes.model.Fistulous;
import com.example.grottes.model.Stalagmite;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EventScheduler {

    public static Timer FistulousEvent() {
        TimerTask task = new TimerTask() {
            public void run() {

                Random random = new Random();
                ArrayList<Fistulous> fistulous = Cave.getInstance().getFistulous();
                for (int i = 0; i < Cave.getInstance().getFistulous().size(); i++) {
                    try {
                        if (random.nextInt(100) <= 10) {
                            //10% chance de se casser à cause du vent
                            Cave.getInstance().removeFistulous(i);
                            System.out.println("Wind broke a fistulous at : " + fistulous.get(i).getPosition());
                        }
                        if (random.nextInt(100) <= 10) {
                            //10% de chance de tomber à cause de son propre poids
                            Cave.getInstance().removeFistulous(i);
                            System.out.println("Fistulous broke because of his self weight : " + fistulous.get(i).getPosition());
                        }
                        int finalI = i;
                        if (Cave.getInstance().getDrops().stream().anyMatch(drop -> drop.getX() == fistulous.get(finalI).getPosition()) && random.nextInt(100) <= 10) {
                            //10% de chance de se boucher à cause d'une goutte
                            Cave.getInstance().removeFistulous(i);
                            Stalagmite stalagmite = new Stalagmite(fistulous.get(i).getPosition(), 10);
                            Cave.getInstance().addStalagmite(stalagmite);
                            stalagmite.draw();
                            System.out.println("Fistulous broke because of a drop : " + fistulous.get(i).getPosition() + ", stalagmite have been created");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        throw e;
                    }
                }
            }
        };

        Timer taskManager = new Timer();

        taskManager.scheduleAtFixedRate(task, 0, 10000);

        return taskManager;
    }
}
