package pt.dei.uc.RESTentities;

import java.util.List;

public class PlaylistREST {
	
	
	private long idplaylist;
	private String designacao;
	private List<MusicREST> songs;
	private String datacriacao;
	private int arraysize;
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



	public int getArraysize() {
		return arraysize;
	}



	public void setArraysize(int arraysize) {
		this.arraysize = arraysize;
	}



	public UserREST getUtilizador() {
		return utilizador;
	}



	public void setUtilizador(UserREST utilizador) {
		this.utilizador = utilizador;
	}
	
	

}
