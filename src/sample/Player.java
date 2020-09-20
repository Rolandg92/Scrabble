package sample;/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686

Allows the player data to be reset            OK -> playerReset() rests the frame, name and score
Allows the name of the player to be set       OK -> names() -> sets with user input and setScore() -> sets by objects
Allows a players score to be increased       OK -> increaseScore(int x) -> increments the score as a tile is being removed
Allows access to their score                  OK -> getScore() which gets the score of the player
Allows access to a players frame (tiles)     OK -> in toString method which prints out the frame they have
Allows display of a players name              OK -> toString method prints out the information of the players aka their name,score and frame
*/
import java.util.Scanner;                               //scanner library

public class Player {
    Frame frame = new Frame();                          //creating an object of frame

    private String names;                               //string to store names of the players
    private int score = 0;                              //int to store to store the score

    public Player(String name) {
        playerReset(name);
    }

    public String getNames() {
        return names;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    //method that assigns the names
    public void setName(String name) {                                 // user-input for player names
        names = name;                       //takes in userinput and stores it into a string called "names"
    }

    public void playerReset(String name) {                         //resets players frame (aka current frame) , name and score to zero
        frame.resetFramePool();
        setName(name);
        setScore(0);
    }

    public void playerScore(int x) {    //-------------------------------new
        score += x;
    }

    public void scoreDecrease(int x) {
        if(score - x < 0){
            score=0;
        }else{
            score -= x;
        }
    }

    public void increaseScore(int x) { //increases/adds the score by calling the remove method from frame-------old
        Tile tmpTile = frame.remove(x);
        if(tmpTile != null) {
            score += tmpTile.getTileValue();
        }
    }

    public void challenged(int x){ scoreDecrease(x); }

    public String toString() {                          //toString method -> prints out the output
        return "Player: " + getNames() + "\t" + "Score:" + getScore() + "\r\n" + frame;
    }
}






