package com.example.grottes.model;

import com.example.grottes.ConcretionApplication;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Cave {
    ArrayList<Drop> drops;
    ArrayList<Fistulous> fistulous;
    ArrayList<Stalagmite> stalagmites;

    private final static int mergeRange = 1;
    private static Cave instance;

    private Cave() {
        this.drops = new ArrayList<>();
        this.fistulous = new ArrayList<>();
        this.stalagmites = new ArrayList<>();
    }

    public static Cave getInstance() {
        if (instance == null) {
            instance = new Cave();
        }
        // Récupérer l'instance de la grotte en faisant: Cave.getInstance();
        // WARNING: Cave cave = new Cave(); ne fonctionnera pas - singleton
        return instance;
    }

    public void addDrop(Drop drop) {
        // Recherche d'une goutte à fusionner
        Optional<Drop> searchDrop = this.drops.stream().filter(d -> Math.abs(d.getX() - drop.getX()) < mergeRange).findFirst();

        // Si une goutte est trouvée, on la fusionne
        if (searchDrop.isPresent()) {
            int index = this.drops.indexOf(searchDrop.get());
            searchDrop.get().increaseSize(drop.getDiameter());
            if (searchDrop.get().isMaxWeight()) {
                this.drops.remove(index);
                System.out.println("Drop fall at " + drop.getX());
                //TODO: Animer la goutte qui tombe + ajouter fistulous...
                drop.fall();

                if(this.fistulous.stream().noneMatch(f -> Math.abs(f.getPosition() - drop.getX()) <= mergeRange))
                {
                    Fistulous f = new Fistulous(drop.getX(), 10); //Ajout d'une fistule
                    f.draw();
                    this.fistulous.add(f);
                } else if(this.stalagmites.stream().anyMatch(s -> Math.abs(s.getPosition() - drop.getX()) < mergeRange)) {
                    Stalagmite s = this.stalagmites.stream().filter(st -> st.getPosition() == drop.getX()).findFirst().get();
                    s.increaseHeight(new Random().nextInt(5, 40));
                } else if (this.fistulous.stream().anyMatch(f -> Math.abs(f.getPosition() - drop.getX()) <= mergeRange)) {
                    drop.setY(this.fistulous.stream().filter(f -> Math.abs(f.getPosition() - drop.getX()) <= mergeRange).findFirst().get().getHeight());
                    drop.getCircle().setCenterY(drop.getY());
                }
                return;
            }
            this.drops.set(index, searchDrop.get());
            //TODO: Animer ajout de goute
            this.drops.get(index).getCircle().setRadius(this.drops.get(index).getDiameter() + drop.getDiameter());
            System.out.println("Merged drop at " + drop.getX());

            return;
        }

        // Sinon, on ajoute la goutte
        System.out.println("New drop at " + drop.getX());
        if(this.fistulous.stream().anyMatch(f -> Math.abs(f.getPosition() - drop.getX()) < mergeRange))
        {
            Fistulous f = this.fistulous.stream().filter(fi -> fi.getPosition() == drop.getX()).findFirst().get();
            drop.setY(f.getHeight());
            drop.getCircle().setCenterY(drop.getY());
        }
        drop.draw();
        drops.add(drop);
    }

    public ArrayList<Drop> getDrops() {
        return drops;
    }

    public void removeFistulous(int index) {
        Platform.runLater(() -> ConcretionApplication.root.getChildren().remove(this.fistulous.get(index).getDraw()));
        this.fistulous.remove(index);
    }

    public ArrayList<Fistulous> getFistulous() {
        return fistulous;
    }

    public void addStalagmite(Stalagmite stalagmite) {
        this.stalagmites.add(stalagmite);
    }
}
