package sample;
/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686
 */
//Stores the letters that each player has in their frame	::DONE
//Allows letters to be removed from the frame				::DONE
//Allows a check to be made if letters are in the frame		::DONE
//Allows a check to be made to see if the frame is empty	::DONE
//Allows access to the letters in the frame					::DONE
//Allows a frame to be refilled from the pool				::DONE
//Allows a frame to be displayed							::DONE
public class Frame{

	Pool pool = new Pool();
	private Tile []frameTiles = new Tile[7];

	//get a tile in the frame
	public Tile getTile(int x) {
		return frameTiles[x];
	}

	public void resetFrame() {
		for(int y = 0; y < frameTiles.length; y++) {
			frameTiles[y] = null;
		}
	}

	public Tile[] getFrame() {
		return frameTiles;
	}

	//resets both frame and pool
	public void resetFramePool() {
		resetFrame();
		pool.resetPool();
		refill();
	}

	//refill missing tiles in the frame by drawing from the pool
	public void refill() {

		for(int y = 0; y < frameTiles.length; y++) {
			if(frameTiles[y] == null) {
				frameTiles[y] = pool.draw();
			}
		}
	}

	public String getTileLetter(int x){
		Tile tempTile = getTile(x);
		if(tempTile == null){
			return "";
		}
		else{
			return tempTile.getTileLetter();
		}
	}
	public Integer getTileValue(String s){
		return pool.pointValue(s);
	}

	//Remove tile from the array/frame and return the removed tile 
	public Tile remove(int x) {
		Tile tempTile = null;
		if( (x > -1 && x < 7 ) && frameTiles[x] != null) {
			tempTile = frameTiles[x];
			frameTiles[x] = null;
			refill();
		}
		return tempTile;
	}
	//if array/frame contains to tiles, return true
	public boolean isEmpty() {
		boolean empty = true;
		for(int i = 0; i < frameTiles.length; i++) {
			if(frameTiles[i] != null)
				empty = false;
		}
		return empty;
	}
	//return all the letters in a string
	//it prints the number of letters in the pool for testing purposes
	public String toString() {
		boolean emptyPool = false;
		String toReturn = "";

		for(int i = 0; i < frameTiles.length; i++) {
			if(frameTiles[i] != null) {
				frameTiles[i].toString();
				toReturn += "[" + frameTiles[i] + "]";
			}
			else {
				emptyPool = true;
				toReturn += "[]";
			}
		}
		if(emptyPool) {
			toReturn += "\n[Bag is empty]";
		}
		else{
			toReturn += "\n<[" + pool.poolSize()+ "]" + " Letters Left>";
		}
		return toReturn;
	}
}

