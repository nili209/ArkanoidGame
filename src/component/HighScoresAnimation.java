package component;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Color color1 = new Color(0xABB8B3);
        d.setColor(color1);
        d.fillRectangle(0, 0, 800, 600);
        Color color = new Color(0xD4867A);
        d.setColor(color);
        d.drawText(100, 70, "High Scores:", 45);
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
        int x = 100;
        int y = 125;
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.drawText(x, y, this.scores.getHighScores().get(i).getName()
                    + "'s score is  " + this.scores.getHighScores().get(i).getScore(), 25);
            y += 30;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
