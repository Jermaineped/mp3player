package presentation.components.trackInformation;

import java.util.Timer;
import java.util.TimerTask;

import com.sun.prism.paint.Color;

import business.Mp3Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TrackInformationUI extends BorderPane {
	
	Label albumLabel;
	Label artistLabel;
	Label titleLabel; 
	Label duration;
	Label endDuration;
	Slider durationSong;
	Mp3Player player;
	
	public TrackInformationUI() {
		
	}
	
	public Node initTrackInformation() {
		
		VBox trackInfos = new VBox();
		HBox time = new HBox();
		HBox endTime = new HBox();
		HBox timer = new HBox();
		VBox box = new VBox();
		titleLabel = new Label();
		artistLabel = new Label("Artist");
		duration = new Label("00:00");
		endDuration = new Label("00:00");
		durationSong = new Slider();
		
		titleLabel.getStyleClass().add("title");
		trackInfos.getChildren().addAll(titleLabel, artistLabel, durationSong);
		endTime.getChildren().addAll(endDuration);
		trackInfos.setAlignment(Pos.CENTER);
		trackInfos.setPadding(new Insets(10, 100, 10, 100));
		trackInfos.setSpacing(15);
		timer.getChildren().addAll(endTime);
		timer.setAlignment(Pos.BASELINE_RIGHT);
		timer.setPadding(new Insets(0, 100, 0, 0));
		box.getChildren().addAll(trackInfos, timer);
		
		return box;
	}
	
	public Slider getDuration() {
		return durationSong;
	}
	
	public void setTitle(String x) {
		this.titleLabel.setText(x);
	}
	
	public void setArtist(String x) {
		this.artistLabel.setText(x);
	}
	
	public Label getTime() {
		return endDuration;
	}
	
	
	
	
//	public void setDuration(double length) {
//		this.durationSong.setProgress(length);
//	}
} 



