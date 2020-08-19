package component;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.Line;
import graphics.Point;
import graphics.Rectangle;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The constant WIDTH.
     */
    public static final double WIDTH = 150;
    /**
     * The constant PADDLE_X_START.
     */
    public static final double PADDLE_X_START = (GameLevel.WIDTH_OF_SCREEN / 2) - (WIDTH / 2);
    /**
     * The constant PADDLE_STEP.
     */
    public static final double PADDLE_STEP = 7;
    /**
     * The constant NUMBER_OF_PARTS.
     */
    public static final double NUMBER_OF_PARTS = 5;
    /**
     * The constant WIDTH.
     */

    public static final int SAFE_DISTANCE = 12;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    //private double x;
    private double speed;


    /**
     * Instantiates a new Paddle.
     *
     * @param sensor the sensor
     * @param p      the p
     * @param wide   the wide
     * @param height the height
     */
    public Paddle(KeyboardSensor sensor, Point p, int wide, int height) {
        this.paddle = new Rectangle(p, wide, height);
        this.keyboard = sensor;
        this.speed = 7;
    }

    /**
     * Set speed.
     *
     * @param s the s
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() > 20) {
           double x = this.paddle.getUpperLeft().getX();
            x -= this.speed;
           double y = this.paddle.getUpperLeft().getY();
            paddle = new Rectangle(new Point(x, y), this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() < GameLevel.WIDTH_OF_SCREEN - GameLevel.WIDTH_OF_FRAME
                - this.paddle.getWidth()) {
            double x = this.paddle.getUpperLeft().getX();
            x += this.speed;
            double y = this.paddle.getUpperLeft().getY();
            paddle = new Rectangle(new Point(x, y), this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    // Sprite
    /**
     * Time passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }

    }
    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    // Collidable
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the hitting ball
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity velocity = currentVelocity;
        if (this.paddle.getDown().checkPointOnLine(collisionPoint)) {
            velocity.setDy((-1) * velocity.getY());
        }
        if (this.paddle.getLeft().checkPointOnLine(collisionPoint)
                || this.paddle.getRight().checkPointOnLine(collisionPoint)) {
            velocity.setDx((-1) * velocity.getX());
        }
        if (this.paddle.getUp().checkPointOnLine(collisionPoint)) {
            double xPoint = collisionPoint.getX();
            double paddleStartX = this.paddle.getUp().start().getX();
            double partSize = this.paddle.getWidth() / NUMBER_OF_PARTS;
            //Velocity v = new Velocity(currentVelocity.getX(),currentVelocity.getY());
            Line tempTrajectory = tempTrajectory(collisionPoint, currentVelocity);
            int collisionPart = 6;
            for (int i = 0; i < NUMBER_OF_PARTS; i++) {
                if (xPoint >= paddleStartX + (i * partSize) && xPoint <= paddleStartX + ((i + 1) * partSize)) {
                    collisionPart = i;
                   // System.out.println(i);
                }
            }
            switch (collisionPart) {
                case 0:
                    velocity = Velocity.fromAngleAndSpeed(300, tempTrajectory.length());
                    break;
                case 1:
                    velocity = Velocity.fromAngleAndSpeed(330, tempTrajectory.length());
                    break;
                case 2:
                    velocity.setDy((-1) * currentVelocity.getY());
                    break;
                case 3:
                    velocity = Velocity.fromAngleAndSpeed(30, tempTrajectory.length());
                    break;
                case 4:
                    velocity = Velocity.fromAngleAndSpeed(60, tempTrajectory.length());
                    break;
                default:
                    //velocity = velocity.fromAngleAndSpeed(30, tempTrajectory.length());
                    break;
            }
        }
        return velocity;
    }

    /**
     * Temp trajectory line.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the line
     */
    public Line tempTrajectory(Point collisionPoint, Velocity currentVelocity) {
        double x1 = collisionPoint.getX();
        double y = collisionPoint.getY();
        double dx = currentVelocity.getX();
        double dy = currentVelocity.getY();
        Line tempTrajectory = new Line(new Point(x1, y), new Point(x1 + dx, y + dy));
        return tempTrajectory;
    }
    /**
     * Add this paddle to the game.
     *
     * @param g  the current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}