package component;

import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;

import java.awt.Color;

/**
 * The type Name indicator.
 */
public class NameIndicator implements Sprite {
    private String levelName;
    private Rectangle indicator;

    /**
     * Instantiates a new Name indicator.
     *
     * @param levelName the level name
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
        this.indicator = new Rectangle(new Point(0, 0), GameLevel.WIDTH_OF_SCREEN + 350, GameLevel.WIDTH_OF_FRAME);

    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int i = ((int) this.indicator.getLeft().middle().getX() + (int) this.indicator.getRight().middle().getX()) / 2;
        int i1 = ((int) this.indicator.getUp().middle().getY() + (int) this.indicator.getDown().middle().getY() - 4);
        d.drawText(i, i1, "Name Level: " + this.levelName, 18);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
