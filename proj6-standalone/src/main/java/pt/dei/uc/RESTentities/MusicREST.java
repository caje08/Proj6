package pt.dei.uc.RESTentities;

import java.util.List;



import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="music")
public class MusicREST {
	
	@XmlElement(name="musicid")
	private long musicid;
	@XmlElement(name="name")
	private String nomemusica;
	@XmlElement(name="artist")
	private String interprete;
	@XmlElement(name="album")
	private String album;
	@XmlElement(name="year")
	private String anolancamento;
	private double length;
	@XmlElement(name="user")
	private UserREST utilizador;
	private String path;
	private List<PlaylistREST> playlists;
	private String datamusica;
	private String tipomusica;

	public MusicREST() {
		
	}
	
	public void getInfo(){
		System.out.println();
		System.out.println("Song Id: " + musicid);
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
