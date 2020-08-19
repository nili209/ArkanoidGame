package component;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private int width = -1;
    private int widthDef = -1;
    private int height = -1;
    private int heightDef = -1;
    private int hitPoints = -1;
    private int hitPointsDef = -1;
    private String symbol = null;
    private String symbolDef = null;
    private String stroke = null;
    private String strokeDef = null;
    private String fill = null;
    private String fillDef = null;
    private String fillK = null;
    private String fillKDef = null;
    private int k = -1;
    private boolean flag = true;
    private boolean color = false;
    private boolean image = false;
    private Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
    private List<Integer> kListColor = new ArrayList<Integer>();
    private List<String> fillKlistColor = new ArrayList<String>();
    private List<Integer> kListImage = new ArrayList<Integer>();
    private List<String> fillKlistImage = new ArrayList<String>();


    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                blocksDefinitionReader.setText(line, spacerWidths, blockCreators);
            }
        } catch (IOException e) {

        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);

    }

    /**
     * Sets text.
     *
     * @param line          the line
     * @param spacerWidths  the spacer widths
     * @param blockCreators the block creators
     */
    public void setText(String line, Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        String[] info = line.split(" ");
        switch (info[0]) {
            case "sdef":
                setSpacerWidth(info, spacerWidths);
                break;
            case "default":
                for (int i = 0; i < info.length - 1; i++) {
                    String[] def = info[i + 1].split(":");
                    set(def);
                    setDef(def);
                }
                break;
            case "bdef":
                String[] s;
                setField();
                for (int i = 1; i < info.length; i++) {
                    s = info[i].split(":");
                    set(s);
                }
                blockCreators.put(this.symbol, new CreateBlock(width, height, hitPoints, stroke, fill, kListColor,
                        kListImage, fillKlistColor, fillKlistImage));
                this.fillKlistColor = new ArrayList<>();
                this.fillKlistImage = new ArrayList<>();
                this.kListColor = new ArrayList<>();
                this.kListImage = new ArrayList<>();
                break;
            default:
                break;
        }
    }

    /**
     * Sets field.
     */
    public void setField() {
        this.hitPoints = this.hitPointsDef;
        this.width = this.widthDef;
        this.height = this.heightDef;
        this.fill = this.fillDef;
        this.stroke = this.strokeDef;
        this.symbol = this.symbolDef;
    }

    /**
     * Sets def.
     *
     * @param def the def
     */
    public void setDef(String[] def) {
        switch (def[0]) {
            case "width":
                this.widthDef = this.width;
                break;
            case "height":
                this.heightDef = this.height;
                break;
            case "hit_points":
                this.hitPointsDef = this.hitPoints;
                break;
            case "symbol":
                this.symbolDef = this.symbol;
                break;
            case "stroke":
                this.strokeDef = this.stroke;
                break;
            case "fill":
                this.fillKDef = this.fill;
                break;
            default:
                break;
        }
    }

    /**
     * Set.
     *
     * @param def the def
     */
    public void set(String[] def) {
        switch (def[0]) {
            case "width":
                this.width = Integer.parseInt(def[1]);
                break;
            case "height":
                this.height = Integer.parseInt(def[1]);
                break;
            case "hit_points":
                this.hitPoints = Integer.parseInt(def[1]);
                break;
            case "symbol":
                this.symbol = def[1];
                break;
            case "stroke":
                this.stroke = def[1];
                break;
            default:
                if (def[0].startsWith("fill")) {
                    String[] s = def[0].split("-");
                    if (def[0].length() == 4) {
                        this.k = 1;
                    } else {
                        this.k = Integer.parseInt(s[1]);
                    }

                    if (def[1].startsWith("color")) {
                        this.color = true;
                        this.kListColor.add(this.k);
                        this.fillK = def[1];
                        this.fillKlistColor.add(this.fillK);
                    } else {
                        this.image = true;
                        this.kListImage.add(this.k);
                        this.fillKlistImage.add(def[1]);

                    }
                }
                break;
        }
    }


    /**
     * Sets spacer width.
     *
     * @param info         the info
     * @param spacerWidths the spacer widths
     */
    public void setSpacerWidth(String[] info, Map<String, Integer> spacerWidths) {
        String[] information = new String[info.length - 1];
        for (int i = 0; i < information.length; i++) {
            information[i] = info[i + 1].split(":")[1];
        }
        for (int i = 0; i < information.length; i++) {
            spacerWidths.put(information[i], Integer.parseInt(information[++i]));
        }
    }

    /**
     * Sets height.
     *
     * @param h the height
     */
    public void setHeight(int h) {
        this.height = h;
    }

    /**
     * Sets width.
     *
     * @param w the width
     */
    public void setWidth(int w) {
        this.width = w;
    }
}
