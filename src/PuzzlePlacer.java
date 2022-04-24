/** Class: PuzzlePlacer
 * @author Andrew Mooduto
 * Course: ITEC 3860 Spring 2022
 *
 *        This class contains attributes special to PuzzlePlacer.
 */

public class PuzzlePlacer extends Thing {

    private PuzzleList puzzles = new PuzzleList();

    public PuzzlePlacer(String thingName, String thingDescription, PuzzleList puzzles) {
        super(thingName, thingDescription);
        this.puzzles = puzzles;
    }

    public void setPuzzles(PuzzleList puzzles) {
        this.puzzles = puzzles;
    }

    public PuzzleList getPuzzles() {
        return puzzles;
    }

}