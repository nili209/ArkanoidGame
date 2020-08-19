package component;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * The constant FRAME_PER_SECOND.
     */
    public static final int FRAME_PER_SECOND = 60;

    /**
     * Instantiates a new Animation runner.
     *
     * @param g the g
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = FRAME_PER_SECOND;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}