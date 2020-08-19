package component;

import graphics.Point;
import graphics.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Create block.
 */
public class CreateBlock implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private String stroke;
    private String fill;
    private Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
    private List<Integer> kListColor = new ArrayList<Integer>();
    private List<Integer> kListImage = new ArrayList<Integer>();
    private List<String> fillKlistColor = new ArrayList<String>();
    private List<String> fillKlistImage = new ArrayList<String>();

    /**
     * Instantiates a new Create block.
     *
     * @param width          the width
     * @param height         the height
     * @param hitPoints      the hit points
     * @param stroke         the stroke
     * @param fill           the fill
     * @param kListColor     the k list color
     * @param kListImage     the k list image
     * @param fillKlistColor the fill klist color
     * @param fillKlistImage the fill klist image
     */
    public CreateBlock(int width, int height, int hitPoints, String stroke, String fill, List<Integer> kListColor,
                       List<Integer> kListImage, List<String> fillKlistColor, List<String> fillKlistImage) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.fill = fill;
        this.kListColor = kListColor;
        this.kListImage = kListImage;
        this.fillKlistColor = fillKlistColor;
        this.fillKlistImage = fillKlistImage;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = new Block(new Rectangle(new Point(xpos, ypos), width, height));
        block.setHitNumbers(this.hitPoints);
        String color;
        if (fill == null) {
            for (int k : kListColor) {
                if (k == hitPoints) {
                    fill = fillKlistColor.get(kListColor.indexOf(k));
                }
            }
            for (int k : kListImage) {
                if (k == hitPoints) {
                    fill = fillKlistImage.get(kListImage.indexOf(k));
                }
            }
        }
        if (stroke != null) {
            color = stroke.substring(6, stroke.length() - 1);
            if (stroke.startsWith("color")) {
                if (color.startsWith("RGB")) {
                    String s = color.substring(4, color.length() - 1);
                    Color strokeColor = new ColorsParser().colorFromString(s);
                    block.setFrame(strokeColor);
                } else {
                    Color strokeColor = new ColorsParser().colorFromString(color);
                    block.setFrame(strokeColor);
                }

            }
        }
        if (fill.startsWith("color")) {
            color = fill.substring(6, fill.length() - 1);
            if (fill.startsWith("color")) {
                if (color.startsWith("RGB")) {
                    String s = color.substring(4, color.length() - 1);
                    Color fillColor = new ColorsParser().colorFromString(s);
                    block.setColor(fillColor);
                } else {
                    Color fillColor = new ColorsParser().colorFromString(color);
                    block.setColor(fillColor);
                }
            } else {
                String image = fill.substring(6, fill.length() - 1);
                Image image1 = null;
                try {
                    //image1 = ImageIO.read(new File("resources/" + image));
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                    //Reader reader = new InputStreamReader(is);
                    image1 = ImageIO.read(is);
                    block.setImages(image1);
                } catch (IOException e) {

                }
            }
        }
        for (String s : fillKlistColor) {
            if (s.startsWith("color")) {
                color = s.substring(6, s.length() - 1);
                if (color.startsWith("RGB")) {
                    String s1 = color.substring(4, color.length() - 1);
                    Color fillKColor = new ColorsParser().colorFromString(s1);
                    this.colorMap.put(kListColor.get(this.fillKlistColor.indexOf(s)), fillKColor);
                } else {
                    Color fillKColor = new ColorsParser().colorFromString(color);
                    this.colorMap.put(kListColor.get(this.fillKlistColor.indexOf(s)), fillKColor);
                }
            }
        }
        String image;
        for (String s : fillKlistImage) {
            image = s.substring(6, s.length() - 1);
            Image image1 = null;
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(image);
                image1 = ImageIO.read(is);
               this.imageMap.put(kListImage.get(fillKlistImage.indexOf(s)), image1);
            } catch (IOException e) {

            }

        }
        block.setImageMap(this.imageMap);
        block.setColorMap(this.colorMap);
        block.setkListColor(this.kListColor);
        block.setkListImage(this.kListImage);
        return block;
    }
}
