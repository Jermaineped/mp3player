package business.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import javafx.scene.shape.Path;

public class PlaylistManager {
    
	private static final String directory = "/Users/jpedraza/eclipse-workspace/mp3-player/assets/music";
	private static final String mp3DirectoryPath = "/music/";
	private static final String allTracks = "All Tracks";
      
	
    public Playlist getAllTracks() {
    	
    		Playlist playlist = new Playlist(allTracks);
    		int id = 0;
            String line = null;
            String directorypath = "/Users/jpedraza/eclipse-workspace/mp3-player/assets/music";
            File directory = new File(directorypath);

            try {
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
                            Mp3File mp3file = new Mp3File(file);
//                            long sec = mp3file.getLengthInSeconds() % 60;
//                            long min = mp3file.getLengthInSeconds() / 60;
//                            double seconds = sec / 100.0;
//                            double minutes = min;
                            long length = mp3file.getLengthInSeconds();
                            Track track = new Track(id, mp3file.getId3v2Tag().getArtist(), mp3file.getId3v2Tag().getTitle(), length, file.getAbsolutePath(), mp3file.getId3v2Tag().getAlbumImage());
                            playlist.addTrack(track);                     
                            id++;
//                            System.out.println("Sekunden: " + mp3file.getLengthInSeconds());
//                            System.out.println();
                        }
                    }
                }
            } else {
                System.err.println("Das Verzeichnis existiert nicht oder ist kein Verzeichnis.");
            }
            } catch (IOException | InvalidDataException | UnsupportedTagException e) {
                throw new RuntimeException(e);
            } 
			return playlist;    	
    }
    
    public Playlist loadPlaylist(String m3uContent, String playlistName) throws UnsupportedTagException, InvalidDataException {
    	
    	Playlist playlist = new Playlist(playlistName);	
        
    	try {
        	BufferedReader reader = new BufferedReader(new FileReader(m3uContent));
            
            int id = 1;
        	String line = null;
            String artist = null;
            String title = null;
            String directorypath = "/Users/jpedraza/eclipse-workspace/mp3-player/assets/music";
            File directory = new File(directorypath);
            File[] files = directory.listFiles();
            
            while ((line = reader.readLine()) != null) {
            	if (line.startsWith("/Users")) {
            		File fileLine = new File(line);
            		if (files != null) {
                         	 for (int i = 0; i < files.length; i++) {
                         		if(files[i].equals(fileLine)) {
                                      Mp3File mp3file = new Mp3File(files[i]);
//                                      long sec = mp3file.getLengthInSeconds() % 60;
//                                      long min = mp3file.getLengthInSeconds() / 60;
//                                      double seconds = sec / 100.0;
//                                      double minutes = min;
                                      long length = mp3file.getLengthInSeconds();
                                      Track track = new Track(id, mp3file.getId3v2Tag().getArtist(), mp3file.getId3v2Tag().getTitle(), length, files[i].getAbsolutePath(), mp3file.getId3v2Tag().getAlbumImage());
                                      playlist.addTrack(track);
                                      id++;
                             	 }          	 
                         	 }                 
                          }

                	} 
            	}
           }catch (IOException e) {
            e.printStackTrace();
        }
		return playlist;
    }
}
