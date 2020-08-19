package component;

/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getNumberOfHits() + " points was hit.");
    }
}