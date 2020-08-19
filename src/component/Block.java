package component;
import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle block;
    private int numberOfHits;
    private int originalnumberOfHits;
    private Color color;
    private Color colorFrame;
    private Image image = null;
    private Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
    private List<Integer> kListColor = new ArrayList<Integer>();
    private List<Integer> kListImage = new ArrayList<Integer>();

    /**
     * Instantiates a new Block.
     *
     * @param b the b
     */
    public Block(Rectangle b) {
        this.block = b;
        initializeHits();
        this.hitListeners = new ArrayList<HitListener>();
        this.color = Color.lightGray;
        this.colorFrame = null;
    }

    /**
     * Initialize hits.
     */
    public void initializeHits() {
        if (this.block.getLeft().end().getY() == 150) {
            this.numberOfHits = 2;
        } else {
            this.numberOfHits = 1;
        }
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the hitting ball
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        setNumberOfHits();
        Velocity velocity = currentVelocity;
        if ((block.getDown().checkPointOnLine(collisionPoint)) || (block.getUp()).checkPointOnLine(collisionPoint)) {
            velocity.setDy((-1) * velocity.getY());
        }
        if ((block.getLeft().checkPointOnLine(collisionPoint)) || (block.getRight()).checkPointOnLine(collisionPoint)) {
            velocity.setDx((-1) * velocity.getX());
        }
        this.notifyHit(hitter);
        return velocity;
    }

    /**
     * Gets number of hits.
     *
     * @return the number of hits
     */
    public int getNumberOfHits() {
        return numberOfHits;
    }

    /**
     * Sets number of hits.
     */
    public void setNumberOfHits() {
        this.numberOfHits -= 1;
    }

    /**
     * Sets hit numbers.
     *
     * @param n the number of hits
     */
    public void setHitNumbers(int n) {
        this.numberOfHits = n;
    }

    /**
     * Sets originalnumber of hits.
     *
     * @param n the number of hits
     */
    public void setOriginalnumberOfHits(int n) {
        this.originalnumberOfHits = n;
    }

    /**
     * Gets originalnumber of hits.
     *
     * @return the originalnumber of hits
     */
    public int getOriginalnumberOfHits() {
        return this.originalnumberOfHits;
    }


    /**
     * New hits.
     */
    public void newHits() {

    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        for (int i : this.kListColor) {
            if (i == this.numberOfHits) {
                this.setColor(this.colorMap.get(i));
            }
        }
        for (int i : this.kListImage) {
            if (i == this.numberOfHits) {
                this.setImages(this.imageMap.get(i));
            }
        }

        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width1 = (int) this.block.getWidth();
        int height1 = (int) this.block.getHeight();
        if (this.image != null) {
            surface.drawImage(x, y, this.image);
        } else {
            surface.setColor(this.color);
            surface.fillRectangle(x, y, width1, height1);
        }
        if (this.colorFrame == null) {
            surface.setColor(this.color);
            surface.drawRectangle(x, y, width1, height1);
        } else {
            surface.setColor(this.colorFrame);
            surface.drawRectangle(x, y, width1, height1);
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify hit.
     *
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Sets color map.
     *
     * @param c the color map
     */
    public void setColorMap(Map<Integer, Color> c) {
        this.colorMap = c;
    }

    /**
     * Sets image map.
     *
     * @param i the image map
     */
    public void setImageMap(Map<Integer, Image> i) {
        this.imageMap = i;
    }

    /**
     * Sets list color.
     *
     * @param klistcolor the k list color
     */
    public void setkListColor(List<Integer> klistcolor) {
        this.kListColor = klistcolor;
    }

    /**
     * Sets list image.
     *
     * @param klistimage the k list image
     */
    public void setkListImage(List<Integer> klistimage) {
        this.kListImage = klistimage;
    }

    /**
     * Sets frame.
     *
     * @param strokeColor the stroke color
     */
    public void setFrame(Color strokeColor) {
        this.colorFrame = strokeColor;
    }

    /**
     * Sets images.
     *
     * @param image1 the image 1
     */
    public void setImages(Image image1) {
        this.image = image1;
    }
}
