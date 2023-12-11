package presentation.scenes.playerview;

import business.Mp3Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class PlayHandler implements EventHandler<ActionEvent> {

	private Mp3Player player;
	
	public PlayHandler(Mp3Player player) {
		this.player = player;
		
	}
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		player.play();

	}

}
