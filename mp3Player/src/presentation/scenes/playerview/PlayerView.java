package presentation.scenes.playerview;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import business.Mp3Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.components.controllbuttons.ControllButtons;
import presentation.components.trackInformation.TrackInfoUiTop;
import presentation.components.trackInformation.TrackInformationUI;

public class PlayerView extends BorderPane {
	
//	private Mp3Player player;
//	private HBox root;
	Label albumLabel;
	Label artistLabel;
	Label titleLabel; 
	ImageView cover;
	String path;
	ControllButtons controllButtons;
	TrackInformationUI trackInformation;
	TrackInfoUiTop trackInfoTop;
	

	
	public PlayerView() {
		trackInformation = new TrackInformationUI();
		controllButtons = new ControllButtons();
		trackInfoTop = new TrackInfoUiTop();
		VBox header = new VBox();
		header.getChildren().add(trackInfoTop.initTrackInformationTop());
//		header.getStyleClass().add("bg-playerview");
		
		this.setTop(header);
		
		cover = new ImageView();
		
		try {
			HBox imageBox = new HBox();
			cover.setImage(new Image(new FileInputStream("../assets/img/nip.jpg")));
			cover.setX(50); 
			cover.setY(50); 
		      
		    //setting the fit height and width of the image view 
			cover.setFitHeight(500); 
			cover.setFitWidth(500); 
		      
		    //Setting the preserve ratio of the image view 
			cover.setPreserveRatio(true);
			imageBox.setAlignment(Pos.CENTER);
			imageBox.getChildren().add(cover);
			this.setCenter(imageBox);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		VBox bottom = new VBox();
		bottom.setPadding(new Insets(10, 0, 50, 0));
		bottom.setSpacing(15);
		bottom.getChildren().addAll(trackInformation.initTrackInformation(), controllButtons.initControllBtns());
		
		this.setBottom(bottom);
	}
	
	public void setImg(Image img) {
        this.cover.setImage(img);
	}
}

// --module-path /Users/jpedraza/Downloads/javafx-sdk-21.0.1 --add-modules javafx.controls