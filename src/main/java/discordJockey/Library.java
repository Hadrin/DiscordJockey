package discordJockey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Library {
	Song[][][] lib;
	boolean testoutput = true; //Flag to output save value to console for debugging
	/**
	 * Constructor for creating a new library from scratch
	 * @param w width of the library int
	 * @param h height of the library int
	 * @param d depth of the library int
	 */
	public Library(int w, int h, int d) {
		lib = buildLib(w, h, d);
		System.out.println("New music library created with a height of " + h + ", a width of " + w + ", and a depth of " + d);
		
	}
	
	/**
	 * Constructor for creating a library from a csv
	 * @param location location of the saved .csv
	 */
	public Library(String location) throws IOException {
		int w, h, d = 0;
		//format of song csv should be either ...,null,... or ...,(s)title,(s)url,(i)length,(b)mvsfx,(b)explicit,(b)instrumental,...
		String rawinput = "";
		rawinput = new String(Files.readAllBytes(Paths.get(location)));
		String[] input = rawinput.split(",");
		w = Integer.parseInt(input[0]);
		h = Integer.parseInt(input[1]);
		d = Integer.parseInt(input[2]);
		Song[][][] lib = buildLib(w, h, d);
		int q = 3;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				for(int k = 0; k < d; k++) {
					if(input[q].contentEquals("null")) {
						q++;
					} else {
						lib[i][j][k]= new Song(input[q], input[q+1], Integer.parseInt(input[q+2]), Boolean.valueOf(input[q+3]), Boolean.valueOf(input[q+4]), Boolean.valueOf(input[q+5]));
						q = q+6;
					}
				}
			}
		}
		System.out.println("Music db load successful");
		if(testoutput == true) {
			for(int t = 0; t < 10; t++) {
				System.out.println(input[t]);
			}
		}
	}
	
	public void saveLib() throws IOException {
		int w = lib.length;
		int h = lib[0].length;
		int d = lib[0][0].length;
		StringBuilder output = new StringBuilder(1028);
		output.append(w);
		output.append(',');
		output.append(h);
		output.append(',');
		output.append(d);
		output.append(',');
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				for(int k = 0; k < d; k++) {
					if(lib[i][j][k] == null){
						output.append("null,");
					}else {
						output.append(lib[i][j][k].getTitle());
						output.append(',');
						output.append(lib[i][j][k].getURL());
						output.append(',');
						output.append(lib[i][j][k].getTime());
						output.append(',');
						output.append(lib[i][j][k].getmvsfx());
						output.append(',');
						output.append(lib[i][j][k].getExplicit());
						output.append(',');
						output.append(lib[i][j][k].getInstrumental());
						output.append(',');
					}
				}
			}
		}
		output.setLength((output.length()-1));
		if(testoutput == true) {
			System.out.println(output);
		}
		
		FileWriter musicdbloc = new FileWriter("C:\\Users\\Dave\\Documents\\musicdb.txt");
		PrintWriter out = new PrintWriter(musicdbloc);
		out.println(output);
		out.close();
		
	}
	
	public int addSong(int w, int h, String title, String url, int length, boolean mvsfx, boolean explicit, boolean instrumental) {
		System.out.print("one");
		int d = 1000000;
		for(int i = 0; i < (lib[w][h].length); i++) {
			if(lib[w][h][i] == null) {
				if(i < d) {
					d = i;
				}
			}
			if( d == 1000000) {
				return 2;
			}
			lib[w][h][d] = new Song(title, url, length, mvsfx, explicit, instrumental);
			return 1;
		}
		
		int result = 0;
		return result;
	}
	
	public Song getSong(int w, int h, int d) {
		return lib[w][h][d];
	}
	
	private Song[][][] buildLib(int w, int h, int d){
		Song[][][] lib = new Song[w][h][d];
		return lib;
	}
	
	
	
	
	
}
