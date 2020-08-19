package component;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Background 3.
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(20, 40, GameLevel.WIDTH_OF_SCREEN - 40, GameLevel.HEIGHT_OF_SCREEN - 40);
        //building
        d.setColor(Color.WHITE);
        d.fillRectangle(50, 400, 130, 200);
        //windows
        //wide
        d.setColor(Color.BLACK);
        d.fillRectangle(50, 400, 130, 10);
        d.fillRectangle(50, 450, 130, 10);
        d.fillRectangle(50, 500, 130, 10);
        d.fillRectangle(50, 550, 130, 10);
        //height
        d.fillRectangle(50, 400, 10, 200);
        d.fillRectangle(80, 400, 10, 200);
        d.fillRectangle(110, 400, 10, 200);
        d.fillRectangle(140, 400, 10, 200);
        d.fillRectangle(170, 400, 10, 200);
        //small grey
        d.setColor(new Color(83, 83, 84));
        d.fillRectangle(92, 330, 40, 70);
        //long grey
        d.setColor(new Color(119, 119, 120));
        d.fillRectangle(107, 180, 15, 150);
        //orange circle
        d.setColor(new Color(179, 144, 58));
        d.fillCircle(115, 168, 18);
        //red circle
        d.setColor(new Color(179, 80, 61));
        d.fillCircle(115, 168, 11);
        //white circle
        d.setColor(Color.WHITE);
        d.fillCircle(115, 168, 5);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
