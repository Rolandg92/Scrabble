package sample;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI{

    private Board boardObject = new Board();
    private Dictionary dictionary = new Dictionary();
    private Player playerOne, playerTwo;
    private Player currentPlayer = null;

    private static final int H_TILES = 15;
    private static final int V_TILES = 15;
    private static final int TILE_SIZE = 30;
    private FxTile[][] fxBoard = new FxTile[V_TILES][H_TILES];
    private FxTile[] fxFrame = new FxTile[7];

    public UI(Player player1, Player player2){
        playerOne = player1;
        playerTwo = player2;
    }
    public Parent contentInWindow() throws IOException {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(2);
        root.setHgap(2);
        root.setStyle("-fx-background-color: #c9c6a3; -fx-border-color: #a39163;" +
                "    -fx-border-width: 11;");

        String [][]board = boardObject.getBoard();

        //00 tile on the board
        FxTile tile_00 = new FxTile("R\\C");
        tile_00.setTileColor("#007994");
        tile_00.tileLetter.setStyle("-fx-font-size: 16; -fx-fill: black; -fx-font-weight: bold");
        root.add(tile_00, 0, 0);
        // adding row and col labels
        for(int i = 0; i < V_TILES; i++){
            FxTile row = new FxTile(i);
            FxTile col = new FxTile(i);
            root.add(col, 0, (i + 1));
            root.add(row, (i + 1), 0);
        }

        //creating the tiles on the board
        for(int y = 0; y < V_TILES; y++){
            for(int x = 0; x < H_TILES; x++){
                String tileContent = board[y][x].toUpperCase();
                FxTile tempTile = new FxTile(tileContent);
                fxBoard[y][x] = tempTile;
                root.add(tempTile, (x + 1), (y + 1));
            }
        }

        // set the frame bellow the board on the scene
        HBox baseFrame = new HBox();
        for(int f = 0; f < 7; f++){
            FxTile frameTile = new FxTile(" ");
            fxFrame[f] = frameTile;
            baseFrame.getChildren().add(frameTile);
        }

        baseFrame.setAlignment(Pos.CENTER);
        baseFrame.setStyle("-fx-background-color: #055069; -fx-border-color: #042b38; -fx-border-width: 3");
        baseFrame.setSpacing(4);
        root.add(baseFrame, 0, 16, 16, 1);

        return root;
    }

    private class FxTile extends StackPane{
        private String letter;
        private String tileColor = "#ededdf";
        private String borderColor = "#ffffff";
        private boolean isLetter = false;
        private Rectangle boardTile = new Rectangle (TILE_SIZE, TILE_SIZE);
        private Text tileLetter = new Text();
        private Text letterValue = new Text();

        //constructor that takes a string as input
        public FxTile(String letterIn){
            setLetter(letterIn);

            // setting round corners
            boardTile.setArcHeight(5);
            boardTile.setArcWidth(5);

            tileLetterStyle();
            getChildren().addAll(boardTile, tileLetter, letterValue);
        }
        //constructor taking int value, used to created row and column headers
        public FxTile(int rowCol){
            tileLetter.setText(String.valueOf(rowCol));
            boardTile.setStyle("-fx-stroke: black; -fx-fill: #ededdf");
            tileLetter.setStyle("-fx-font-size: 20; -fx-fill: black;");

            getChildren().addAll(boardTile, tileLetter);
        }

        private void tileLetterStyle(){
            Frame tempFrame = new Frame();
            if(isLetter){
                int valueOfLetter = tempFrame.getTileValue(letter);
                letterValue.setText(String.valueOf(valueOfLetter));
                letterValue.setStyle("-fx-font-size: 8; -fx-fill: black;");
                tileLetter.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-fill: white;");
                setAlignment(letterValue, Pos.BOTTOM_RIGHT);
                setMargin(letterValue, new Insets(2, 4,2, 2));
            }
            else{
                tileLetter.setStyle("-fx-font-size: 18; -fx-fill: white;");
            }
        }

        public void setTileColor(String color){
            if(color.contains("#")){
                tileColor = color;
            }
            else{
                tileColor = "#ededdf";
            }
        }

        private void setBoardTileStyle(){
            backgroundColorBasedOnTile();
            boardTile.setStyle("-fx-stroke: " + borderColor + "; -fx-stroke-width: 2; -fx-fill: " + tileColor);
        }

        private void tileStyle(){
            tileLetterStyle();
            setBoardTileStyle();
        }

        private void setLetter(String letterInput){
            letterInput = letterInput.toUpperCase().trim();
            letter = letterInput;
            setIsLetter();
            tileLetter.setText(letterInput);
            tileStyle();
        }

        private void setIsLetter(){
            isLetter = letter.matches("[A-Z_]");
        }
        private void backgroundColorBasedOnTile(){
            if(isLetter){
                tileColor = "#ff9100";
            }
            else{
                if(letter.contains("2L")){
                    tileColor = "#007994";
                    borderColor = "#007994";
                }
                else if(letter.contains("2W") || letter.contains("**")){
                    tileColor = "#58bebf";
                    borderColor = "#58bebf";
                }
                else if(letter.contains("3L")){
                    tileColor = "#d16f5e";
                    borderColor = "#d16f5e";
                }
                else if(letter.contains("3W")){
                    tileColor = "#85174e";
                    borderColor = "#85174e";
                }
                else{
                    tileColor = "#ededdf";
                    borderColor = "#ffffff";
                }
            }
        }
    }

    public void refreshBoard(){
        String tempLetter;
        String[][] tempBoard = boardObject.getBoard();
        for(int y = 0; y < V_TILES; y++){
            for(int x = 0; x < H_TILES; x++){
                tempLetter = tempBoard[y][x];
                fxBoard[y][x].setLetter(tempLetter);
            }
        }
    }

    public void refreshUiFrame(Player player){
        Tile[] frame = player.frame.getFrame();
        String tempString = null;

        for(int x = 0; x < frame.length; x++){
            tempString = frame[x].getTileLetter();
            fxFrame[x].setLetter(tempString);
        }
    }

    private void exchange(Player p){
        Frame tempFrame = p.frame;
        Pool tempPool = p.frame.pool;
        Tile tempTile;

        Scanner numInput = new Scanner(System.in);
        System.out.println("Frame:	" + tempFrame);
        System.out.println("Number of tiles to be exchanged: ");
        int num = numInput.nextInt();

        for(int i = 0; i < num; i++) {
            Scanner exInput = new Scanner(System.in);
            System.out.println("Tile 0~6 to remove:	");
            int tileRemoving = exInput.nextInt();
            tempTile = tempFrame.remove(tileRemoving);
            tempPool.addToBag(tempTile);
        }
        System.out.println("Frame:	" + tempFrame);
    }

    // changes the current player depending on which player's turn is
    //if player 2 is the current player and the player completed its action the switch to player 1
    public void switchPlayers(){
        if(currentPlayer == playerOne){
            currentPlayer = playerTwo;
        }
        else if(currentPlayer == playerTwo){
            currentPlayer = playerOne;
        }
    }

    //displays helpful information
    public String help(){
        StringBuilder str = new StringBuilder();
        str.append("\n======================= * HELP * =======================");
        str.append("\nCOMMANDS:");
        str.append("\nEnter [7 7 V WORD] for entering a word row,column,H or V for orientation,your word");
        str.append("\nEnter [7 7 V W_RD O] if the word contains '_', enter the letter that it substitutes at the end");
        str.append("\nEnter 'PASS' to pass your turn");
        str.append("\nEnter 'QUIT' to end game");
        str.append("\nEnter 'EXCHANGE' to switch out some or all of your tiles");
        str.append("\nEnter 'CHALLENGE' to challenge a words spelling");
        str.append("\nEnter ['NAME' YourName], YourName should be the name you want to assign for yourself");
        str.append("\n======================= x HELP x =======================");
        return str.toString();
    }

    //returns -1 for error, 0 for unsuccessful, 1 for successful challenge
    public int challenge() {
        System.out.println("[======================== * CHALLENGE * ========================]");
        int returnValue = -1;
        int lstWordValue = boardObject.lastWordScore;
        String[] tmpWord = boardObject.lastWordPlayed.trim().split(" ");
        String temporaryWord = "";

        for (int i = 0; i < tmpWord.length; i++) {
            temporaryWord = tmpWord[i].trim().toUpperCase();
            System.out.println("\nChallenged word: " + temporaryWord);

            if (!temporaryWord.equals("")) {
                if (!dictionary.dictionaryChecker(temporaryWord)) {
                    System.out.println("The challenge was successful");

                    if (currentPlayer.equals(playerOne)) {
                        playerTwo.challenged(lstWordValue);
                    } else if (currentPlayer.equals(playerTwo)) {
                        playerOne.challenged(lstWordValue);
                    }
                    returnValue = 1;
                } else {
                    System.out.println(" >> The challenged word is a valid english word <<");
                    returnValue = 0;
                }
            }
        }

        System.out.println(" >> The challenge method cannot be called at this point in the game <<");
        return returnValue;
    }

    private void stats(){
        System.out.println("====================== *Stats* ======================");
        System.out.println("Player: " + playerOne.getNames() + "\t\t\tScore:" + playerOne.getScore());
        System.out.println("Player: " + playerTwo.getNames() + "\t\t\tScore:" + playerTwo.getScore());
        System.out.println("=====================================================");
    }

    //method to substitute the blanks in the frame with the letter entered so that it does not conflict
    //when placed on the board
    private void substituteBlank(String[] s){
        Tile[] tempFrame = currentPlayer.frame.getFrame();
        int y = 0;
        for(int x = 0; x < tempFrame.length && y < s.length; x++){
            if(tempFrame[x].getTileLetter().equals("_")){
                tempFrame[x].setTileLetter(s[y]);
                y++;
            }
        }
    }

    private void endGame(){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx *Game Over* xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        if(playerOne.getScore() > playerTwo.getScore()){
            System.out.println(" **Player [" + playerOne.getNames() + "] is the winner **");
        }
        else if(playerOne.getScore() < playerTwo.getScore()){
            System.out.println(" **Player [" + playerTwo.getNames() + "] is the winner **");
        }
        else{
            System.out.println(" ******* DRAW *******");
        }
    }

    //method that calls getInput method and refreshes the board
    public void playGame(){

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        currentPlayer = playerOne;
                        boolean errorCondition;
                        boolean sentinel = true;

                        String[] commands = null;
                        Scanner scan = new Scanner(System.in);

                        while(sentinel) {

                            //refresh the ui frame
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() { refreshUiFrame(currentPlayer); }
                            });

                            System.out.println("\n▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ " + currentPlayer.getNames() + "'s Turn ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                            System.out.println(currentPlayer);
                            System.out.println("Example: [7] [7] [V] [WORD]");  //give and example of input style
                            System.out.print("Input: ");

                            String inLine = scan.nextLine();
                            commands = inLine.toUpperCase().trim().split("\\s+");   //separates on spaces " "
                            System.out.println(commands.length);
                            if (commands[0].equals("PASS")) {
                                switchPlayers();
                                System.out.println(">>> Round passed <<<");
                            } else if (commands[0].equals("QUIT")) {
                                endGame();
                                System.exit(1);
                            } else {
                                if(commands[0].equals("NAME")){
                                    if(commands.length > 1){
                                        currentPlayer.setName(commands[1]);
                                    }
                                }
                                else if(commands[0].equals("HELP")) {
                                    System.out.println(help());

                                } else if ((commands.length == 4 || commands.length == 5) && (commands[2].equals("V") || commands[2].equals("H")) &&
                                        (Pattern.matches("[0-9]+", commands[0]) == true) &&
                                        (Pattern.matches("[0-9]+", commands[1]) == true)) {

                                    // this help display an incorrect input message if the conditions to place a tile
                                    //on the board are not met
                                    errorCondition = true;

                                    //assigning int to the direction for calling method to place on board
                                    int direct = (commands[2].equals("V") ? 2 : 1);

                                    int xCoord = Integer.parseInt(commands[0]); //converting the coordinates from string to int
                                    int yCoord = Integer.parseInt(commands[1]);

                                    if (boardObject.coordAndDirection(currentPlayer, xCoord, yCoord, commands[2], commands[3]) == true) { //calling board method to check word is in frame
                                        if (boardObject.tilesInFrame(commands[3], currentPlayer) == true) { //doing all checks on word placement

                                            if(commands.length == 5){
                                                String []temp = commands[4].trim().split("");

                                                for(int x = 0; x < temp.length; x++){
                                                    commands[3] = commands[3].replaceFirst("_", temp[x]);
                                                }
                                                substituteBlank(temp);
                                                temp = null;
                                            }

                                            boardObject.setLastWordPlayed("");
                                            boardObject.setLastWordScore(0);
                                            boardObject.placeTilesOnBoard(currentPlayer,direct, commands[3], xCoord, yCoord);
                                            //System.out.println(player); //for testing tiles removed from frame
                                            switchPlayers();
                                            errorCondition = false;
                                        }
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            refreshBoard();
                                            refreshUiFrame(currentPlayer);
                                        }
                                    });

                                    if(errorCondition){
                                        System.out.println("Invalid input. No tiles were placed on the board");
                                    }
                                } else if (commands[0].equals("EXCHANGE")) {
                                    exchange(currentPlayer);

                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            refreshUiFrame(currentPlayer);
                                        }
                                    });

                                } else if (commands[0].equals("CHALLENGE")) {
                                    if(challenge() == 0){
                                        switchPlayers();
                                    }
                                }
                            }

                            stats();

                        }// end while loop

                        return null;
                    }
                };
            }
        };
        service.start();
    }
}