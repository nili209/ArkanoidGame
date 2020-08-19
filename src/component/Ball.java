package component;
import biuoop.DrawSurface;
import graphics.Line;
import graphics.Point;
import java.awt.Color;

/**
 * The type Ball.
 *
 * @author Nili Cohen 1998nili@gmail.com This class builds a ball.
 */
public class Ball implements Sprite {
    private GameEnvironment myGame;
    private Line trajectory;
    private Point startRange;
    private int height;
    private int width;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;

    /**
     * This method builds a ball using a point, the radius and a specific color.
     *
     * @param center the middle point inside the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.startRange = new Point(0, 0);
        this.height = 0;
        this.width = 0;
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.trajectory = new Line(startRange, startRange);
        this.myGame = new GameEnvironment();
    }

    /**
     * This method builds a ball using two given values that will be the center point, the radius and a specific color.
     *
     * @param x     the x value of the center
     * @param y     the y value of the center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.startRange = new Point(0, 0);
        this.height = 0;
        this.width = 0;
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.trajectory = new Line(startRange, startRange);
        this.myGame = new GameEnvironment();
    }

    /**
     * Sets range.
     *
     * @param p the p
     * @param w the w
     * @param h the h
     */
    public void setRange(Point p, int w, int h) {
        this.startRange = p;
        this.height = h;
        this.width = w;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(GameEnvironment game) {
        this.myGame = game;

    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets start range.
     *
     * @return the start range
     */
    public Point getStartRange() {
        return this.startRange;

    }

    /**
     * This method finds the x value of the center point.
     *
     * @return the x value of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * This method finds the y value of the center point.
     *
     * @return the y value of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * This method finds the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This method finds the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * This method draw the ball on the given DrawSurface using the color and its parameters.
     *
     * @param surface the background of the animation
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = this.getX();
        int y = this.getY();
        surface.fillCircle(x, y, this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.radius);
    }

    /**
     * This method changes the velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * This method changes the velocity.
     *
     * @param dx the new x value velocity
     * @param dy the new y value velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * This method finds the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * This method creates the next move of the ball.
     */
    public void moveOneStep() {
        //checker();
        double x = this.getX();
        double y = this.getY();
        //this.center = this.getVelocity().applyToPoint(this.center);

        CollisionInfo collision = this.myGame.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = trajectory.end();
        } else {
            Point collisionPoint = collision.collisionPoint();
            //System.out.println(collisionPoint.getX() + ", "+ collisionPoint.getY() );
            switch (this.myGame.getName()) {
                case "Up":
                    y = collisionPoint.getY() - this.getSize() - 1;
                    break;
                case "Down":
                    y = collisionPoint.getY() + this.getSize() + 1;
                    break;
                case "Right":
                    x = collisionPoint.getX() + this.getSize() + 1;
                    break;
                case "Left":
                    x = collisionPoint.getX() - this.getSize() - 1;
                    break;
                default:
                    break;
            }
            this.center = new Point(x, y);
            this.setVelocity(collision.collisionObject().hit(this, collisionPoint, this.getVelocity()));

        }
       setTrajectory();
    }

    /**
     * Checker.
     */
    public void checker() {
        double x = this.getX();
        double y = this.getY();
        //System.out.println("x="+x+", y="+y);
        double startX = this.trajectory.start().getX();
        double startY = this.trajectory.start().getY();
        double endX = this.trajectory.end().getX();
        double endY = this.trajectory.end().getY();
        if ((x < GameLevel.WIDTH_OF_FRAME) || (x > GameLevel.WIDTH_OF_SCREEN - GameLevel.WIDTH_OF_FRAME)) {
            System.out.println("Trajectory - " + "(" + startX + ", " + startY + "), (" + endX + ", " + endY + ")");
        }
        if ((y < GameLevel.WIDTH_OF_FRAME) || (y > GameLevel.HEIGHT_OF_SCREEN - GameLevel.WIDTH_OF_FRAME)) {
            System.out.println("Trajectory - " + "(" + startX + ", " + startY + "), (" + endX + ", " + endY + ")");
        }
    }

    /**
     * Sets trajectory.
     */
    public void setTrajectory() {
        double x = this.getX();
        double y = this.getY();
        double dx = this.velocity.getX();
        double dy = this.velocity.getY();
        this.trajectory = new Line(new Point(x, y), new Point(x +  (int) dx, y +  (int) dy));
    }

    /**
     * This method changes the velocity of the ball according to its place in the frame
     * and checks that the ball will not go outside the frame.
     * if in the next step the ball will be outside the frame so it changes th velocity.
     *
     * @param x the the height of the frame
     * @param y the the width of the frame
     * @param w the the width of the frame
     * @param z the the height of the frame
     */
    public void check(int x, int y, int w, int z) {
        if ((this.center.getX() + this.radius + this.getVelocity().getX()) >= x) {
            if (this.velocity.getX() > 0) {
                setVelocity((-1) * this.velocity.getX(), this.velocity.getY());
            }
        }
        if ((this.center.getX() - this.radius + this.getVelocity().getX()) <= y) {
            if (this.velocity.getX() < 0) {
                setVelocity((-1) * this.velocity.getX(), this.velocity.getY());
            }
        }
        if (((this.center.getY() - this.radius + this.getVelocity().getY()) <= w)) {
            if (this.velocity.getY() < 0) {
                setVelocity(this.velocity.getX(), (-1) * this.velocity.getY());
            }
        }
        if ((this.center.getY() + this.radius + this.getVelocity().getY()) >= z) {
            if (this.velocity.getY() > 0) {
                setVelocity(this.velocity.getX(), (-1) * this.velocity.getY());
            }
        }
    }
    /**
     * Time passed.
     */
    public void timePassed() {
        moveOneStep();

    }
    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}