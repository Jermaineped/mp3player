package application;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.scenes.playerview.PlayerView;
import presentation.scenes.playerview.PlayerViewController;
import presentation.scenes.playlistview.PlaylistView;
import presentation.scenes.playlistview.PlaylistViewController;


public class Main extends Application {
	
	Map<String, Pane> scenes;
	
	@Override     
	public void start(Stage primaryStage) {
		
		try {
			
			scenes = new HashMap<String, Pane>();
			
			PlayerViewController playerViewController = new PlayerViewController();
			scenes.put("PlayerView", playerViewController.getRoot());
			PlaylistViewController playlistViewController = new PlaylistViewController();
			scenes.put("PlaylistView", playlistViewController.getRoot());
			
			Pane root = scenes.get("PlayerView");
			
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("MP3 Player");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
	
    public static void main(String[] args) {
       launch(args);
       System.out.println("Hallo");
    }
}
