import biuoop.GUI;
import biuoop.KeyboardSensor;
import component.LevelInformation;
import component.LevelSpecificationReader;
import component.AnimationRunner;
import component.HighScoresTable;
import component.Animation;
import component.GameFlow;
import component.KeyPressStoppableAnimation;
import component.Menu;
import component.HighScoresAnimation;
import component.MenuAnimation;
import component.Task;



import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static component.GameLevel.HEIGHT_OF_SCREEN;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    private List<LevelInformation> levelInformations = new ArrayList<LevelInformation>();
    private List<String> symbol = new ArrayList<String>();
    private List<String> name = new ArrayList<String>();
    private List<String> path = new ArrayList<String>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, HEIGHT_OF_SCREEN);
        Ass7Game game = new Ass7Game();
        if (args.length == 0) {
            game.readLevelSet("level_sets.txt");
        } else {
            String newString = Arrays.toString(args);
            String fileName = newString.substring(1, newString.length() - 1);
            game.readLevels(fileName);
        }
        game.makeMenu(gui, args);
    }

    /**
     * Read level set.
     *
     * @param s the s
     */
    public void readLevelSet(String s) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            Reader reader = new InputStreamReader(is);
            LineNumberReader lineNumberReader = null;
            try {
                lineNumberReader = new LineNumberReader(reader);
                String line;
                while ((line = lineNumberReader.readLine()) != null) {
                    if (lineNumberReader.getLineNumber() % 2 == 1) {
                        String[] strings = line.split(":");
                        this.symbol.add(strings[0]);
                        this.name.add(strings[1]);
                    } else if (!line.equals("")) {
                        this.path.add(line);
                    }
                }
            } catch (IOException e) {

            }

        } catch (Exception e) {

        }
    }


    /**
     * Read levels list.
     *
     * @param s the s
     * @return the list
     */
    public List<LevelInformation> readLevels(String s) {
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            Reader reader = new InputStreamReader(is);
            return levelSpecificationReader.fromReader(reader);
        } catch (Exception e) {

        }
        return new ArrayList<LevelInformation>();
    }

    /**
     * Make menu.
     *
     * @param gui  the gui
     * @param args the args
     */
    public void makeMenu(GUI gui, String[] args) {
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        HighScoresTable highScoresTable = new HighScoresTable(3);
        Animation highScoreAnimation = new KeyPressStoppableAnimation(keyboardSensor,
                keyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable));
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        try {
            highScoresTable.load(new File("highscores"));
            //highScoresTable.clear();
        } catch (Exception e) {

        }
        Menu<Task<Void>> menu = new MenuAnimation<>(keyboardSensor, animationRunner);
        menu.addSelection("h", "High Score", new Ass7Game.ShowHiScoresTask(animationRunner, highScoreAnimation));
        Menu<Task<Void>> submenu = new MenuAnimation<>(keyboardSensor, animationRunner);
        for (int i = 0; i < this.name.size(); i++) {
            List<LevelInformation> levels = readLevels(this.path.get(i));
            submenu.addSelection(this.symbol.get(i),
                    "(" + this.symbol.get(i) + ")" + this.name.get(i),
                    new Ass7Game.StartGame(gameFlow, levels));

        }
        menu.addSubMenu("s", "Start Game", submenu);
        //menu.addSelection("s", "Start Game", new Ass7Game.StartGame(gameFlow));
        menu.addSelection("q", "Quit Game", new Ass7Game.Quit());
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
            menu = new MenuAnimation<>(keyboardSensor, animationRunner);
            //setLevels(args, gui);
            menu.addSelection("h", "High Score", new Ass7Game.ShowHiScoresTask(animationRunner, highScoreAnimation));
            submenu = new MenuAnimation<>(keyboardSensor, animationRunner);
            for (int i = 0; i < this.name.size(); i++) {
                List<LevelInformation> levels = readLevels(this.path.get(i));
                submenu.addSelection(this.symbol.get(i),
                        "(" + this.symbol.get(i) + ")" + this.name.get(i),
                        new Ass7Game.StartGame(gameFlow, levels));

            }
            menu.addSubMenu("s", "Start Game", submenu);
            //menu.addSelection("s", "Start Game", new Ass7Game.StartGame(gameFlow));
            menu.addSelection("q", "Quit Game", new Ass7Game.Quit());
        }
    }

    /**
     * The type Show hi scores task.
     */
    public class ShowHiScoresTask implements Task<Void> {
        private AnimationRunner runner;
        private Animation highScoresAnimation;

        /**
         * Instantiates a new Show hi scores task.
         *
         * @param runner              the runner
         * @param highScoresAnimation the high scores animation
         */
        public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
            this.runner = runner;
            this.highScoresAnimation = highScoresAnimation;
        }

        /**
         * run the game.
         * @return null
         */
        public Void run() {
            this.runner.run(this.highScoresAnimation);
            return null;
        }
    }

    /**
     * The type Start game.
     */
    public class StartGame implements Task<Void> {
        private List<LevelInformation> levels = new ArrayList<LevelInformation>();
        private GameFlow gameFlow;

        /**
         * Instantiates a new Start game.
         *
         * @param gameFlow the game flow
         * @param levels   the levels
         */
        public StartGame(GameFlow gameFlow, List<LevelInformation> levels) {
            this.gameFlow = gameFlow;
            this.levels = levels;
        }
        @Override
        public Void run() {
            gameFlow.runLevels(this.levels);
            return null;
        }
    }

    /**
     * The type Quit.
     */
    public class Quit implements Task<Void> {
        @Override
        public Void run() {
            System.exit(0);
            return null;
        }
    }

//    private void setLevels(String[] args, GUI gui) {
//        //AnimationRunner ar = new AnimationRunner(gui);
//        List<LevelInformation> levels = new ArrayList<LevelInformation>();
//        boolean flag = false;
//        if (args.length == 0) {
//            this.levelInformations.add(new DirectHit());
//            this.levelInformations.add(new WideEasy());
//            this.levelInformations.add(new Green3());
//            this.levelInformations.add(new FinalFour());
//        }
//        for (int i = 0; i < args.length; i++) {
//            if (args[i].equals("1")) {
//                this.levelInformations.add(new DirectHit());
//                flag = true;
//            }
//            if (args[i].equals("2")) {
//                this.levelInformations.add(new WideEasy());
//                flag = true;
//            }
//            if (args[i].equals("3")) {
//                this.levelInformations.add(new Green3());
//                flag = true;
//            }
//            if (args[i].equals("4")) {
//                this.levelInformations.add(new FinalFour());
//                flag = true;
//            }
//        }
//        if (!flag) {
//            this.levelInformations.add(new DirectHit());
//            this.levelInformations.add(new WideEasy());
//            this.levelInformations.add(new Green3());
//            this.levelInformations.add(new FinalFour());
//        }
//        //GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);
//        //gameFlow.runLevels(levels);
//    }

}
