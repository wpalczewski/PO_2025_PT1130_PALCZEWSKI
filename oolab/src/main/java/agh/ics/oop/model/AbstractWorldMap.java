package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    private final List<MapChangeListener> observers = new ArrayList<>();
    private final UUID id = UUID.randomUUID();

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal placed at " + animal.getPosition());
        } else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animals.containsValue(animal)) return;

        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            mapChanged("Animal moved from " + oldPosition + " to " + newPosition);
        }
    }
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public String toString() {
        Boundary boundary = getCurrentBounds();
        return visualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }

    public abstract Boundary getCurrentBounds();

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    private void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
}