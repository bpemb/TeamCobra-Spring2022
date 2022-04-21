/** Class: Puzzle
 * @author Andrew Mooduto
 * Course: ITEC 3860 Spring 2022
 *
 *        his class contains attributes special to Puzzle.
 */

public class Puzzle extends Thing {

    private String puzzleAnswer;
    private String puzzleHint;
    private int roomID;
    private Room location;

    public Puzzle(String thingName, String thingDescription, String puzzleAnswer, String puzzleHint, int roomID,
                  Room location) {
        super(thingName, thingDescription);
        this.puzzleAnswer = puzzleAnswer;
        this.puzzleHint = puzzleHint;
        this.roomID = roomID;
        this.location = location;
    }


    public String getAnswer() {
        return puzzleAnswer;
    }


    public void setAnswer(String answer) {
        this.puzzleAnswer = answer;
    }


    public String getHint() {
        return puzzleHint;
    }


    public void setHint(String hint) {
        this.puzzleHint = hint;
    }


    public int getRoomID() {
        return roomID;
    }


    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }


    public Room getLocation() {
        return location;
    }


    public void setLocation(Room location) {
        this.location = location;
    }

    public String toString() {
        return "puzzleAnswer: " + puzzleAnswer;


    }

}
