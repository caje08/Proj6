package pt.dei.uc.RESTentities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "playlists")
public class PlaylistsREST {
	
	@XmlElement(name="playlist")
	private List<PlaylistREST> playlists;

	public PlaylistsREST() {
	}

	public List<PlaylistREST> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistREST> playlists) {
		this.playlists = playlists;
	}
	
	

}
