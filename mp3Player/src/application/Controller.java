package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import business.Mp3Player;
import business.data.Playlist;
import business.data.PlaylistManager;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.playerview.PlayerView;


public class Controller {

	public static void main(String [] args) throws UnsupportedTagException, InvalidDataException {
		
		start();
	}
	
	public static void start() throws UnsupportedTagException, InvalidDataException {
		
		System.out.println("Welcome to my MP3-Player!");
		System.out.println("");
		System.out.println("Please use as command: play, pause, stop, volume <number>");
		System.out.println("");
		System.out.println("Folgende Playlisten stehen zur Verfügung: ");
		
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		PlaylistManager playlistManager = new PlaylistManager();
        ArrayList<Playlist> playlist = new ArrayList<>();
        Mp3Player player = new Mp3Player();
        
        playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/music/playlist.m3u", "Playlist 1"); 
		playlist.add(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/music/playlist.m3u", "Playlist 1"));
//		playlist.add(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/music/playlist-2.m3u", "Playlist 2"));
		playlist.add(playlistManager.getAllTracks());
//		
		// Standard Playlist die geladen wird
		player.setPlaylist(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/music/playlist.m3u", "Standard Playlist"));
		player.length();
		
		for (int i = 0; i < playlist.size(); i++) {
			System.out.println(playlist.get(i).getName());
		}
		System.out.println("");
		
		
		
		String input;
		 
		 while (isRunning) {
			 System.out.println("Geben Sie Ihren Befehl ein:");
			 input = scanner.nextLine();
			 
			 String [] split = input.split(" ");
//			 int id = Integer.parseInt(split[1]);
			 
			 switch(split[0]) {
			 case "play":			 
				 player.play();
				 break;
			 case "pause": 
				 player.pause();
				 break;
			 case "next": 
				 player.next();
				 break;
			 case "back": 
				 player.back();
				 break;
			 case "volume": 
				 float volume = Float.parseFloat(split[1]);
				 System.out.println(volume);
				 player.setVolume(volume); // zwischen 0 und -30
				 break;
			 case "quit": 
				 player.stop();
				 break;
				 
			 }
		 }	
	}
}
