package graphics;
import java.util.List;

/**
 * The type Line.
 */
public class Line {
    /**
     * @author Nili Cohen 1998nili@gmail.com
     * This class builds a line.
     */
        private double startX;
        private double startY;
        private double endX;
        private double endY;
        private double slope;
        private double intercept;

    /**
     * This method builds a line using two given point.
     *
     * @param start the first given point
     * @param end   the second given point
     */
    public Line(Point start, Point end) {
            this.startX = start.getX();
            this.startY =  start.getY();
            this.endX =  end.getX();
            this.endY =  end.getY();
            this.slope = (this.startX - this.endX == 0) ? Double.NEGATIVE_INFINITY
                    : (this.startY - this.endY) / (this.startX - this.endX);
            if (this.startX >= this.endX) {
                this.intercept = (this.slope == Double.NEGATIVE_INFINITY) ? Double.NEGATIVE_INFINITY
                        : startY - (slope *  startX);
            } else {
                this.intercept = (this.slope == Double.NEGATIVE_INFINITY) ? Double.NEGATIVE_INFINITY
                        : endY - (slope *  endX);
            }
        }

    /**
     * This method builds a line using four given values.
     *
     * @param x1 the first given value
     * @param y1 the second given value
     * @param x2 the third given value
     * @param y2 the fourth given value
     */
    public Line(double x1, double y1, double x2, double y2) {
            this.startX = x1;
            this.startY = y1;
            this.endX = x2;
            this.endY = y2;
            // if the subtraction of the 2 x's is zero, we're not allowed to divide by it, so we return null
            this.slope = (x1 - x2 == 0) ? Double.NEGATIVE_INFINITY : ((y1 - y2) / (x1 - x2));
            if (this.startX >= this.endX) {
                this.intercept = (this.slope == Double.NEGATIVE_INFINITY) ? Double.NEGATIVE_INFINITY
                        : startY - (slope *  startX);
            } else {
                this.intercept = (this.slope == Double.NEGATIVE_INFINITY) ? Double.NEGATIVE_INFINITY
                        : endY - (slope *  endX);
            }
        }

    /**
     * This method calculate the length of this line.
     *
     * @return the length of the line
     */
    public double length() {
            return Math.sqrt(((startX - endX) * (startX - endX)) + ((startY - endY) * (startY - endY)));
        }

    /**
     * This method calculate the middle point of this line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
            double x = ((startX + endX) / 2);
            double y = ((startY + endY) / 2);
            Point middle = new Point(x, y);
            return middle;
        }

    /**
     * This method finds the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
            Point start = new Point(startX, startY);
            return start;
        }

    /**
     * This method finds the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
            Point end = new Point(endX, endY);
            return end;
        }

    /**
     * This method checks if this line and the other lines intersect.
     *
     * @param other the line compered to
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
            return intersectionWith(other) != null ? true : false;
        }

    /**
     * This method finds the intersection point if this line and the other lines intersect.
     *
     * @param other the line compered to.
     * @return the intersection point if the lines have only one point of intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
            boolean isXIntersectThis = false, isYIntersectThis = false,
                    isXIntersectOther = false, isYIntersectOther = false;
            double x = Double.NEGATIVE_INFINITY, y = Double.NEGATIVE_INFINITY;
            if (new Double(this.slope).compareTo(new Double(other.slope)) == 0) {
                double thisYMin = min(this.startY, this.endY);
                double otherYMin = min(other.startY, other.endY);
                if (thisYMin >= otherYMin) {
                    if (new Double(thisYMin).compareTo(new Double(max(other.startY, other.endY))) == 0) {
                        if (new Double(this.start().getX()).compareTo(new Double(other.start().getX())) == 0) {
                            x = this.startX;
                            y = thisYMin;
                            isXIntersectThis = true;
                            isYIntersectThis = true;
                            isXIntersectOther = true;
                            isYIntersectOther = true;
                        }

                    } else {
                        return null;
                    }
                } else {
                    // the other line is higher, now check if this's highest Y is the other lines lowest Y
                    double thisYMax = max(this.startY, this.endY);
                    if (new Double(thisYMax).compareTo(new Double(otherYMin)) == 0) {
                        if (new Double(this.start().getX()).compareTo(new Double(other.start().getX())) == 0) {
                            x = max(this.startX, this.endX);
                            y = otherYMin;
                            isXIntersectThis = true;
                            isYIntersectThis = true;
                            isXIntersectOther = true;
                            isYIntersectOther = true;
                        }

                    } else {
                        return null;
                    }
                }
            } else {
                if (this.slope == Double.NEGATIVE_INFINITY)  {
                    // when slope is Double.NEGATIVE_INFINITY, it's a vertical line.
                    //So startX and endX are the same, and we just have to check if it intercepts the other line
                    if (this.startX <= max(other.startX, other.endX)
                            && (this.startX >= min(other.startX, other.endX))) {
                        // now check the Y's intersection
                        double y1 = (other.slope * this.startX) + other.intercept;
                        if (this.checkPointOnLine(new Point(this.startX, y1))) {
                            x = this.startX;
                            y = (other.slope * x) + other.intercept;
                            isXIntersectThis = true;
                            isYIntersectThis = true;
                            isXIntersectOther = true;
                            isYIntersectOther = true;
                        }
                    }
                } else {
                    if (other.slope == Double.NEGATIVE_INFINITY) {
                        // when slope is Double.NEGATIVE_INFINITY, it's a vertical line.
                        //So startX and endX are the same, and we just have to check if it intercepts the this line
                        if (max(other.startX, other.endX)  <= max(this.startX, this.endX)
                                && (min(other.startX, other.endX) >= min(this.startX, this.endX))) {
                            double y1 = (this.slope * other.startX) + this.intercept;
                            if (other.checkPointOnLine(new Point(other.startX, y1))) {
                                x = other.startX;
                                y = (this.slope * x) + this.intercept;
                                isXIntersectThis = true;
                                isYIntersectThis = true;
                                isXIntersectOther = true;
                                isYIntersectOther = true;
                            }
                        }
                    } else {
                        x = ((other.intercept - this.intercept) / (this.slope - other.slope));
                        y = ((this.slope * x) + this.intercept);
                        // check if the point of intersection is on the first line
                        if (this.startX < this.endX) {
                            if ((x >= this.startX) && (x <= this.endX)) {
                                isXIntersectThis = true;
                            }
                        } else {
                            if ((x <= this.startX) && (x >= this.endX)) {
                                isXIntersectThis = true;
                            }
                        }
                        if (this.startY < this.endY) {
                            if ((y >= this.startY) && (y <= this.endY)) {
                                isYIntersectThis = true;
                            }
                        } else {
                            if ((y <= this.startY) && (y >= this.endY)) {
                                isYIntersectThis = true;
                            }
                        }
                        // check if the point of intersection is on the first line
                        if (other.startX < other.endX) {
                            if ((x >= other.startX) && (x <= other.endX)) {
                                isXIntersectOther = true;
                            }
                        } else {
                            if ((x <= other.startX) && (x >= other.endX)) {
                                isXIntersectOther = true;
                            }
                        }
                        if (other.startY < other.endY) {
                            if ((y >= other.startY) && (y <= other.endY)) {
                                isYIntersectOther = true;
                            }
                        } else {
                            if ((y <= other.startY) && (y >= other.endY)) {
                                isYIntersectOther = true;
                            }
                        }
                    }
                }
            }
            // after we finish all claculations, we can create the point
            Point intersection = new Point(x, y);
            // only if intersects both lines return it
            if (isXIntersectThis && isYIntersectThis && isXIntersectOther && isYIntersectOther) {
                return intersection;
            }

            return null;
        }

    /**
     * This method checks if the lines are equals.
     *
     * @param other the given point
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
            return ((this.slope == other.slope) && (this.end() == other.end()) && (this.length() == other.length()))
                    ? true : false;
        }

    /**
     * This method finds the maximum number.
     *
     * @param a the first given value
     * @param b the second given value
     * @return the maximum number
     */
    public double max(double a, double b) {
            return (a > b) ? a : b;
        }

    /**
     * This method finds the minimum number.
     *
     * @param a the first given value
     * @param b the second given value
     * @return the minimum number
     */
    public double min(double a, double b) {
            return (a < b) ? a : b;
        }

    /**
     * Check point on line boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean checkPointOnLine(Point p) {
            if ((p.getX() < min(startX, endX)) || (p.getX() > max(startX, endX))) {
                return false;
            }
            if ((p.getY() < min(startY, endY)) || (p.getY() > max(startY, endY))) {
                return false;
            }
            return true;
        }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
        public Point closestIntersectionToStartOfLine(Rectangle rect) {
            List<Point> intersection = rect.intersectionPoints(this);
            if (intersection.size() == 0) {
              return null;
            }
            if (intersection.size() == 1) {
                return intersection.get(0);
            }
            double distance1 = (intersection.get(0)).distance(this.start());
            double distance2 = (intersection.get(1)).distance(this.start());
            double distance = min(distance1, distance2);
            if (distance == distance1) {
                return intersection.get(0);
            }
            return intersection.get(1);

        }
    }


