package component;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double framesPerNumber;
    private double appearance;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop = false;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.framesPerNumber = 1000 * numOfSeconds / countFrom;
        this.appearance = this.framesPerNumber;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.orange);
        d.drawText(GameLevel.WIDTH_OF_SCREEN / 2, GameLevel.HEIGHT_OF_SCREEN / 2, Integer.toString(this.countFrom), 30);
        this.framesPerNumber = this.framesPerNumber - (double) (1000 / 60);
        if ((this.framesPerNumber <= 0) && (this.countFrom != 0)) {
            this.countFrom = this.countFrom - 1;
            this.framesPerNumber = this.appearance;
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
