public class Track {
	
	private int id;
    private String artist;
    private String title;
    private double length;
    private String filePath;

    
    public Track(int id, String artist, String title, double length, String filePath) {
    	this.id = id;
    	this.title = artist;
        this.artist = title;
        this.length = length;
        this.filePath = filePath;
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
    
    public void setFilePath(String file) {
        this.filePath = file;
    }
}