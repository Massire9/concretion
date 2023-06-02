package com.example.grottes.model;

import com.example.grottes.HelloApplication;

import java.util.ArrayList;
import java.util.Optional;

public class Cave {
    ArrayList<Drop> drops;

    private final static int mergeRange = 3;
    private static Cave instance;

    private Cave() {
        this.drops = new ArrayList<>();
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
        Optional<Drop> searchDrop = this.drops.stream().filter(d -> Math.abs(d.getPosition() - drop.getPosition()) < mergeRange).findFirst();

        // Si une goutte est trouvée, on la fusionne
        if (searchDrop.isPresent()) {
            int index = this.drops.indexOf(searchDrop.get());
            searchDrop.get().increaseSize(drop.getDiameter());
            if (searchDrop.get().isMaxWeight()) {
                this.drops.remove(index);
                System.out.println("Drop fall at " + drop.getPosition());
                //TODO: Animer la goutte qui tombe + ajouter stalagmite...
                return;
            }
            this.drops.set(index, searchDrop.get());
            //TODO: Animer ajout de goute
            System.out.println("Merged drop at " + drop.getPosition());


            return;
        }

        // Sinon, on ajoute la goutte
        System.out.println("New drop at " + drop.getPosition());
        drops.add(drop);
    }

    public ArrayList<Drop> getDrops() {
        return drops;
    }
}
