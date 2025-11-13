package agh.ics.oop.model;


public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    private static final Vector2d LOWER_LEFT_BOUND = new Vector2d(0, 0);
    private static final Vector2d UPPER_RIGHT_BOUND = new Vector2d(4, 4);

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }
    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = orientation.next();
            case LEFT -> this.orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d moveVector = orientation.toUnitVector();

                if (direction == MoveDirection.BACKWARD) {
                    moveVector = moveVector.opposite();
                }

                Vector2d newPosition = position.add(moveVector);

                if (isWithinBounds(newPosition)) {
                    position = newPosition;
                }
            }
        }
    }
    @Override
    public String toString() {
        return position + " " + orientation;
    }

    private boolean isWithinBounds(Vector2d position) {
        return position.follows(LOWER_LEFT_BOUND) && position.precedes(UPPER_RIGHT_BOUND);
    }
}

