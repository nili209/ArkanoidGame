package component;
import graphics.Point;
import graphics.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle indicator;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
        this.indicator = new Rectangle(new Point(0, 0), GameLevel.WIDTH_OF_SCREEN, GameLevel.WIDTH_OF_FRAME);
    }

    @Override
    public void drawOn(DrawSurface d) {
        //this.indicator.drawOn(d);
        d.setColor(Color.BLACK);
        int i = ((int) this.indicator.getLeft().middle().getX() + (int) this.indicator.getRight().middle().getX()) / 2;
        int i1 = ((int) this.indicator.getUp().middle().getY() + (int) this.indicator.getDown().middle().getY() - 4);
        d.drawText(i, i1, "Score: " + this.scoreCounter.getValue(), 18);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
