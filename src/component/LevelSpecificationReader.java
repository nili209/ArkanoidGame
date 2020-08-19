package component;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private String levelName;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private String blockDefinitions;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int numBlocks;
    private List<Block> blockList = new ArrayList<>();
    private List<LevelInformation> levelInformation = new ArrayList<LevelInformation>();
    private int numBalls;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(Reader reader) {
        List<String> level = new ArrayList<String>();
        //int i = 0;
        BufferedReader bufferedReader = null;
        // read lines
        try {
            bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                if (line.equals("START_LEVEL")) {
                    //i++;
                    level = new ArrayList<String>();
                } else if (line.equals("END_LEVEL")) {
                    setLevels(level);
                    levelInformation.add(createLevel());
                } else {
                    level.add(line);
                }
            }
        } catch (IOException e) {

        }

        return levelInformation;
    }

    /**
     * Create level level.
     *
     * @return the level
     */
    public Level createLevel() {
        Level newLevel = new Level();
        newLevel.setNumberOfBalls(this.numBalls);
        newLevel.setBallVelocities(this.ballVelocities);
        newLevel.setPaddleSpeed(this.paddleSpeed);
        newLevel.setPaddleWidth(this.paddleWidth);
        newLevel.setLevelName(this.levelName);
        newLevel.setBackground(this.background);
        newLevel.setBlocks(this.blockList);
        newLevel.setNumberOfBlocks(this.numBlocks);
        this.levelName = null;
        this.ballVelocities = new ArrayList<>();
        background = null;
        this.paddleSpeed = -1;
        this.paddleWidth = -1;
        this.blockDefinitions = null;
        this.blockStartX = -1;
        this.blockStartY = -1;
        this.rowHeight = -1;
        this.numBlocks = -1;
        this.blockList = new ArrayList<>();
        this.numBalls = -1;
        return newLevel;
    }

    /**
     * Sets levels.
     *
     * @param level the level
     */
    public void setLevels(List<String> level) {
        String[] info;
        List<String> blocks = new ArrayList<String>();
        for (String s : level) {
            info = s.split(":");
            if (info[0].equals("#")) {
                continue;
            }
            switch (info[0]) {
                case "level_name":
                    levelName = info[1];
                    break;
                case "ball_velocities":
                    setBallVelocities(info[1]);
                    break;
                case "background":
                    setBackround(info[1]);
                    break;
                case "paddle_speed":
                    paddleSpeed = Integer.parseInt(info[1]);
                    break;
                case "paddle_width":
                    paddleWidth = Integer.parseInt(info[1]);
                    break;
                case "block_definitions":
                    blockDefinitions = info[1];
                    break;
                case "blocks_start_x":
                    blockStartX = Integer.parseInt(info[1]);
                    break;
                case "blocks_start_y":
                    blockStartY = Integer.parseInt(info[1]);
                    break;
                case "row_height":
                    rowHeight = Integer.parseInt(info[1]);
                    break;
                case "num_blocks":
                    numBlocks = Integer.parseInt(info[1]);
                    break;
                case "START_BLOCKS":
                    break;
                case "END_BLOCKS":
                    setBlocks(blocks);
                    blocks.removeAll(blockList);
                    break;
                default:
                    blocks.add(info[0]);
                    break;
            }

        }
    }

    /**
     * Sets backround.
     *
     * @param c the c
     */
    public void setBackround(String c) {
        String color;
        String image;
        if (c.startsWith("color")) {
            color = c.substring(6, c.length() - 1);
            if (c.startsWith("color")) {
                if (color.startsWith("RGB")) {
                    String s = color.substring(4, color.length() - 1);
                    Color fillColor = new ColorsParser().colorFromString(s);
                    this.background = new Background(fillColor);
                } else {
                    Color fillColor = new ColorsParser().colorFromString(color);
                    this.background = new Background(fillColor);
                }

            }
        } else {
            Image i = null;
            try {
                image = c.substring(6, c.length() - 1);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                i = ImageIO.read(is);
            } catch (IOException e) {

            }
            this.background = new Background(i);
        }
    }

    /**
     * Sets blocks.
     *
     * @param info the info
     */
    public void setBlocks(List<String> info) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blockDefinitions);
            Reader reader = new InputStreamReader(is);
            //InputStream is = new FileInputStream("resources/" + blockDefinitions);
            //Reader reader = new InputStreamReader(is);
            BlocksDefinitionReader bdr = new BlocksDefinitionReader();
            BlocksFromSymbolsFactory bdf = BlocksDefinitionReader.fromReader(reader);
            int startX = this.blockStartX;
            int startY = this.blockStartY;
            char[] details;
            boolean flg = false;
            for (String s : info) {
                if (s.equals(" ")) {
                    break;
                }
                details = s.toCharArray();
                String string;
                startX = this.blockStartX;
                for (int i = 0; i < s.length(); i++) {
                    flg = true;
                    string = "" + details[i];
                    if (bdf.isSpaceSymbol(string)) {
                        if (details.length == 1) {
                            flg = false;
                            startY += this.rowHeight;
                        } else {
                            startX += bdf.getSpaceWidth(string);
                        }

                    } else if (bdf.isBlockSymbol(string)) {
                        Block newBlock = bdf.getBlock(string, startX, startY);
                        newBlock.setOriginalnumberOfHits(newBlock.getNumberOfHits());
                        bdr.setHeight((int) newBlock.getCollisionRectangle().getHeight());
                        bdr.setWidth((int) newBlock.getCollisionRectangle().getWidth());
                        this.blockList.add(newBlock);
                        startX += newBlock.getCollisionRectangle().getWidth();
                    }
                }
                if (flg) {
                    startY += this.rowHeight;
                }
            }
        } catch (Exception e) {
        }

    }

    /**
     * Sets ball velocities.
     *
     * @param s the s
     */
    public void setBallVelocities(String s) {
        String[] balls = s.split(" ");
        String[] velocities = new String[2];
        double angle;
        double speed;
        for (int i = 0; i < balls.length; i++) {
            String ball = balls[i];
            velocities = ball.split(",");
            angle = Double.parseDouble(velocities[0]);
            speed = Double.parseDouble(velocities[1]);
            this.ballVelocities.add((Velocity.fromAngleAndSpeed(angle, speed)));
        }
        this.numBalls = this.ballVelocities.size();
    }

}
