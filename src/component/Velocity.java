package component;

import graphics.Point;

/**
 * The type Velocity.
 *
 * @author Nili Cohen 1998nili@gmail.com Velocity specifies the change in position on the `x` and the `y` axes..
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * This method creates the velocity using two given values.
     *
     * @param dx the first given value
     * @param dy the second given value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method finds the x value of the velocity.
     *
     * @return the value of x
     */
    public double getX() {
        return this.dx;
    }

    /**
     * This method finds the y value of the velocity.
     *
     * @return the value of y
     */
    public double getY() {
        return this.dy;
    }

    /**
     * This method Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the first given value
     * @return newP the new point after the changes
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point newP = new Point(newX, newY);
        return  newP;
    }

    /**
     * This method takes the given angle and speed and calculates the new velocity.
     *
     * @param angle the angle of the ball
     * @param speed the speed of the ball
     * @return a new velocity defined according to the speed and the angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle -= 90;
        angle *= Math.PI / (180);
        double dx = Math.cos(angle) * speed;
        double dy = Math.sin(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Sets dx.
     *
     * @param x the dx
     */
    public void setDx(double x) {
        this.dx = x;
    }

    /**
     * Sets dy.
     *
     * @param y the dy
     */
    public void setDy(double y) {
        this.dy = y;
    }
}
//        angle = Math.toRadians(angle);
//        double dx = Math.sin(angle) * speed;
//        double dy = (-1) * Math.cos(angle) * speed;