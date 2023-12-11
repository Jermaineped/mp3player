package presentation.components.trackInformation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TrackInfoUiTop extends BorderPane{
	
	private Label albumLabel;
	private Label playlistName; 
	
	
	public TrackInfoUiTop() {
		
	}
	
	public Node initTrackInformationTop() {
		
		VBox trackInfosTop = new VBox();
		playlistName = new Label("Playlist");
		albumLabel = new Label("Album");
		playlistName.getStyleClass().add("title");
		trackInfosTop.getChildren().addAll(albumLabel, playlistName);
		trackInfosTop.setAlignment(Pos.CENTER);
		trackInfosTop.setPadding(new Insets(10, 5, 10, 5));
		trackInfosTop.setSpacing(15);
		
		return trackInfosTop;
	}
}
