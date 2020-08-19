package component;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private int isWinner;
    private Counter score;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param k        the k
     * @param isWinner the is winner
     * @param score    the score
     */
    public EndScreen(KeyboardSensor k, int isWinner, Counter score) {
        this.keyboard = k;
        this.isWinner = isWinner;
        this.score = score;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Color color1 = new Color(0xABB8B3);
        Color color = new Color(0xA79A28);
        d.setColor(color1);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(color);
        d.fillCircle(790, 590, 200);
        d.setColor(color1);
        d.fillCircle(790, 590, 180);
        d.setColor(color);
        d.fillCircle(790, 590, 160);
        d.setColor(color1);
        d.fillCircle(790, 590, 140);
        d.setColor(color);
        d.fillCircle(790, 590, 120);
        d.setColor(color1);
        d.fillCircle(790, 590, 100);
        d.setColor(color);
        d.fillCircle(790, 590, 80);
        d.setColor(color1);
        d.fillCircle(790, 590, 60);
        d.setColor(color);
        d.fillCircle(790, 590, 40);
        d.setColor(color1);
        d.fillCircle(790, 590, 20);
        d.setColor(color);
        if (isWinner == 0) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
