package dei.uc.pt.ar.paj.EJB;

import java.util.List;

import javax.ejb.Local;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;

@Local
public interface MusicEJBLocal {

	public void populateMusic();
	
	public List<MusicEntity> getMusicas();
	public List<MusicEntity> findOrdered(MusicEntity.Ordering order, UserEntity owner);
	public List<MusicEntity> search(String searchTerm);
	public List<MusicEntity> searchByTrack(String searchTerm);
	public List<MusicEntity> searchByArtist(String searchTerm);
}