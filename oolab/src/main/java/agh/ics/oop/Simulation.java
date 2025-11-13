package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> moves;


    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        this.moves = moves;
        this.animals = new ArrayList<>();

        for (Vector2d position : positions) {
            this.animals.add(new Animal(position));
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

            currentAnimal.move(direction);

            System.out.println("Zwierzę " + (animalIndex + 1) + " : " + currentAnimal);
        }
    }


    public List<Animal> getAnimals() {
        return List.copyOf(animals);
    }
}