package sample;

/*
 * Name: Vasile Gabriel Gherasim		Student Number: 18480212
 * Name: Roland Garza 					Student Number: 18200061
 * Name: Aishah Aich 					Student Number: 18320686
 */
public class Tile {
	private String tileLetter;
	private int tileValue;
	
	public Tile(String s, int v) {
		tileLetter = s;
		tileValue = v;
	}
	public String getTileLetter() {
		return tileLetter;
	}

	public int getTileValue() {
		return tileValue;
	}

	public void setTileLetter(String tileLetter) {
		this.tileLetter = tileLetter;
	}

	public void setTileValue(int tileValue) {
		this.tileValue = tileValue;
	}
	
	public String toString() {		
		return getTileLetter() + ":" + getTileValue();
	}
	
}
