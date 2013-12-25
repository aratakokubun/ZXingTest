package jp.app.bookList;

public class Settings {
	public static final int LAUNCH_CAMERA = 0;
	public static final int MANUAL_ADD = 1;
	public static final int DELETE = 2;
	public static final int SEARCH = 3;
	
	private int mode;
	private String name;
	private int imageId;
	
	/* ----------------------------------------------------------- */
	public Settings(int mode, String name, int imageId){
		this.mode = mode;
		this.name = name;
		this.imageId = imageId;
	}
	
	/* ----------------------------------------------------------- */
	public int getMode(){
		return mode;
	}
	
	public String getName(){
		return name;
	}
	
	public int getImageId(){
		return imageId;
	}	
}