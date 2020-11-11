package discordJockey;
/**
 * Constructor for Song
 * 
 * @param t Title of song
 * @param u URL of song
 * @param r Runtime in seconds
 * @param m Does the song have sound effects for the music video
 * @param e Is the song explicit
 * @param i Does the song have no lyrics?
 */
public class Song {
	private String title = ""; //Title of song
	private String url = ""; //URL where song can be accessed
	private int time = 0; //Length in seconds of song
	// private String genre = ""; //Genre of song 
	private boolean mvsfx = false; //store if song has sound effects for the music video
	private boolean explicit = false; //store if song is explicit
	private boolean instrumental = false; //store if song has no lyrics
	
	/**
	 * Constructor for Song
	 * @param t Title of song
	 * @param u URL of song
	 * @param r Runtime in seconds
	 * @param m Does the song have sound effects for the music video
	 * @param e Is the song explicit
	 * @param i Does the song have no lyrics?
	 */
	public Song(String t, String u, int r, boolean m, boolean e, boolean i) {
		title = t;
		url = u;
		time = r;
		//genre = g;
		mvsfx = m;
		explicit = e;
		instrumental = i;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getURL() {
		return url;
	}
	
	public int getTime() {
		return time;
	}
	
	/*
	 * private String getGenre(){
	 * 	return genre;
	 * }
	 */
	
	public boolean getmvsfx() {
		return mvsfx;
	}
	
	public boolean getExplicit() {
		return explicit;
	}
	
	public boolean getInstrumental() {
		return instrumental;
	}
	
	public String toString() {
		String s = (title + "," + url + "," + time + "," + Boolean.toString(mvsfx) + "," + Boolean.toString(explicit) + "," + Boolean.toString(instrumental) + "|");
		return s;
	}

}
