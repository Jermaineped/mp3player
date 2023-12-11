package business;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import business.data.Playlist;
import business.data.PlaylistManager;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class Mp3Player {
	
	SimpleMinim minim;
	SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	private int trackId = 0;
	
	
	public Mp3Player() {
		PlaylistManager playlistManager = new PlaylistManager();
        ArrayList<Playlist> playlist = new ArrayList<>();
        
        try {
			playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/assets/music/playlist.m3u", "Playlist 1");
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			playlist.add(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/assets/music/playlist.m3u", "Playlist 1"));
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		playlist.add(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/music/playlist-2.m3u", "Playlist 2"));
		playlist.add(playlistManager.getAllTracks());
//		
		// Standard Playlist die geladen wird
		try {
			setPlaylist(playlistManager.loadPlaylist("/Users/jpedraza/eclipse-workspace/mp3-player/assets/music/playlist.m3u", "Standard Playlist"));
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		minim = new SimpleMinim(true);
		
	}
	
	public void setPlaylist(Playlist actPlaylist) {
		this.actPlaylist = actPlaylist;
	}

	public Playlist getPlaylist() {
		return actPlaylist;
	}
	
	

	
	public void play(String file) {
		audioPlayer = minim.loadMP3File(file);
		audioPlayer.play();
		
	}
	
	public void play() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(trackId).getFilePath());
		audioPlayer.play();
		System.out.println(audioPlayer.isPlaying());
		audioPlayer.position();
		
		
		System.out.println(audioPlayer.position());
		System.out.println(audioPlayer.length());
		long minutes = TimeUnit.MILLISECONDS.toSeconds(audioPlayer.length());
		double current = audioPlayer.position();
		
        System.out.println(actPlaylist.getTracks().get(trackId).getArtist());
		
		// Pause methode funktioniert nicht hängt sich auf. 
        
       Thread thread = new Thread();
       
       thread.start();
		for (double i = 0; i <= minutes; i++) {
			if (isPlaying()) {
				current = i / minutes;
			} else {
				break;
			}
			System.out.println("Länge " + current);
			 try {
		        	Thread.sleep(1000);
		        	} catch (InterruptedException e) {
		        		e.printStackTrace();
		        	}
		}
		
		

	}
	
	public Boolean isPlaying() {
		return audioPlayer.isPlaying();
		
	}
	
	public void shuffle() {
		
		int randomNum; 
		int size = actPlaylist.getTracks().size();
		
		randomNum = (int) (Math.random()*size) ;
		
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(randomNum).getFilePath());
		audioPlayer.play();
		
		System.out.print(randomNum);
		
	}
	
//	public void play() {
//		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(0).getFilePath());
//		audioPlayer.play();
//	} 
	
	public void pause() {
		audioPlayer.pause();
	}
	
	public void next() {
			if (actPlaylist.getTracks().size() - 1 <= trackId) {
				audioPlayer.pause();
				trackId = 0;
				setTrackId(trackId);
				play(actPlaylist.getTracks().get(trackId).getFilePath());

			} else {
				audioPlayer.pause();
				trackId++;
				setTrackId(trackId);
				System.out.println("Track: " + trackId);
				play(actPlaylist.getTracks().get(trackId).getFilePath());
			
			}
	}
	
	public void back() {
		if (trackId <= 0) {
			audioPlayer.pause();
			trackId = 0;
			setTrackId(trackId);
			play(actPlaylist.getTracks().get(trackId).getFilePath());

		} else {
			audioPlayer.pause();
			trackId--;
			setTrackId(trackId);
			play(actPlaylist.getTracks().get(trackId).getFilePath());
		
		}
	}
	
	public void stop() {
		minim.stop();
		
	}
	
	public void setVolume(float vol) {
		audioPlayer.setGain(vol);
		System.out.println("Lautstärke: " + vol);
	}
	
	public int length() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(0).getFilePath());
		long minutes = TimeUnit.MILLISECONDS.toSeconds(audioPlayer.length());
		long seconds = (int)((audioPlayer.length() / 1000) % 60);
		System.out.println("Länge in sekunden: " + seconds);
		System.out.println("Länge: " + audioPlayer.length());
		return (int) minutes;
	}
	
	public double position() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(0).getFilePath());
		double length = TimeUnit.MILLISECONDS.toSeconds(audioPlayer.length());
		double actMinutes = 0;
		actMinutes++;	
		return actMinutes;
	}
	
	public int getTrackId() {
		return trackId;
	}
	
	public void setTrackId(int x) {
		this.trackId = x;
	}
	
//	public int positionTrack() {
//		double current = audioPlayer.position();
//		double end = audioPlayer.length();
//		for (double i = 0; i <= end; i++) {
//			current = i / end;
////			slider.setProgress(current);
//			System.out.println("Slider: " + current);
//			 try {
//		        	Thread.sleep(1000);
//		        	} catch (InterruptedException e) {
//		        		e.printStackTrace();
//		        	}
//		}
//		return (int) current;
//	}
//	

}

