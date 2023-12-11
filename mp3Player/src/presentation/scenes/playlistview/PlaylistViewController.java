package presentation.scenes.playlistview;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.scenes.playerview.PlayerView;

public class PlaylistViewController {
	BorderPane root;
	Label albumLabel;
	Label titleLabel; 
	ImageView cover;
	Button play;
	
	public PlaylistViewController() {
		
		PlaylistView mainView = new PlaylistView();
		albumLabel = mainView.albumLabel;
		titleLabel = mainView.titleLabel;
		play = mainView.play;
		root = mainView;
		
	}
	
	public Pane getRoot() {
		return root;
	}
}
