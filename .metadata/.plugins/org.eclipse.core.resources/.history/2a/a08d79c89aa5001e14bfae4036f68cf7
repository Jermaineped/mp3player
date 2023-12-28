package business.data;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.mpatric.mp3agic.ID3v2FrameSet;

import javafx.scene.image.Image;

public class Track {
	
	private int id;
    private String artist;
    private String title;
    private double length;
    private String filePath;
    private byte[] imgPath;

    
    public Track(int id, String title, String artist,  double length, String filePath, byte[] imgPath) {
    	this.id = id;
    	this.title = artist;
        this.artist = title;
        this.length = length;
        this.filePath = filePath;
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilePath() {
        return filePath;
    }
    
    
    
    public byte[] getImgPath() {
        return imgPath;
    }
    
	public Image byteToImage(byte[] imageData) {
		ByteArrayInputStream byteArray = new ByteArrayInputStream(imageData);
		return new Image(byteArray);
	}
    
    public void setFilePath(String file) {
        this.filePath = file;
    }
}
