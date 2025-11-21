package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeftBound;
    private final Vector2d upperRightBound;

    public RectangularMap(int width, int height) {
        this.lowerLeftBound = new Vector2d(0, 0);
        this.upperRightBound = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeftBound)
                && position.precedes(upperRightBound)
                && !super.isOccupied(position);
    }
    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeftBound, upperRightBound);
    }
}