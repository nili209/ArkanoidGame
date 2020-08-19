package component;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor sensor;
    private GUI gui;
    private Counter livesCounter;
    private Counter scoreCounter;
    private HighScoresTable highScoresTable;
    private File file;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.sensor = ks;
        this.gui = gui;
        this.scoreCounter = new Counter(0);
        this.livesCounter = new Counter(2);
        this.highScoresTable = new HighScoresTable(3);
        this.file = new File("highscores");
        try {
            highScoresTable.load(this.file);
        } catch (Exception e) {
            System.out.println("cant read from file");
        }
    }


    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {

        int i = 0;
        for (LevelInformation levelInfo : levels) {
            i++;
            GameLevel level = new GameLevel(levelInfo, this.sensor, this.animationRunner, this.scoreCounter,
                    this.livesCounter);

            level.initialize();

            while ((level.getBlocksCounter().getValue() > 0) && (level.getLivesCounter().getValue() > 0)) {
                level.playOneTurn();
                if (level.getBlocksCounter().getValue() > 0) {
                    level.getLivesCounter().decrease(1);
                }
            }
            resetLevel(levelInfo);
            if (level.getBlocksCounter().getValue() == 0) {
                level.getScoreCounter().increase(100);
            }

            if (level.getLivesCounter().getValue() <= 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY,
                        new EndScreen(this.sensor, 1, this.scoreCounter)));
                checkScore();
                break;
            }

        }
        if (this.livesCounter.getValue() > 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY,
                    new EndScreen(this.sensor, 0, this.scoreCounter)));
            checkScore();
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.sensor, this.sensor.SPACE_KEY,
                new HighScoresAnimation(this.highScoresTable)));
        this.scoreCounter.decrease(this.scoreCounter.getValue());
        //gui.close();
    }

    /**
     * Reset level.
     *
     * @param levelInfo the level info
     */
    public void resetLevel(LevelInformation levelInfo) {
        for (Block b : levelInfo.blocks()) {
            b.setHitNumbers(b.getOriginalnumberOfHits());
            //b.removeHitListener();
        }
    }

    /**
     * Check score.
     */
    public void checkScore() {
        if (this.highScoresTable.getRank(this.scoreCounter.getValue()) <= this.highScoresTable.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            //System.out.println(name);
            this.highScoresTable.add(new ScoreInfo(name, this.scoreCounter.getValue()));
            System.out.println(this.highScoresTable.getHighScores().size());
            if(this.highScoresTable.getHighScores().size() > 3) {
                this.highScoresTable.getHighScores().remove(this.highScoresTable.size());
            }

            //this.highScoresTable.getHighScores().remove(0);
            try {
                this.highScoresTable.save(this.file);
            } catch (Exception e) {
                System.out.println("cant write to file");
            }
        }
    }

}
