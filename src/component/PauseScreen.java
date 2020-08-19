package component;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        Color color1 = new Color(0xABB8B3);
        Color color = new Color(0x54A1A7);
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
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}