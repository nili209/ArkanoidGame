package graphics;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upLeft;
    private Point downLeft;
    private Point downRight;
    private Point upRight;
    private Line up;
    private Line right;
    private Line down;
    private Line left;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upLeft = upperLeft;
        this.upRight = new Point(this.upLeft.getX() + width, this.upLeft.getY());
        this.downLeft = new Point(this.upLeft.getX(), this.upLeft.getY() + height);
        this.downRight = new Point(this.upLeft.getX() + width, this.upLeft.getY() + height);
        this.up = new Line(upLeft, upRight);
        this.right = new Line(upRight, downRight);
        this.down = new Line(downLeft, downRight);
        this.left = new Line(upLeft, downLeft);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersection = new ArrayList<Point>();
        Point intersect;
        intersect = line.intersectionWith(this.down);
        if (intersect != null) {
            intersection.add(intersect);
        }
        intersect = line.intersectionWith(this.left);
        if (intersect != null) {
            intersection.add(intersect);
        }
        intersect = line.intersectionWith(this.up);
        if (intersect != null) {
            intersection.add(intersect);
        }
        intersect = line.intersectionWith(this.right);
        if (intersect != null) {
            intersection.add(intersect);
        }
        return intersection;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upLeft;

    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return this.upRight;

    }

    /**
     * Gets down left.
     *
     * @return the down left
     */
    public Point getDownLeft() {
        return this.downLeft;

    }

    /**
     * Gets down right.
     *
     * @return the down right
     */
    public Point getDownRight() {
        return this.downRight;

    }

    /**
     * Gets up.
     *
     * @return the up
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * Gets down.
     *
     * @return the down
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int width1 = (int) this.getWidth();
        int height1 = (int) this.getHeight();
        surface.setColor(Color.YELLOW.brighter());
        surface.fillRectangle(x, y, width1, height1);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width1, height1);


    }
}

