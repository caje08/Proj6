package pt.dei.uc.RESTentities;

import java.util.List;

public class MusicREST {
	
	private long musicid;
	private String nomemusica;
	private String interprete;
	private String album;
	private String anolancamento;
	private double length;
	private UserREST utilizador;
	private String path;
	private List<PlaylistREST> playlists;
	private String datamusica;
	private String tipomusica;

	public MusicREST() {
		
	}
	
	public void getInfo(){
		System.out.println();
		System.out.println("Name: " + nomemusica);
		System.out.println("Artist: " + interprete);
		System.out.println("Album: " + album);
		System.out.println("Year: " + anolancamento);
		System.out.println("Type: " + tipomusica);
		
	}

	public long getMusicid() {
		return musicid;
	}

	public void setMusicid(long musicid) {
		this.musicid = musicid;
	}

	public String getNomemusica() {
		return nomemusica;
	}

	public void setNomemusica(String nomemusica) {
		this.nomemusica = nomemusica;
	}

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAnolancamento() {
		return anolancamento;
	}

	public void setAnolancamento(String anolancamento) {
		this.anolancamento = anolancamento;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public UserREST getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(UserREST utilizador) {
		this.utilizador = utilizador;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<PlaylistREST> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistREST> playlists) {
		this.playlists = playlists;
	}

	public String getDatamusica() {
		return datamusica;
	}

	public void setDatamusica(String datamusica) {
		this.datamusica = datamusica;
	}

	public String getTipomusica() {
		return tipomusica;
	}

	public void setTipomusica(String tipomusica) {
		this.tipomusica = tipomusica;
	}
	
	

}
