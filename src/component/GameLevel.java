package component;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.Point;
import graphics.Rectangle;
import java.awt.Color;


/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    //private GUI gui;
    private biuoop.Sleeper sleeper;
    private Paddle paddle;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private HitListener blockRemover;
    private HitListener ballRemover;
    private HitListener scoreTrackingListener;
    private Counter scoreCounter;
    private Sprite indicator;
    private Counter livesCounter;
    private Sprite livesIndicator;
    private Sprite levelIndicator;
    private Block deathRegion;
    private LevelInformation level;
    private biuoop.KeyboardSensor keyboard;
    /**
     * The constant NUMBER_OF_LIVES.
     */
    public static final int NUMBER_OF_LIVES = 4;
    /**
     * The constant NUMBER_OF_BLOCKS.
     */
    public static final int NUMBER_OF_BLOCKS = 57;
    /**
     * The constant HEIGHT_OF_SCREEN.
     */
    public static final int HEIGHT_OF_SCREEN = 600;
    /**
     * The constant WIDTH_OF_SCREEN.
     */
    public static final int WIDTH_OF_SCREEN = 800;
    /**
     * The constant WIDTH_OF_FRAME.
     */
    public static final int WIDTH_OF_FRAME = 20;
    /**
     * The constant X_START_BLOCK.
     */
    public static final double X_START_BLOCK = WIDTH_OF_SCREEN - WIDTH_OF_FRAME;
    /**
     * The constant Y_START_BLOCK.
     */
    public static final double Y_START_BLOCK = 300;
    /**
     * The constant WIDTH_START_BLOCK.
     */
    public static final double WIDTH_START_BLOCK = 60;
    /**
     * The constant HEIGHT_START_BLOCK.
     */
    public static final double HEIGHT_START_BLOCK = 30;


    /**
     * The constant HEIGHT_OF_SCREEN.
     *
     * @param levels          the levels
     * @param sensor          the sensor
     * @param animationRunner the animation runner
     * @param scoreCounter    the score counter
     * @param livesCounter    the lives counter
     */
    public GameLevel(LevelInformation levels, KeyboardSensor sensor, AnimationRunner animationRunner,
                     Counter scoreCounter, Counter livesCounter) {
        this.sprites = new SpriteCollection();
        this.level = levels;
        this.sprites.addSprite(level.getBackground());
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(level.numberOfBlocksToRemove());
        this.ballsCounter = new Counter(level.numberOfBalls());
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreCounter = scoreCounter;
        this.scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        this.indicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(this.indicator);
        this.livesCounter = livesCounter;
        this.livesIndicator = new LivesIndicator(this.livesCounter);
        this.levelIndicator = new NameIndicator(this.level.levelName());
        this.sprites.addSprite(this.levelIndicator);
        this.sprites.addSprite(this.livesIndicator);
        this.runner = animationRunner;
        this.keyboard = sensor;

    }

    /**
     * Gets block remover.
     *
     * @return the block remover
     */
    public HitListener getBlockRemover() {
        return blockRemover;
    }

    /**
     * Gets score tracking listener.
     *
     * @return the score tracking listener
     */
    public HitListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    /**
     * Gets blocks counter.
     *
     * @return the blocks counter
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * Gets lives counter.
     *
     * @return the lives counter
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }

    /**
     * Gets balls counter.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {

        this.sleeper = new biuoop.Sleeper();
        createFrame();
        createPaddle();
        createBall();
        createBlocks();


    }
// Run the game -- start the animation loop.
    //playOneTurn
    @Override
    public void doOneFrame(DrawSurface d) {
//        updatePaddle();
//        createBall();
//        //this.runner.run(new CountdownAnimation(2,3,this.sprites));
//        //this.runner.run(new CountdownAnimation(.));
//        int framesPerSecond = 60;
//        int millisecondsPerFrame = 1000 / framesPerSecond;
//        while ((this.blocksCounter.getValue() > 0) && this.ballsCounter.getValue() > 0) {
//            long startTime = System.currentTimeMillis(); // timing
//
//            //d = gui.getDrawSurface();
//            this.sprites.drawAllOn(d);
//            //gui.show(d);
//            this.sprites.notifyAllTimePassed();
//            // timing
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//            if (this.keyboard.isPressed("p")) {
//                this.runner.run(new PauseScreen(this.keyboard));
//            }
//        }
//        if (this.blocksCounter.getValue() == 0) {
//            this.running= false;
//            this.scoreCounter.increase(100);
//        } else {
//            this.livesCounter.decrease(1);
//        }
//        if (this.livesCounter.getValue() == 0) {
//            this.running = false;
//        }
//        resrtBallCounter();
        if ((this.blocksCounter.getValue() <= 0) || (this.ballsCounter.getValue() <= 0)) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation stoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
                    this.keyboard.SPACE_KEY, new PauseScreen(this.keyboard));
            this.runner.run(stoppableAnimation);
        }
    }

    /**
     * Play one turn.
     */
    public void playOneTurn() {
        updatePaddle();
        if (this.getBallsCounter().getValue() == 0) {
            resrtBallCounter();
            createBall();
        }
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }
    /**
     * Run.
     */
//    public void run() {
//        while (this.livesCounter.getValue() > 0) {
//            playOneTurn();
//            if (this.blocksCounter.getValue() == 0) {
//                this.running = false;
//                break;
//            }
//        }
//        this.running = false;
//        gui.close();
//
//
//    }

    /**
     * Create frame.
     */
    public void createFrame() {
        Block[] frame = new Block[3];
        Rectangle rect0 = new Rectangle(new Point(0, WIDTH_OF_FRAME), WIDTH_OF_FRAME,
                HEIGHT_OF_SCREEN - WIDTH_OF_FRAME); // left line
        frame[0] = new Block(rect0);
        Rectangle rect1 = new Rectangle(new Point(0, 20), WIDTH_OF_SCREEN, WIDTH_OF_FRAME); // top line
        frame[2] = new Block(rect1);
//        Rectangle rect2 = new Rectangle(new Point(WIDTH_OF_FRAME, HEIGHT_OF_SCREEN - WIDTH_OF_FRAME),
//                WIDTH_OF_SCREEN - WIDTH_OF_FRAME, WIDTH_OF_FRAME); // bottom line
 //       frame[2] = new Block(rect2);
        Rectangle rect3 = new Rectangle(new Point(WIDTH_OF_SCREEN - WIDTH_OF_FRAME, WIDTH_OF_FRAME), WIDTH_OF_FRAME,
                HEIGHT_OF_SCREEN - WIDTH_OF_FRAME); // right line
        frame[1] = new Block(rect3);
        for (int i = 0; i < frame.length; i++) {
            frame[i].addToGame(this);
        }

    }

    /**
     * Create ball.
     */
    public void createBall() {
        Ball[] ballsArray = new Ball[level.numberOfBalls()];
        int wide = level.paddleWidth() / 2;
        for (int i = 0; i < ballsArray.length; i++) {
            int x = (int) this.paddle.getCollisionRectangle().getUpperLeft().getX() + wide;
            int y = (int) this.paddle.getCollisionRectangle().getUpperLeft().getY() - 30;
            ballsArray[i] = new Ball(new Point(x, y), 6, Color.WHITE);
            ballsArray[i].setVelocity(level.initialBallVelocities().get(i));
            ballsArray[i].setTrajectory();
            ballsArray[i].setGame(this.environment);
            ballsArray[i].addToGame(this);
        }
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
//        Block[][] blocks = new Block[6][12];
//        for (int i = 0; i < blocks.length; i++) {
//            double yPoint = Y_START_BLOCK - (i + 1) * HEIGHT_START_BLOCK;
//            for (int j = 0; j < i + 7; j++) {
//                Point point = new Point(X_START_BLOCK - (j + 1) * WIDTH_START_BLOCK, yPoint);
//                Rectangle rect = new Rectangle(point, WIDTH_START_BLOCK, HEIGHT_START_BLOCK);
//                blocks[i][j] = new Block(rect);
//                blocks[i][j].addHitListener(this.blockRemover);
//                blocks[i][j].addHitListener(this.scoreTrackingListener);
//                blocks[i][j].addToGame(this);
//
//            }
//        }
        for (Block b: level.blocks()) {
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreTrackingListener);
            b.addToGame(this);
        }

        //deathRegion
        Point point = new Point(0, HEIGHT_OF_SCREEN);
        Rectangle rect = new Rectangle(point, WIDTH_OF_SCREEN - WIDTH_OF_FRAME, WIDTH_OF_FRAME);
        this.deathRegion = new Block(rect);
        deathRegion.addHitListener(this.ballRemover);
        deathRegion.addToGame(this);
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        double x = Paddle.PADDLE_X_START;
        Point p = new Point((WIDTH_OF_SCREEN / 2) - (level.paddleWidth() / 2), HEIGHT_OF_SCREEN - WIDTH_OF_FRAME - 20);
        this.paddle = new Paddle(this.keyboard, p, level.paddleWidth(), 20);
        //this.paddle = new Paddle(this.gui);
        this.paddle.setSpeed(level.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * Update paddle.
     */
    public void updatePaddle() {
        this.removeSprite(this.paddle);
        this.removeCollidable(this.paddle);
        createPaddle();
    }

    /**
     * Resrt ball counter.
     */
    public void resrtBallCounter() {
        this.ballsCounter = new Counter(level.numberOfBalls());
        this.deathRegion.removeHitListener(this.ballRemover);
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        this.deathRegion.addHitListener(this.ballRemover);

    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets score counter.
     *
     * @return the score counter
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }
}