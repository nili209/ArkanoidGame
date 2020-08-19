package component;

import graphics.Point;
import graphics.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
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
     * The constant WIDTH_OF_SCREEN.
     */
    public static final int WIDTH_OF_SCREEN = 1000;
    /**
     * The constant WIDTH_OF_FRAME.
     */
    public static final int WIDTH_OF_FRAME = 20;
    /**
     * The constant X_START_BLOCK.
     */
    public static final double X_START_BLOCK = WIDTH_OF_SCREEN - WIDTH_OF_FRAME;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(new Velocity(1, -4));
        velocityList.add(new Velocity(-1, -4));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background3();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 4; i++) {
            double yPoint = Y_START_BLOCK - (i + 1) * HEIGHT_START_BLOCK;
            for (int j = 0; j < i + 6; j++) {
                Point point = new Point((double) (X_START_BLOCK - (j + 1) * WIDTH_START_BLOCK), yPoint);
                Rectangle r = new Rectangle(point, WIDTH_START_BLOCK, HEIGHT_START_BLOCK);
                Block b = new Block(r);
                setColor(b, j);
                blockList.add(b);
            }
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
        switch ((int) b.getBlock().getLeft().end().getY()) {
            case 300:
                b.setColor(Color.WHITE);
                break;
            case 270:
                b.setColor(Color.BLUE);
                break;
            case 240:
                b.setColor(Color.YELLOW);
                break;
            case 210:
                b.setColor(Color.RED);
                break;
            case 180:
                b.setColor(Color.GRAY);
                break;
            case 150:
                b.setColor(Color.GREEN.darker().darker().brighter());
                break;
            default:
                b.setColor(Color.lightGray);
        }
    }


    @Override
    public int numberOfBlocksToRemove() {
        return 34;
    }
}
