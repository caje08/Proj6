package dei.uc.pt.ar.paj.Facade;

import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity;

/**
 *
 * @author
 */
@Stateless
public class LyricFacade extends AbstractFacade<LyricEntity> {

	@PersistenceContext(unitName = "myPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public LyricFacade() {
		super(LyricEntity.class);
	}

	public LyricEntity findLyric(MusicEntity music, UserEntity utilizador)
			throws Exception {
		TypedQuery<LyricEntity> q;
		q = em.createNamedQuery("Lyric.findLyricByMusic&User",
				LyricEntity.class);
		try {
			q.setParameter("music", music).setParameter("utilizador", utilizador);
			return q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean existLyric(MusicEntity music, UserEntity utilizador) {
		TypedQuery<LyricEntity> q;
		q = em.createNamedQuery("Lyric.findLyricByMusic&User",
				LyricEntity.class);
		try {
			q.setParameter("music", music).setParameter("utilizador", utilizador);
			q.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existUserLyric(MusicEntity music, UserEntity utilizador) {
		TypedQuery<Integer> q;
		q = em.createNamedQuery("Lyric.existLyric", Integer.class);
		q.setParameter("music", music).setParameter("utilizador", utilizador);
		int i = q.getSingleResult();
		return i == 1; //only returns true if i==1
	}

	public void editLyric(LyricEntity lyric) {
		getEntityManager().merge(lyric);
		UserEntity utilizador = lyric.getUtilizador();
		MusicEntity m = lyric.getMusic();
		// vai à tabela UserEntity e actualiza a lista de lyrics
		for (LyricEntity l : utilizador.getLyrics()) {
			if (Objects.equals(l.getUtilizador().getUserId(),
					utilizador.getUserId())) {
				l.setTextLyric(lyric.getTextLyric());
				getEntityManager().merge(utilizador);
			}
		}
		// vai à tabela Music e actualiza a lista de lyrics
		for (LyricEntity l : m.getLyrics()) {
			if (Objects.equals(l.getMusic().getMusicid(), m.getMusicid())) {
				l.setTextLyric(lyric.getTextLyric());
				getEntityManager().merge(m);
			}
		}
	}
}
