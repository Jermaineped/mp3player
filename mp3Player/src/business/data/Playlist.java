package business.data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private String file;
    private ArrayList<Track> tracks;

    public Playlist(String name, ArrayList<Track> tracks) {
        this.name = name;
        this.tracks =  tracks;
    }
    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public ArrayList<Track> getTracks() {
    	for (Track track : tracks) {
    		return tracks;        
    		}
         return null;
    }
}
