package dei.uc.pt.ar.paj.Entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Embeddable
public class LyricEntityId implements Serializable {

	private UserEntity utilizador;

	private MusicEntity music;

	public MusicEntity getMusic() {
		return music;
	}

	public LyricEntityId() {
		// TODO Auto-generated constructor stub
	}

	public LyricEntityId(UserEntity u, MusicEntity m) {
		this.utilizador = u;
		this.music = m;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}

	public UserEntity getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(UserEntity utilizador) {
		this.utilizador = utilizador;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (utilizador.getUserId() != null ? utilizador.getUserId().hashCode() : 0); //it must be CHECKED IF getUserId() needs to be replaced by LyricEntityId()
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof UserEntity)) {
			return false;
		}
		UserEntity other = (UserEntity) object;
//		return (this.userid != null || other.userid == null)
//				&& (this.userid == null || this.userid.equals(other.userid));
		return (utilizador.getUserId() != null || other.getUserId() == null)
				&& (utilizador.getUserId() == null || this.utilizador.getUserId().equals(other.getUserId()));
		
	//	return true;
	}
}
