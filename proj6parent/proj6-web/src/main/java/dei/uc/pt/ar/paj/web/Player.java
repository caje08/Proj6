package dei.uc.pt.ar.paj.web;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.MusicEntity;

import java.io.Serializable;
import java.util.Properties;



@Named
@SessionScoped
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1333929301315965245L;
	
	private String track;

	public Player() {
		super();
	}
	
	public void playMusic(MusicEntity music){
		if(!music.getPath().equals("path"))
			this.track=music.getPath();
		this.track = "resources/mp3/test.mp3";
		
		this.track=music.getPath();
	}

	public String getMusic() {
		return track;
	}

	public void setMusic(String music) {
		this.track = music;
	}
	

}
