package sample;

/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686
 */
public class BoardTest {

	public static void main(String[] args) {
		Board board = new Board();
		Player player1 = new Player("Roland");
		boolean check;

		//TESTING
		//void methods: reset, tilesPlayed, coordAndDirection, placeTilesOnBoard

// Testing reset method
		System.out.println("Initialise the board to a start state: \n");
		board.reset();
		System.out.println(board);
////Testing onBoardCheck method
//		System.out.println();
//		System.out.println("Testing onBoardCheck method:");
//		String word = "word";
//		//checking if the word is not our of bounds --> true
//		check=board.onBoardCheck(5,6,1,word.length());
//		System.out.println("onBoardCheckMethod:		Expected: true		Actual: " + check);
//		//checking if the word is not our of bounds --> false
//		check=board.onBoardCheck(14,14,1,word.length());
//		System.out.println("onBoardCheckMethod:		Expected: false		Actual: " + check);
//		System.out.println();
//
////is this even needed to be tested ???
////Testing tilesInFrame method
//		System.out.println("Testing tilesInFrame method:");
//		//should print out false as we don't know what the frame tiles are
//		System.out.println(player1);
//		check=board.tilesInFrame("hello",player1);
//		System.out.println("tilesInFrame:	Expected: false		Actual: " + check);
//		System.out.println("");
//
////Testing InMiddleCheck method
//		System.out.println("Testing InMiddleCheck method:");
//		//Horizontal Middle Check
//		//checks if the word goes through the middle
//		check = board.InMiddleCheck(7,7,"hello",1);
//		System.out.println("Horizontal:		Row: 7 Column: 7		Expected: true		Actual: " + check);
//
//		//checks if the checkRow increments and goes through the middle
//		check = board.InMiddleCheck(7,6,"hello",1);
//		System.out.println("Horizontal:		Row: 7 Column: 7		Expected: true		Actual: " + check);
//
//		//checks if if dosent go through the middle
//		check = board.InMiddleCheck(5,1,"hello",1);
//		System.out.println("Horizontal:		Row: 7 Column: 7		Expected: false		Actual: " + check);
//
//		//Vertical Middle Check
//		//checks if the word goes through the middle
//		check = board.InMiddleCheck(7,7,"hello",2);
//		System.out.println("Vertical:		Row: 7 Column: 7		Expected: true 		Actual: " + check);
//
//		//checks if the checkRow increments and goes through the middle
//		check = board.InMiddleCheck(6,7,"hello",2);
//		System.out.println("Vertical:		Row: 7 Column: 7		Expected: true		Actual: " + check);
//
//		//checks if if dosent go through the middle
//		check = board.InMiddleCheck(5,1,"hello",2);
//		System.out.println("Vertical:		Row: 7 Column: 7		Expected: false		Actual: " + check);
//
//
////Testing placeTilesOnBoard method
//		//Adding multiple words onto the board
//		board.placeTilesOnBoard(1, "HELLO", 7,7);
//		board.toString();
//		board.placeTilesOnBoard(2, "APPEN", 7,7);
//		board.toString();
//		board.placeTilesOnBoard(2, "JELO", 5,10);
//		board.toString();
//		board.placeTilesOnBoard(2, "NT", 6,8);
//		System.out.println(board.toString());
//
//// Testing word contact method, which returns if a given word will make contact with other words on the board
//		System.out.println("wordContact:\t Expected: true \tGiven: " + board.wordContact(6, 7, 5, 1));
//		System.out.println("wordContact:\t Expected: false \tGiven: " + board.wordContact(0, 0, 5, 1));
//		System.out.println("wordContact:\t Expected: false \tGiven: " + board.wordContact(0, 3, 7, 2));
//		System.out.println("wordContact:\t Expected: true \tGiven: " + board.wordContact(8, 5, 4, 1));
//		System.out.println("wordContact:\t Expected: true \tGiven: " + board.wordContact(5, 8, 8, 2));
//		System.out.println("wordContact:\t Expected: true \tGiven: " + board.wordContact(6, 6, 8, 2));
//		System.out.println("wordContact:\t Expected: true \tGiven: " + board.wordContact(9, 8, 2, 1));
//		System.out.println();
//
////Testing placeWordOnBoard method
//		//Allows user to choose starting spot for word and which direction to place letters
////		System.out.println("Testing coordAndDirection method: \n"); //commented off because i changed method old one just commented out *roland*
////		board.coordAndDirection("Hello", player1);
////		System.out.println(board);
////Testing reset method
//		//Clearing the board of all previous tests
//		board.reset();
//		System.out.println("Board is expected to be initialized to start state: \n" + board);
//
////Testing tilesPlayed method working with all other check methods also toString method is tested throughout tests
//		System.out.println("Testing tilesPlayed method: ");
//		board.tilesPlayed(player1);
//		System.out.println(board);
//		board.tilesPlayed(player1);
//		System.out.println(board);
//		//Board reset
//		board.reset();
//		System.out.println("Board reset: \n" + board);
//
//	//Testing scoreMultiplier method
//		System.out.println("Checking that letter values work on multipliers");
//		System.out.println("No letter multiplier Z: " + board.letterMultiplier(player1,0,0,'Z'));
//		System.out.println("No letter multiplier A: " + board.letterMultiplier(player1,0,0,'A'));
//		System.out.println("No letter multiplier V: " + board.letterMultiplier(player1,0,0,'V'));
//		System.out.println("Double letter multiplier: " + board.letterMultiplier(player1,0,3,'Z'));
//		System.out.println("Double letter multiplier: " + board.letterMultiplier(player1,0,3,'A'));
//		System.out.println("Double letter multiplier: " + board.letterMultiplier(player1,0,3,'V'));
//		System.out.println("Triple letter multiplier: " +board.letterMultiplier(player1,5,1,'Z'));
//		System.out.println("Triple letter multiplier: " +board.letterMultiplier(player1,5,1,'A'));
//		System.out.println("Triple letter multiplier: " +board.letterMultiplier(player1,5,1,'V'));
//		System.out.println();
//
//	//Testing totalMulitplier method
//		System.out.println("Checking that word multipliers register");
//		System.out.println("Triple word actual: " + board.totalMultiplier(0,0) + " Expected: 3");
//		System.out.println("Double word actual: " + board.totalMultiplier(1,1) + " Expected: 2");
//		System.out.println();
//
//	//Testing placeTilesOnBoard keeping track of letter score and word score multipliers
//		System.out.println("Testing the method keeps track of letter and word multipliers to send to playerScore method");
//		System.out.print("Expect: 3 and 15  Actual ");	//triple word on z=10 + a=1 + v=4
//		board.placeTilesOnBoard(player1,1, "ZAV", 0, 0);
//
//	//Testing playerScore from player class
//		System.out.println("The current score actual: " + player1.getScore() + " Expected: 45\n");
//		player1.playerScore(20);
//		System.out.println("Plus a 20pt word actual: " + player1.getScore() + " Expected: 65\n");
//		System.out.print("Expect: 1 and 29  Actual ");	//original z=10 + new z=10 + a=1 + v=8(double letter)
//		board.placeTilesOnBoard(player1,2, "ZAV", 0, 0);

//        player1.scoreDecrease(10);
//        System.out.println("minus 10pt word actual: " + player1.getScore() + " Expected: 55\n");

		//Testing counting extra letters in the word
		board.placeTilesOnBoard(player1,1, "ZAV", 7, 7);
		System.out.println("score actual: " + player1.getScore() + " Expected: 15\n");
		System.out.println("Word played: " + board.lastWordPlayed+ " Expected: ZAV\n");

		board.setLastWordPlayed("");

		board.placeTilesOnBoard(player1,2, "ZAV", 4, 7);
		System.out.println("score actual: " + player1.getScore() + " Expected: 40\n");
        System.out.println("Word played: " + board.lastWordPlayed + " Expected: ZAVZ\n");

		board.setLastWordPlayed("");

		board.placeTilesOnBoard(player1,2, "ZV", 6, 8);
		System.out.println("score actual: " + player1.getScore() + " Expected: 93\n");
		System.out.println("Word played: " + board.lastWordPlayed + " Expected: VZ ZAV \n");

		System.out.println(board);


	}
//*/
}
