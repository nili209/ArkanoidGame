package component;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Background 2.
 */
public class Background2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(216, 225, 117));
        for (int i =  1; i < 520; i++) {
            d.drawLine(180, 150, 16 * (i / 10), 260);
        }
        //big
        d.setColor(new Color(216, 225, 117));
        d.fillCircle(180, 150, 80);
        //middle
        d.setColor(new Color(216, 225, 10));
        d.fillCircle(180, 150, 70);
        //small
        d.setColor(new Color(225, 198, 14));
        d.fillCircle(180, 150, 56);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
