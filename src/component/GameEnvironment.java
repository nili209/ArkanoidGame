package component;
import graphics.Line;
import graphics.Point;
import graphics.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    /**
     * The Collidables.
     */
    private List<Collidable> collidables;
    private String nameOfLine;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.nameOfLine;
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> intersection = new ArrayList<Point>();
        List<String> names = new ArrayList<String>();
        int index;
        List<Integer> indexes = new ArrayList<Integer>();
        List<Collidable> newList = new ArrayList<Collidable>(this.collidables);
        for (int i = 0; i < newList.size(); i++) {
            Rectangle currRec = newList.get(i).getCollisionRectangle();
            Point point = trajectory.intersectionWith(currRec.getRight());
            savePointData(intersection, indexes, names, i, point, "Right");
            point = trajectory.intersectionWith(currRec.getLeft());
            savePointData(intersection, indexes, names, i, point, "Left");
            point = trajectory.intersectionWith(currRec.getUp());
            savePointData(intersection, indexes, names, i, point, "Up");
            point = trajectory.intersectionWith(currRec.getDown());
            savePointData(intersection, indexes, names, i, point, "Down");

        }
        if (intersection.size() == 0) {
            return null;
        }
        Point collision = findCollision(trajectory, intersection);
        int collidablesIndex = 0;
        for (int i = 0; i < intersection.size(); i++) {
             if (collision.equals(intersection.get(i))) {
                 collidablesIndex = indexes.get(i);
                 this.nameOfLine = names.get(i);
             }
        }
        return new CollisionInfo(collision, collidables.get(collidablesIndex));
    }

    /**
     * Save point data.
     *
     * @param intersection the intersection
     * @param indexes      the indexes
     * @param names        the names
     * @param i            the
     * @param p            the point
     * @param side         the side
     */
    public void savePointData(List<Point> intersection, List<Integer> indexes, List<String> names,
                              int i, Point p, String side) {
        if (p != null) {
            intersection.add(p);
            indexes.add(i);
            names.add(side);
        }
    }

    /**
     * Find collision point.
     *
     * @param trajectory  the trajectory
     * @param itersection the itersection
     * @return the point
     */
    public Point findCollision(Line trajectory, List<Point> itersection) {
        double d = -1;
        int index = -1;
        int i;
        for (i = 0; i < itersection.size(); i++) {
            double c = trajectory.start().distance(itersection.get(i));
            if (d == -1 || (c < d)) {
                d = c;
                index = i;
            }
        }
        return itersection.get(index);
    }


}
