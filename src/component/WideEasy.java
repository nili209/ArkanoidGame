package component;

import graphics.Point;
import graphics.Rectangle;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<Velocity>();
        double speed = -8;
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(130 + (i * 10), speed));
            velocityList.add(Velocity.fromAngleAndSpeed(-(130 + (i * 10)), speed));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<Block>();
        int wide = (GameLevel.WIDTH_OF_SCREEN - (2 * GameLevel.WIDTH_OF_FRAME)) / numberOfBlocksToRemove();
        int x = GameLevel.WIDTH_OF_FRAME;
        int y = 260;
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            int x1 = x + (i * wide);
            Rectangle r = new Rectangle(new Point(x1, y), wide, 30);
            Block b = new Block(r);
            setColor(b, i);
            blockList.add(b);
        }

        return blockList;
    }

    /**
     * Sets color.
     *
     * @param b the b
     * @param i the
     */
    public void setColor(Block b, int i) {
        if (i == 0 || i == 1) {
            b.setColor(Color.RED);
        }
        if (i == 2 || i == 3) {
            b.setColor(Color.ORANGE);
        }
        if (i == 4 || i == 5) {
            b.setColor(Color.YELLOW);
        }
        if (i == 6 || i == 7 || i == 8) {
            b.setColor(Color.GREEN);
        }
        if (i == 9 || i == 10) {
            b.setColor(Color.BLUE);
        }
        if (i == 11 || i == 12) {
            b.setColor(Color.PINK);
        }
        if (i == 13 || i == 14) {
            b.setColor(Color.CYAN);
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
