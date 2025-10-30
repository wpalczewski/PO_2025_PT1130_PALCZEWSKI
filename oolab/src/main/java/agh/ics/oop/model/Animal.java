package agh.ics.oop.model;


public class Animal {
    private MapDirection orientation;
    private Vector2d position;


    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }


    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }


    @Override
    public String toString() {
        return position + " " + orientation;
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
                    moveVector = new Vector2d(-moveVector.getX(), moveVector.getY());
                }
                Vector2d newPosition = position.add(moveVector);
                if (newPosition.getX() >= 0 && newPosition.getX() <= 4
                        && newPosition.getY() >= 0 && newPosition.getY() <= 4) {
                    position = newPosition;
                }
            }
        }
    }

    public Vector2d getPosition() {
        return this.position;
    }
    public MapDirection getOrientation() {
        return this.orientation;
        }
}

