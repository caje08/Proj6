package dei.uc.pt.ar.paj.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Embeddable
public class LyricEntityId implements Serializable {

//	private UserEntity utilizador;
//	private String useremail;
//	private MusicEntity music;
	private Long userID;
	private Long musicID;
	
	public LyricEntityId() {
		// TODO Auto-generated constructor stub
	}

//	public LyricEntityId(String uemail, MusicEntity m) {
//		this.useremail = uemail;
//		this.music = m;
//	}

	public LyricEntityId( Long mid,Long uid) {
		this.userID = uid;
		this.musicID = mid;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getMusicID() {
		return musicID;
	}

	public void setMusicID(Long musicID) {
		this.musicID = musicID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((musicID == null) ? 0 : musicID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LyricEntityId other = (LyricEntityId) obj;
		if (musicID == null) {
			if (other.musicID != null)
				return false;
		} else if (!musicID.equals(other.musicID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	
//	public String getUseremail() {
//		return useremail;
//	}
//
//	public void setUseremail(String useremail) {
//		this.useremail = useremail;
//	}
//
//	public MusicEntity getMusic() {
//		return music;
//	}
//	public void setMusic(MusicEntity music) {
//		this.music = music;
//	}
//
//	public UserEntity getUtilizador() {
//		return utilizador;
//	}
//
//	public void setUtilizador(UserEntity utilizador) {
//		this.utilizador = utilizador;
//	}

	
	
}
