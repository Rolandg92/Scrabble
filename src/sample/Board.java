package sample;
/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686
 */

import java.util.*;

public class Board {
    String lastWordPlayed = "";
    int lastWordScore = 0;

    int rows = 15;
    int columns = 15;
    String[][] board = new String[rows][columns];

    public void setLastWordPlayed(String s) {
        lastWordPlayed = s;
    }

    public void setLastWordScore(int x) {
        lastWordScore = x;
    }

    public Board() {
        reset();
    }

    //clear the board while marking all word and letter multipliers
    public void reset() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (((i == 0 || i == 14) && (j == 3 || j == 11))
                        || ((i == 2 || i == 12) && (j == 6 || j == 8))
                        || ((i == 3 || i == 11) && (j == 0 || j == 7 || j == 14))
                        || ((i == 6 || i == 8) && (j == 2 || j == 6 || j == 8 || j == 12))
                        || (i == 7 && (j == 3 || j == 11))) {

                    board[i][j] = "2l";    //double letter

                } else if (((i == 1 || i == 13) && (j == 5 || j == 9))
                        || ((i == 5 || i == 9) && (j == 1 || j == 5 || j == 9 || j == 13))) {

                    board[i][j] = "3l"; //triple letter

                } else if (((i == 1 || i == 13) && (j == 1 || j == 13))
                        || ((i == 2 || i == 12) && (j == 2 || j == 12))
                        || ((i == 3 || i == 11) && (j == 3 || j == 11))
                        || ((i == 4 || i == 10) && (j == 4 || j == 10))) {

                    board[i][j] = "2w"; //double word

                } else if (((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14))
                        || (i == 7 && (j == 0 || j == 14))) {

                    board[i][j] = "3w";//triple word

                } else if (i == 7 && j == 7) {
                    board[i][j] = "**"; //middle
                } else {
                    board[i][j] = "  ";
                }
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    //allow the play of a word
    public void tilesPlayed(Player player) {
        boolean wordMatched = false;
        String wordPlayed = null;

        System.out.println(player);            //Printing frame for ease of player seeing current letters available

        while (wordMatched == false) {                    //loop till word uses only tiles in the players frame.
            System.out.print("Enter your word: ");

            Scanner usersWord = new Scanner(System.in);
            wordPlayed = usersWord.nextLine().toUpperCase();

            wordMatched = tilesInFrame(wordPlayed, player);        //check if letters are in frame return true if they are

            if (wordMatched == false) {
                System.out.println(">>Tiles for the word are not in your frame, Choose another word");
            }
        }
    }

    //Method to check each letter of entered word is in players frame
    public boolean tilesInFrame(String wordAttempt, Player player) {
        String[] tmpFrame = new String[7];
        int[] matchedFrame = new int[7];

        for (int z = 0; z < 7; z++) {
            tmpFrame[z] = player.frame.getTile(z).getTileLetter();
        }

        String word[] = wordAttempt.split("");
        int count = 0;

        for (int i = 0; i < word.length; i++) {                //comparing each letter of word to each letter of frame through the tile class
            for (int j = 0; j < 7; j++) {
                if (word[i].equals(tmpFrame[j])) {
                    tmpFrame[j] = null;
                    matchedFrame[i] = j;
                    count++;
                    break;
                }
            }
        }

        if (count == word.length) {
            for (int i = 0; i < count; i++) {
                player.frame.remove(matchedFrame[i]);
                matchedFrame[i] = 0;
            }
            return true;
        }
        return false;
    }

    //Method to choose where to place word and its orientation
    public boolean coordAndDirection(Player player, int tileRow, int tileColumn, String direction, String approvedWord) {
        boolean centre = false;
        boolean onboard = false;
        boolean contact = false;
        boolean firstTile = false;

        int flow = (direction.equals("V") ? 2 : 1);
        
        if (!(onBoardCheck(tileRow, tileColumn, flow, approvedWord.length()))) {
            return false;
        } else {
            onboard = true;
        }

        if (board[7][7].equals("**")) {
            centre = InMiddleCheck(tileRow, tileColumn, approvedWord, flow);
            contact = true;
        } else {
            centre = true;
            // checks if the word makes contact with other words
            contact = wordContact(tileRow, tileColumn, approvedWord.length(), flow);
        }
        //check if the tile is available to place the first letter/tile of a word
        firstTile = board[tileRow][tileColumn].matches(".[A-Z]");

        if (centre && onboard && contact && !firstTile) {
            return true;
        }
        return false;
    }

    //method to place tiles on the board
    public void placeTilesOnBoard(Player player, int orientation, String wordToBePlayed, int rowCoord, int colCoord) {
        int wordScore = 0;
        int wordMultiplier = 1;

        if (orientation == 1) {    //checking for letters before the word played
            if(colCoord-1>=0){
                while (board[rowCoord][colCoord - 1].matches(".*[A-Z].*")) {
                    if(colCoord==0){ break;}
                    colCoord--;
                }
            }
        } else {
            if(rowCoord-1>=0){
                while (board[rowCoord - 1][colCoord].matches(".*[A-Z].*")) {
                    if(rowCoord==0){ break;}
                    rowCoord--;
                }
            }
        }

        for (int i = 0; i < wordToBePlayed.length(); i++) {  //plays the word either horizontally or vertically
            if (board[rowCoord][colCoord].matches(".*[A-Z].*")) {
                wordScore += player.frame.getTileValue(board[rowCoord][colCoord].trim());
                lastWordPlayed = lastWordPlayed.concat(board[rowCoord][colCoord].trim());    //saving the word played in case of challenge

                i--;

            } else {
                int tmpMultiplier = 1;
                int tmpLetterValue = 0;

                wordScore += letterMultiplier(player, rowCoord, colCoord, wordToBePlayed.charAt(i));
                tmpMultiplier *= totalMultiplier(rowCoord, colCoord);
                wordMultiplier *= tmpMultiplier;

                board[rowCoord][colCoord] = " " + wordToBePlayed.charAt(i);

                lastWordPlayed = lastWordPlayed.concat(board[rowCoord][colCoord].trim());

                tmpLetterValue += player.frame.getTileValue(board[rowCoord][colCoord].trim());

                wordScore += extraWordsCreated(player, orientation, rowCoord, colCoord, tmpMultiplier, tmpLetterValue);
            }

            if (orientation == 1) {
                if(colCoord==14){ break;}
                colCoord++;
            } else {
                if(rowCoord==14){ break;}
                rowCoord++;
            }
        }
        while (board[rowCoord][colCoord].matches(".*[A-Z].*")) {    //checking for letters at the end of word played
            wordScore += player.frame.getTileValue(board[rowCoord][colCoord].trim());
            lastWordPlayed = lastWordPlayed.concat(board[rowCoord][colCoord].trim());
            if (orientation == 1) {
                if(colCoord+1<=14){
                    colCoord++;
                }else{
                    break;
                }
            } else {
                if(rowCoord+1<=14){
                    rowCoord++;
                }else{
                    break;
                }
            }
        }
        lastWordScore = wordScore * wordMultiplier;    //saving the score from played word in case of challenge
        player.playerScore(lastWordScore);
    }

    public int extraWordsCreated(Player player, int orientation, int rowCoord, int colCoord, int wordMulti, int letterMulti) {
        int scoring = 0;
        boolean letterBefore = false;
        boolean letterAfter = false;
        String tmpWord = "";

        if (orientation == 2) {    //checking for letters before the single tile is played
            if(colCoord-1>=0){
                while (board[rowCoord][colCoord - 1].matches(".*[A-Z].*")) {
                    if(colCoord==0){ break; }
                    colCoord--;
                    letterBefore = true;
                }
            }

        } else {
            if(rowCoord-1>=0){
                while (board[rowCoord - 1][colCoord].matches(".*[A-Z].*")) {
                    if(rowCoord==1){ break; }
                    rowCoord--;
                    letterBefore = true;
                }
            }
        }

        if (!letterBefore) {
            if (orientation == 2) {
                if(colCoord+1<=14){
                    letterAfter = board[rowCoord][colCoord + 1].matches(".*[A-Z].*");
                }
            } else {
                if(rowCoord+1<=14){
                    letterAfter = board[rowCoord + 1][colCoord].matches(".*[A-Z].*");
                }
            }
        }

        if (letterBefore || letterAfter) {
            while (board[rowCoord][colCoord].matches(".*[A-Z].*")) {    //checking for letters at the end of word played
                scoring += player.frame.getTileValue(board[rowCoord][colCoord].trim());
                tmpWord = tmpWord.concat(board[rowCoord][colCoord].trim());
                if (orientation == 2) {
                    if(colCoord+1<=14){
                        colCoord++;
                    }else{
                        break;
                    }
                } else {
                    if(rowCoord+1<=14){
                        rowCoord++;
                    }else{
                        break;
                    }
                }
            }
            scoring += letterMulti;
        }
        lastWordPlayed = tmpWord + " " + lastWordPlayed;
        return scoring * wordMulti;
    }

    //Method to add letter multipliers from board
    public int letterMultiplier(Player p, int rowC, int colC, char wordLetter) {
        String s = Character.toString(wordLetter);

        if (board[rowC][colC].equals("2l")) {
            return p.frame.getTileValue(s) * 2;
        } else if (board[rowC][colC].equals("3l")) {
            return p.frame.getTileValue(s) * 3;
        }
        return p.frame.getTileValue(s);
    }

    public int totalMultiplier(int rowC, int colC) {
        if (board[rowC][colC].equals("2w")) {
            return 2;
        } else if (board[rowC][colC].equals("3w")) {
            return 3;
        }
        return 1;
    }

    // Checking if the word is out of bounds
    public boolean onBoardCheck(int row, int column, int flow, int word) {
        int occupiedTiles = 0;

        //Loops through the row or column counting squares that are occupied by a tile already.
        if (flow == 1) {
            for (int i = 0; i + column < 15; i++) {
                if (board[row][column + i].matches(".*[A-Z].*")) {
                    occupiedTiles++;
                }
            }
            if (column + occupiedTiles + word - 1 > 14) {
                return false;
            }
        } else if (flow == 2) {
            for (int i = 0; i + row < 15; i++) {
                if (board[row + i][column].matches(".*[A-Z].*")) {
                    occupiedTiles++;
                }
            }
            if (row + occupiedTiles + word - 1 > 14) {
                return false;
            }
        }
        return true;
    }

    //checking if the first word goes through the middle --> horizontal && vertical
    public boolean InMiddleCheck(int rowCheck, int columnCheck, String word, int flow) {

        if (flow == 1) {
            if (rowCheck == 7) {
                for (int i = 0; i < word.length(); i++) {
                    if (columnCheck == 7) {
                        return true;
                    }
                    columnCheck++;
                }
            }
        } else {
            if (columnCheck == 7) {
                for (int i = 0; i < word.length(); i++) {
                    if (rowCheck == 7) {
                        return true;
                    }
                    rowCheck++;
                }
            }
        }
        return false;
    }

    // Calls checkSides and checkEnds to check if the word makes contact with other words on the board
    public boolean wordContact(int row, int col, int length, int direction) {
        return checkEnds(row, col, length, direction) || checkSides(row, col, length, direction);
    }

    // Check if both end of the word make contact with other letter, first and last letter of the word.
    private boolean checkEnds(int row, int col, int length, int d) {
        boolean cond = false;
        if (d == 1) {

            if (col - 1 >= 0) {
                cond = board[row][col - 1].matches(".[A-Z]");
            }
            if (col + length < 15 && !cond) {
                cond = board[row][col + length].matches(".[A-Z]");
            }
        } else {

            if (row - 1 >= 0) {
                cond = board[row - 1][col].matches(".[A-Z]");
            }
            if (row + length < 15 && !cond) {
                cond = board[row + length][col].matches(".[A-Z]");
            }
        }
        return cond;
    }

    // Method needed to check if the sides of the word make contact with other letters on the board
    private boolean checkSides(int row, int col, int length, int d) {
        boolean cond = false;
        if (d == 1) {
            for (int i = col; i < (col + length) && !cond; i++) {
                // if any squares on the board have tile that will be included in the word, return true
                cond = board[row][i].matches(".[A-Z]");
                //check for the tiles around the squares on the board the word is going to be placed
                if (row - 1 >= 0 && !cond) {
                    cond = board[row - 1][i].matches(".[A-Z]");
                }
                if (row + 1 < 15 && !cond) {
                    cond = board[row + 1][i].matches(".[A-Z]");
                }
            }
        } else {
            for (int i = row; i < (row + length) && !cond; i++) {

                cond = board[i][col].matches(".[A-Z]");

                if (col - 1 >= 0 && !cond) {
                    cond = board[i][col - 1].matches(".[A-Z]");
                }
                if (col + 1 < 15 && !cond) {
                    cond = board[i][col + 1].matches(".[A-Z]");
                }
            }
        }
        return cond;
    }

    //Printing the board
    public String toString() {
        StringBuilder reString = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                reString.append("[" + board[i][j] + "]");
            }
            reString.append("\n");
        }
        return reString.toString();
    }
}

