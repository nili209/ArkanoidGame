package component;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level.
 */
public class Level implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks = new ArrayList<Block>();
    private int numberOfBlocks;
    private Sprite background;
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Sets number of balls.
     *
     * @param n the number of balls
     */
    public void setNumberOfBalls(int n) {
        this.numberOfBalls = n;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * Sets ball velocities.
     *
     * @param b the ball velocities
     */
    public void setBallVelocities(List<Velocity> b) {
        this.ballVelocities = b;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Sets paddle speed.
     *
     * @param p the paddle speed
     */
    public void setPaddleSpeed(int p) {
        this.paddleSpeed = p;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Sets paddle width.
     *
     * @param p the paddle width
     */
    public void setPaddleWidth(int p) {
        this.paddleWidth = p;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Sets level name.
     *
     * @param l the level name
     */
    public void setLevelName(String l) {
        this.levelName = l;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Sets background.
     *
     * @param b the background
     */
    public void setBackground(Sprite b) {
        this.background = b;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Sets blocks.
     *
     * @param b the blocks
     */
    public void setBlocks(List<Block> b) {
        this.blocks = b;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocks;
    }

    /**
     * Sets number of blocks.
     *
     * @param n the number of blocks
     */
    public void setNumberOfBlocks(int n) {
        this.numberOfBlocks = n;
    }

}
