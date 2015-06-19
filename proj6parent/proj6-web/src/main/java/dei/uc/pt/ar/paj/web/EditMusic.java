package dei.uc.pt.ar.paj.web;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;

import java.io.Serializable;

@Named
@SessionScoped
public class EditMusic implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1528717472172712711L;
	
	private String newName;
	private String newAlbum;
	private String newArtist;
	private String genre;
	private String path;
	
	private MusicEntity music;

	private Date added;
	
	private Date launched;
	
	private UserEntity user;

	public String getNewName() {
		return newName;
	}

	public MusicEntity getMusic() {
		return music;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewAlbum() {
		return newAlbum;
	}

	public void setNewAlbum(String newAlbum) {
		this.newAlbum = newAlbum;
	}

	public String getNewArtist() {
		return newArtist;
	}

	public void setNewArtist(String newArtist) {
		this.newArtist = newArtist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Date getLaunched() {
		return launched;
	}

	public void setLaunched(Date launched) {
		this.launched = launched;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public boolean editThisMusic(MusicEntity music, UserEntity user){
		if(music.getOwner().getUserId()==user.getUserId()){
			this.newName=music.getNomemusica();
			this.newArtist=music.getInterprete();
			this.newAlbum=music.getAlbum();
			this.music=music;
			return true;
		}
		return false;
	}
	
	public MusicEntity saveChanges(){
		if(!this.newName.equals(""))
			this.music.setNomemusica(this.newName);
		if(!this.newArtist.equals(""))
			this.music.setInterprete(this.newArtist);
		if(!this.newAlbum.equals(""))
			this.music.setAlbum(this.newAlbum);
		
		return this.music;
	}
	
}
