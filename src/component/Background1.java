package component;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type Background 1.
 */
public class Background1 implements Sprite {
    private ArrayList<Block> blockList = new ArrayList<Block>();
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(20, 40, GameLevel.WIDTH_OF_SCREEN - 40, GameLevel.HEIGHT_OF_SCREEN - 40);
        d.setColor(Color.BLUE);
        d.drawCircle(510, 225, 110);
        d.drawCircle(510, 225, 90);
        d.drawCircle(510, 225, 70);
        //left
        d.drawLine(370, 230, 470, 230);
        //right
        d.drawLine(550, 230, 650, 230);
        //up
        d.drawLine(510, 90, 510, 190);
        //down
        d.drawLine(510, 270, 510, 360);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
