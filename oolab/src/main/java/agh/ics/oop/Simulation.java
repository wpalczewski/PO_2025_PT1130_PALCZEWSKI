package agh.ics.oop;

import agh.ics.oop.model.*;


import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(WorldMap map, List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            Animal animal = new Animal(position);
            try {
                this.map.place(animal);
                this.animals.add(animal);
            } catch (IncorrectPositionException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void run() {
        int numAnimals = animals.size();
        if (numAnimals == 0) {
            return;
        }
        for (int i = 0; i < moves.size(); i++) {
            int animalIndex = i % numAnimals;
            Animal currentAnimal = animals.get(animalIndex);
            MoveDirection direction = moves.get(i);

            this.map.move(currentAnimal, direction);
        }
    }


    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}