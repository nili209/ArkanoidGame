package component;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private Color color = null;
    private Image image = null;

    /**
     * Instantiates a new Background.
     *
     * @param i the
     */
    public Background(Image i) {
        this.image = i;
    }

    /**
     * Instantiates a new Background.
     *
     * @param c the c
     */
    public Background(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color == null) {
            d.drawImage(0, 0, this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle(20, 20, Green3.WIDTH_OF_SCREEN, GameLevel.HEIGHT_OF_SCREEN);
        }

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
