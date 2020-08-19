package component;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;

    }
    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumberOfHits() == 0) {
            beingHit.removeFromGame(gameLevel);
            beingHit.removeHitListener(this);
            //remainingBlocks.decrease(1);
            //this.gameLevel.getBlocksCounter().decrease(1);
            this.remainingBlocks.decrease(1);
        }
    }
}