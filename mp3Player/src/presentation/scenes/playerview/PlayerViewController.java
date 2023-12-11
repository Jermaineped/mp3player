package presentation.scenes.playerview;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import business.Mp3Player;
import business.data.Playlist;
import business.data.PlaylistManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.components.controllbuttons.ControllButtons;
import presentation.components.trackInformation.TrackInformationUI;
import presentation.scenes.playerview.PlayerView;

public class PlayerViewController {

	private BorderPane root;
	private Label albumLabel;
	private Label titleLabel; 
	private String title;
	private ImageView cover;
	private TrackInformationUI info;
	private ControllButtons controllButtons;
	private Button play;
	private Button playPause;
	private Button pause;
	private Button next;
	private Button back;
	private Button shuffle;
	private Button repeat;
	private ProgressBar slider;
	private Mp3Player player;
	private String pauseIcon = "../assets/img/pause.png";
	private String playIcon = "../assets/img/play.png";
	Timer timer;
	TimerTask task;
	boolean isClicked = false;
	
	public PlayerViewController() {
		
		player = new Mp3Player();
		PlayerView mainView = new PlayerView();
		ControllButtons cb = new ControllButtons();
		TrackInformationUI inf = new TrackInformationUI();
		mainView.setImg(player.getPlaylist().getTracks().get(player.getTrackId()).byteToImage(player.getPlaylist().getTracks().get(player.getTrackId()).getImgPath()));
		mainView.trackInformation.setTitle(player.getPlaylist().getTracks().get(player.getTrackId()).getTitle());
		mainView.trackInformation.setArtist(player.getPlaylist().getTracks().get(player.getTrackId()).getArtist());
		albumLabel = mainView.albumLabel;
		titleLabel = mainView.titleLabel;
//		mainView.setImg(player.getPlaylist().getTracks().get(0).getImgPath());
		controllButtons = mainView.controllButtons;
		play = mainView.controllButtons.getPlayBtn();
		pause = mainView.controllButtons.getPauseBtn();
		next = mainView.controllButtons.getNextBtn();
		back = mainView.controllButtons.getBackBtn();
		shuffle = mainView.controllButtons.getShuffleBtn();
		repeat = mainView.controllButtons.getRepeatBtn();
		slider = mainView.trackInformation.getDuration();
		
		play.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainView.controllButtons.setImg(pauseIcon);
				
				new Thread() {
					
					public void run () {
						player.play();
						Platform.runLater(
							() -> {
								mainView.controllButtons.setImg(playIcon);
							});
					}
				}.start();
				
				
				
				System.out.println(pauseIcon);
//				player.play();
				
//				timer = new Timer();
//				task = new TimerTask() {
//
//					@Override
//					public void run() {
//						double current = player.currentTime();
//						double end = player.length();
//						for (double i = 0; i <= end; i++) {
//							current = i / end;
//							slider.setProgress(current);
//							System.out.println("Slider: " + current);
//							 try {
//						        	Thread.sleep(1000);
//						        	} catch (InterruptedException e) {
//						        		e.printStackTrace();
//						        	}
//						}
////						double x = current/end;
////						slider.setProgress(x);
////						System.out.println("Slider: " + x);
//						// TODO Auto-generated method stub
//					}
//				}; 
//				
//				timer.scheduleAtFixedRate(task, 1000, 1000);
			}
		});
		
		pause.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				mainView.controllButtons.setImg(playIcon);
				player.pause();
				System.out.println("geht");
			}
		});
		
		next.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				player.next();
				System.out.println("Track ID: " + player.getTrackId());
				mainView.setImg(player.getPlaylist().getTracks().get(player.getTrackId()).byteToImage(player.getPlaylist().getTracks().get(player.getTrackId()).getImgPath()));
				mainView.trackInformation.setTitle(player.getPlaylist().getTracks().get(player.getTrackId()).getTitle());
				mainView.trackInformation.setArtist(player.getPlaylist().getTracks().get(player.getTrackId()).getArtist());
			}
		});
		
		back.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				player.back();
			}
		});
		
		shuffle.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				player.shuffle();
			}
			
			// Back button zu andere View auch hier erzeugen
		});
		
		cover = mainView.cover;
		root = mainView;

		
	}
	
	public void initialize() {
		
	}
	
	// Ruft die FXML Datei auf, für FXML Datei? 
//	public void initalize() {
//		play.getPlayBtn().addEventHandler(ActionEvent.ACTION, new PlayHandler());
//		System.out.println("Funzt");
//		
//	};
	
	public Pane getRoot() {
		return root;
	}
	
	public Image byteToImage(byte[] imageData) {
		ByteArrayInputStream byteArray = new ByteArrayInputStream(imageData);
		return new Image(byteArray);
	}
}