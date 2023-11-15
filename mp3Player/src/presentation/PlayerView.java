package presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class PlayerView extends Application {
	
	public void init() {
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
//		FileInputStream inputstream = new FileInputStream("/Users/jpedraza/eclipse-workspace/mp3-player/img/nip.jpg"); 
//		Image image = new Image(inputstream); 
		Image image = new Image(new FileInputStream("/Users/jpedraza/eclipse-workspace/mp3-player/img/nip.jpg"));
		
		Text h1 = new Text("Player");  
		
		h1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		
		Button play = new Button("Play");
		Button pause = new Button("Pause");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(200); 
	    imageView.setFitWidth(200);
		
		VBox root = new VBox(5);
		root.getChildren().addAll(h1, imageView, play, pause);
		
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.BASELINE_LEFT);
		root.setStyle("-fx-background-color: lightgrey;");

		Scene scene = new Scene(root, 500, 500);
		
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void stop() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

// --module-path /Users/jpedraza/Downloads/javafx-sdk-21.0.1 --add-modules javafx.controls