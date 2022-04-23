import java.util.ArrayList;

/**
 * Class: PuzzleList
 * @author Andrew
 * 			
 * 			The functionality of this class is to check whether a puzzle exist or not in a room.
 *
 */

public class PuzzleList extends ArrayList<Puzzle> {

    public String puzzleFinder() {
        String s = "";

        switch (this.size()) {
            case 0:
                s = "There is no puzzle in this room";
                break;
            default:
                for (int i = 0; i < this.size(); i++) {
                    Puzzle x = this.get(i);
                    s += x.getThingName() + ": " + x.getThingDescription() + "\n";
                }
                break;
        }
        return s;
    }
}