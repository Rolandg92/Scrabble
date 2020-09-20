package sample;/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686

Stores the value of each tile -- pointValue assigns value while sample.Tile class takes the value for each letter
Stores the tiles currently in the pool -- Linked list letterBag holds all the tiles currently in pool
Allows the pool to be reset -- Reset method refills pool back to all 100 tiles
Allows display of the number of tiles in the pool -- sample.Frame toString prints out current amount of tiles left
Allows the pool to be checked to see if it is empty	-- sample.Frame toString lets player know bag is empty if draw is null
Allows tiles to be drawn at random from the pool -- Draw method uses random number to select which tile to pull
Allows the value of a tile to be queried -- queriedValue method to allow user to get value of certain tile.
*/
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Pool {
	Tile t;
	private LinkedList<Tile> letterBag = new LinkedList<Tile>();

	public void resetPool() {				//Method to fill the pool of letters for players to draw from
		letterBag.clear();
		//Creates the pool of all 100 letters including blanks in a string list
		String[] alphabet = "AAAAAAAAABBCCDDDDEEEEEEEEEEEEFFGGGHHIIIIIIIIIJKLLLLLMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ__".split("");

		for (String s : alphabet) {			//Gives value to each tile based on the current letter it represents by calling pointValue method
			t = new Tile(s, pointValue(s));
			letterBag.add(t);
		}
	}

	public LinkedList<Tile> get(){
		return letterBag;
	}

	public int poolSize() {					//Method to return the current amount of tiles in the bag which prints in the frame
		return letterBag.size();			//toString method in the frame class
	}

	public Tile draw(){						//Method to draw a random tile from the pool of letters while removing the tile from 
		Tile tile1 = null;					//the pool so it cannot be drawn again.

		if(letterBag.size() != 0) {			//Creating a random int that is used to select tile from pool
			Random r = new Random();
			int low = 0;
			int high = letterBag.size();
			int i = r.nextInt(high-low) + low;

			tile1 = letterBag.get(i);
			letterBag.remove(i);
		}
		return tile1;						//Returns the letter that was drawn		
	}

	public void addToBag(Tile t){
		letterBag.add(t);
	}

	public void queriedvalue() {			//Method allows player to ask the value of a chosen tile

		System.out.println("Choose which letter to find value");

		Scanner query = new Scanner(System.in);

		String que = query.nextLine();		//Saves user input as a string and toUppercase for simplicity
		que = que.toUpperCase();

		if(que.matches("[A-Z]+")) {
			System.out.println(pointValue(que));
		}else {
			throw new IllegalArgumentException("Must enter a letter");
		}
	}

	public int pointValue(String tile) {	//Method to assign point value to each tile
		int tileValue = 1;					//Initial value since its the most common tiles

		if("DG".contains(tile)) {			//Using contains to look for the current tile character in the string to determine point value
			tileValue = 2;
		}else if("BCMP".contains(tile)) {
			tileValue = 3;
		}else if("FHVWY".contains(tile)) {
			tileValue = 4;
		}else if("K".contains(tile)) {
			tileValue = 5;
		}else if("JX".contains(tile)) {
			tileValue = 8;
		}else if("QZ".contains(tile)) {
			tileValue = 10;
		}else if("_".contains(tile)) {
			tileValue = 0;
		}
		return tileValue;		//Return assigned point value of tile	
	}
}
