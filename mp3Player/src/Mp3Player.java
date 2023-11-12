import java.util.Scanner;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

public class Mp3Player {
	
	SimpleMinim minim;
	SimpleAudioPlayer audioPlayer;
	Playlist actPlaylist;
	private int trackId = 0;
	
	
	public Mp3Player() {
		minim = new SimpleMinim(true);
		
	}
	
	public void setPlaylist(Playlist actPlaylist) {
		this.actPlaylist = actPlaylist;
	}
	
	public int getLength() {
		return audioPlayer.length();
	}
	
	public void play(String file) {
		audioPlayer = minim.loadMP3File(file);
		audioPlayer.play();
		
	}
	
	public void play() {
		audioPlayer = minim.loadMP3File(actPlaylist.getTracks().get(0).getFilePath());
		audioPlayer.play();
		
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
				play(actPlaylist.getTracks().get(trackId).getFilePath());

			} else {
				audioPlayer.pause();
				trackId++;
				play(actPlaylist.getTracks().get(trackId).getFilePath());
			
			}
	}
	
	public void back() {
		if (trackId <= 0) {
			audioPlayer.pause();
			trackId = 0;
			play(actPlaylist.getTracks().get(trackId).getFilePath());

		} else {
			audioPlayer.pause();
			trackId--;
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
}
