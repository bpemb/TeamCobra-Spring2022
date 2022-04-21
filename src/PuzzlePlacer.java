/** Class: PuzzlePlacer
 * @author Andrew Mooduto
 * Course: ITEC 3860 Spring 2022
 *
 *        his class contains attributes special to PuzzlePlacer.
 */

public class PuzzlePlacer extends Thing {

    private PuzzleController puzzles = new PuzzleController();

    public PuzzlePlacer(String thingName, String thingDescription, PuzzleController puzzles) {
        super(thingName, thingDescription);
        this.puzzles = puzzles;
    }

    public void setPuzzles(PuzzleController puzzles) {
        this.puzzles = puzzles;
    }

    public PuzzleController getPuzzles() {
        return puzzles;
    }

}