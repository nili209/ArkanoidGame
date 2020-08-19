package component;

import java.io.Serializable;
import java.io.IOException;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> list;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.list = new ArrayList<ScoreInfo>(size);
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        this.list.add(score);
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        this.list = sort();

        return this.list;
    }

    /**
     * Sort list.
     *
     * @return the list
     */
    public List<ScoreInfo> sort() {
        List<ScoreInfo> sortedList = new ArrayList<ScoreInfo>(this.size);
        while (this.list.size() > 0) {
            int i;
            int max = 0;
            int maxIndex = 0;
            for (i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getScore() > max) {
                    max = this.list.get(i).getScore();
                    maxIndex = i;
                }
            }
            sortedList.add(this.list.get(maxIndex));
            this.list.remove(this.list.get(maxIndex));
        }
        return sortedList;

    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (ScoreInfo s : this.list) {
            if (score > s.getScore()) {
                return this.list.indexOf(s) + 1;
            }
        }
        return this.list.size() + 1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
//        for (ScoreInfo s : this.list) {
//            this.list.remove(s);
//        }
        this.list.removeAll(this.list);
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable newHighScore = null;
        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));

            // unsafe down casting, we better be sure that the stream really contains a Person!
            newHighScore = (HighScoresTable) objectInputStream.readObject();
        } catch (FileNotFoundException e) { // Can't find file to open
            //System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            //System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            //System.err.println("Failed reading object");
            //e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        this.list = newHighScore.list;
        this.size = newHighScore.size;
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable st = new HighScoresTable(10);
        try {
            st.load(filename);
        } catch (IOException e) {
            return st;
        }
        return st;
    }

//    public static void main(String[] args) {
//        ScoreInfo scoreInfo1 = new ScoreInfo("nili", 100);
//        ScoreInfo scoreInfo2 = new ScoreInfo("hana", 63);
//        ScoreInfo scoreInfo3 = new ScoreInfo("Ima", 150);
//        HighScoresTable table = new HighScoresTable(3);
//        table.add(scoreInfo1);
//        table.add(scoreInfo2);
//        table.add(scoreInfo3);
//        File f = new File("nili");
////       try {
////           table.save(f);
////       } catch (Exception e) {
////           System.out.println("cant write to file");
////       }
//        try {
//            table.load(f);
//            System.out.println(table.list.get(1).getName());
//        } catch (Exception e) {
//            System.out.println("cant read from file");
//        }
//    }
}
