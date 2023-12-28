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
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Mp3Player {
	
	SimpleIntegerProperty currentTime;
	SimpleDoubleProperty currentSlide;
	SimpleMinim minim;
	SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	private int trackId = 0;
	private boolean running = true;
	Thread run;
	
	
	public Mp3Player() {
		PlaylistManager playlistManager = new PlaylistManager();
        ArrayList<Playlist> playlist = new ArrayList<>();
        currentTime = new SimpleIntegerProperty();
        currentSlide = new SimpleDoubleProperty();
        
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
	
	

	public synchronized void play(String file) {
		audioPlayer = minim.loadMP3File(file);
		audioPlayer.play();
		
	}
	
	public synchronized void play() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(trackId).getFilePath());

		long minutes = TimeUnit.MILLISECONDS.toSeconds(audioPlayer.length());
		long mini = TimeUnit.MINUTES.toMinutes(audioPlayer.length());
		double current = audioPlayer.position();
		
        System.out.println("TrackX: " +trackId);
        
        Runnable myThread = () ->
        {
            Thread.currentThread().setName("myThread");
        	double curr = audioPlayer.length() / 1000;
        	curr = curr - 3;
			audioPlayer.play();
			currentTime.setValue(0);
			currentSlide.setValue(0);
			System.out.println(audioPlayer.length() / 1000 - 3);

			while (isPlaying()) {
				try {
					Thread.sleep(1000);
					currentTime.setValue(currentTime.getValue() + 1);
					currentSlide.setValue((currentTime.getValue() / curr) * 100);
				} catch (InterruptedException e) {
					 Thread.currentThread().interrupt();
					 System.out.print("Interrupt");
				}
//				if (currentSlide.doubleValue() >= 100) {
//					trackId++;
//					audioPlayer.pause();
//					audioPlayer.play();
//				} else {System.out.println("Leck mich");}
			}
        };
        
        run = new Thread(myThread);
        run.start();
	}
	
	public Boolean isPlaying() {
		return audioPlayer.isPlaying();
		
	}
	
	public synchronized void shuffle() {
		
		int randomNum; 
		int size = actPlaylist.getTracks().size();
		randomNum = (int) (Math.random()*size);
		trackId = randomNum;
		
		if (running && !isPlaying()) {
			run.interrupt();
			play();
			running = false;
		} else {
			audioPlayer.pause();
			run.interrupt();
			play();
		}
		
		System.out.print(randomNum);
		
	}
	
	public void pause() {
		audioPlayer.pause();
	}
	
	public synchronized void next() {
			if (actPlaylist.getTracks().size() - 1 <= trackId) {
				audioPlayer.pause();
				run.interrupt();
				trackId = 0;
				setTrackId(trackId);
				play();

			} else {
				audioPlayer.pause();
				run.interrupt();
				trackId++;
				setTrackId(trackId);
				play();

		}
	}
	
	public synchronized void back() {
		if (trackId <= 0) {
			audioPlayer.pause();
			run.interrupt();
			trackId = 0;
			setTrackId(trackId);
			play();

		} else {
			audioPlayer.pause();
			run.interrupt();
			trackId--;
			setTrackId(trackId);
			play();
		
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
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(trackId).getFilePath());
//		long minutes = TimeUnit.MILLISECONDS.toSeconds(audioPlayer.length());
//		long seconds = (int)((audioPlayer.length() / 1000) % 60);
//		System.out.println("Länge in sekunden: " + seconds);
//		System.out.println("Länge: " + audioPlayer.length());
		return audioPlayer.length();
	}
	
	public int position() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(trackId).getFilePath());
		int actMinutes = 0;
		actMinutes++;	
		return actMinutes;
	}
	
	public int getTrackId() {
		return trackId;
	}
	
	public void setTrackId(int x) {
		this.trackId = x;
	}

	public SimpleIntegerProperty currentTimeProperty() {
		return currentTime;
	}
	
	public SimpleDoubleProperty currentSlideProperty() {
		return currentSlide;
	}

}

