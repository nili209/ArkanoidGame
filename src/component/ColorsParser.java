package component;

import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        switch (s) {
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
            case "orange":
                return Color.ORANGE;
            case "green":
                return Color.GREEN;
            case "cyan":
                return Color.CYAN;
            case "blue":
                return Color.BLUE;
            case "white":
                return Color.WHITE;
            case "black":
                return Color.BLACK;
            case "lightGray":
                return Color.lightGray;
            case "gray":
                return Color.gray;
            case "pink":
                return Color.pink;
                default:
                    String[] rgb = s.split(",");
                    return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        }

    }
}