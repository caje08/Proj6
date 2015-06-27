package pt.dei.uc.RESTentities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "playlist")
public class PlaylistREST {
	
	@XmlElement(name="playlistid")
	private long idplaylist;
	@XmlElement(name="designacao")
	private String designacao;
	
	private List<MusicREST> songs;
	@XmlElement(name="datacriacao")
	private String datacriacao;
	@XmlElement(name="utilizador")
	private UserREST utilizador;
	
	

	public PlaylistREST() {
		
	}



	public long getIdplaylist() {
		return idplaylist;
	}



	public void setIdplaylist(long idplaylist) {
		this.idplaylist = idplaylist;
	}



	public String getDesignacao() {
		return designacao;
	}



	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}



	public List<MusicREST> getSongs() {
		return songs;
	}



	public void setSongs(List<MusicREST> songs) {
		this.songs = songs;
	}



	public String getDatacriacao() {
		return datacriacao;
	}



	public void setDatacriacao(String datacriacao) {
		this.datacriacao = datacriacao;
	}



	public UserREST getUtilizador() {
		return utilizador;
	}



	public void setUtilizador(UserREST utilizador) {
		this.utilizador = utilizador;
	}
	
	

}
