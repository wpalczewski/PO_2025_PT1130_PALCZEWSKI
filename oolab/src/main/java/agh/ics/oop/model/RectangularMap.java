package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {

    private final Map<Vector2d, Animal> animals;
    private final int width;
    private final int height;

    private final Vector2d lowerLeftBound;
    private final Vector2d upperRightBound;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.animals = new HashMap<>();

        this.lowerLeftBound = new Vector2d(0, 0);
        this.upperRightBound = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean withinBounds = position.follows(lowerLeftBound) && position.precedes(upperRightBound);
        return withinBounds && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();

        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animals.containsValue(animal)) {
            return;
        }
        Vector2d oldPosition = animal.getPosition();

        animal.move(direction, this);

        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeftBound, upperRightBound);
    }
}