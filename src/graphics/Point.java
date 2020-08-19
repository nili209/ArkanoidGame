/**
 * The type Point.
 */
package graphics;

/**
 * The type Point.
 */
public class Point {
    /**
     * @author Nili Cohen 1998nili@gmail.com
     * This class builds a point.
     */
        private double x;
        private double y;

    /**
     * This method builds a point using two given values.
     *
     * @param x the first given value
     * @param y the second given value
     */
    public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    /**
     * This method calculate the distance of this point to the other point.
     *
     * @param other the given point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
            double dx = this.x - other.getX();
            double dy = this.y - other.getY();
            return Math.sqrt((dx * dx) + (dy * dy));
        }

    /**
     * This method checks if the point are equals.
     *
     * @param other the given point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
            return  (new Double(this.x).compareTo(new Double(other.getX())) == 0)
                    && (new Double(this.y).compareTo(new Double(other.getY())) == 0) ? true : false;
        }

    /**
     * This method finds the value of x.
     *
     * @return the value of x
     */
    public double getX() {
            return this.x;
        }

    /**
     * This method finds the value of y.
     *
     * @return the value of y
     */
    public double getY() {
            return this.y;
        }
    }

