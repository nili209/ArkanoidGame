package component;

import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;
import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    private Rectangle indicator;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param score the score
     */
    public LivesIndicator(Counter score) {
        this.livesCounter = score;
        this.indicator = new Rectangle(new Point(0, 0), GameLevel.WIDTH_OF_SCREEN / 3, GameLevel.WIDTH_OF_FRAME);
    }

    @Override
    public void drawOn(DrawSurface d) {
        //this.indicator.drawOn(d);
        d.setColor(Color.BLACK);
        int i = ((int) this.indicator.getLeft().middle().getX() + (int) this.indicator.getRight().middle().getX()) / 2;
        int i1 = ((int) this.indicator.getUp().middle().getY() + (int) this.indicator.getDown().middle().getY() - 4);
        d.drawText(i, i1, "Lives: " + this.livesCounter.getValue(), 18);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

