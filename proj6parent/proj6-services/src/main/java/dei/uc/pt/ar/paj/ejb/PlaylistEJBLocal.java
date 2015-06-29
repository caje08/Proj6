package dei.uc.pt.ar.paj.ejb;

import java.util.List;

import javax.ejb.Local;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;

@Local
public interface PlaylistEJBLocal {
	
	 public void populatePlaylist();
	 public void generatePlaylists();
	 public List<PlaylistEntity> getPlaylists();
	 public List<PlaylistEntity> findOrdered(PlaylistEntity.Ordering order, UserEntity owner);
	 public PlaylistEntity findByID(long id);
}