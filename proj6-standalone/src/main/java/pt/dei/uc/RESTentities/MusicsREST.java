package pt.dei.uc.RESTentities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "musics")
public class MusicsREST {
	
	@XmlElement(name="music")
	List<MusicREST> musics = new ArrayList<>();

	public MusicsREST() {
		
	}	

	public List<MusicREST> getMusics() {
		return musics;
	}

	public void setMusics(List<MusicREST> musics) {
		this.musics = musics;
	}
	

}
