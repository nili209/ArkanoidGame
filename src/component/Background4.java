package component;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Background 4.
 */
public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(62, 155, 179));
        d.fillRectangle(20, 40, GameLevel.WIDTH_OF_SCREEN - 40, GameLevel.HEIGHT_OF_SCREEN - 40);
        //rain
        d.setColor(Color.WHITE);
        d.drawLine(140, 380, 100, 600);
        d.drawLine(145, 380, 105, 600);
        d.drawLine(150, 380, 110, 600);
        d.drawLine(155, 380, 115, 600);
        d.drawLine(160, 380, 120, 600);
        d.drawLine(165, 380, 125, 600);
        d.drawLine(170, 380, 130, 600);
        d.drawLine(175, 380, 135, 600);
        d.drawLine(180, 380, 140, 600);
        d.drawLine(185, 380, 145, 600);
        d.drawLine(190, 380, 150, 600);
        d.drawLine(195, 380, 155, 600);
        d.drawLine(200, 380, 160, 600);
        //1
        d.setColor(new Color(191, 205, 199));
        d.fillCircle(150, 400, 25);
        //2
        d.fillCircle(130, 380, 23);
        //3
        d.setColor(new Color(171, 184, 179));
        d.fillCircle(160, 370, 28);
        //4
        d.setColor(new Color(149, 160, 156));
        d.fillCircle(195, 370, 30);
        //5
        d.fillCircle(180, 400, 25);
        //rain
        d.setColor(Color.WHITE);
        d.drawLine(740, 500, 700, 600);
        d.drawLine(745, 500, 705, 600);
        d.drawLine(750, 500, 710, 600);
        d.drawLine(755, 500, 715, 600);
        d.drawLine(760, 500, 720, 600);
        d.drawLine(765, 500, 725, 600);
        d.drawLine(770, 500, 730, 600);
        d.drawLine(775, 500, 735, 600);
        d.drawLine(780, 500, 740, 600);
        d.drawLine(785, 500, 745, 600);
        d.drawLine(790, 500, 750, 600);
        d.drawLine(795, 500, 755, 600);
        d.drawLine(800, 500, 760, 600);
        //1
        d.setColor(new Color(191, 205, 199));
        d.fillCircle(750, 500, 25);
        //2
        d.fillCircle(730, 480, 23);
        //3
        d.setColor(new Color(171, 184, 179));
        d.fillCircle(760, 470, 28);
        //4
        d.setColor(new Color(149, 160, 156));
        d.fillCircle(795, 470, 30);
        //5
        d.fillCircle(780, 500, 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
