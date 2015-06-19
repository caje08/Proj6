package dei.uc.pt.ar.paj.web;

import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
//import dei.uc.pt.ar.paj.upload.Upload;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class NewMusic implements Serializable {

//	@Inject
//	private Upload upload;


	/**
	 * 
	 */
	private static final long serialVersionUID = -3861004602652852231L;

	private String newName;
	private String newAlbum;
	private String newArtist;
	private String genre;
	private String path;

	private MusicEntity music;

	private Date added;

	private Date launched;

	private UserEntity user;

	public void init(){
//		this.upload.init();
		this.path=this.newAlbum=this.newArtist=this.newName="";
	}

	public boolean verify() throws IOException{
		if(this.newAlbum.equals(""))
			return false;

		if(this.newArtist.equals(""))
			return false;

		if(this.newName.equals(""))
			return false;

//		if(!this.upload.validExtension()){
//			return false;
//		}

		return true;
	}

//	public String uploadFile(Long musicId){
//		//Faz o Upload neste ponto
//		return this.upload.upload(musicId);
//	}

	public String getNewName() {
		return newName;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
