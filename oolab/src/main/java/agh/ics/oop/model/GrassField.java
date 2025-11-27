package agh.ics.oop.model;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        int range = (int) Math.sqrt(grassCount * 10);
        Random random = new Random();

        for (int i = 0; i < grassCount; i++) {
            Vector2d pos;
            boolean placed = false;

            while (!placed) {
                int x = random.nextInt(range + 1);
                int y = random.nextInt(range + 1);
                pos = new Vector2d(x, y);

                if (!isOccupied(pos)) {
                    grasses.put(pos, new Grass(pos));
                    placed = true;
                }
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement animal = super.objectAt(position);
        if (animal != null) {
            return animal;
        }
        return grasses.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }

    @Override
    public Boundary getCurrentBounds() {

        Set<Vector2d> allPositions = new HashSet<>(animals.keySet());
        allPositions.addAll(grasses.keySet());

        if (allPositions.isEmpty()) {
            return new Boundary(new Vector2d(0, 0), new Vector2d(0, 0));
        }

        Iterator<Vector2d> it = allPositions.iterator();
        Vector2d first = it.next();
        Vector2d minBound = first;
        Vector2d maxBound = first;

        while (it.hasNext()) {
            Vector2d pos = it.next();
            minBound = minBound.lowerLeft(pos);
            maxBound = maxBound.upperRight(pos);
        }

        return new Boundary(minBound, maxBound);
    }
}
