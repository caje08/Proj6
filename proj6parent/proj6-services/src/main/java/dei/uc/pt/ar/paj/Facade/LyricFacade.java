package dei.uc.pt.ar.paj.Facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;

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

	static Logger logger = LoggerFactory.getLogger(LyricFacade.class);
	
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
	public LyricEntity existUserLyricText(MusicEntity music, UserEntity utilizador) {
		TypedQuery<LyricEntity> q;
		LyricEntity saida;
		q = em.createNamedQuery("Lyric.findLyricByMusic&User",
				LyricEntity.class);
		try {
			logger.info("LyricFacade.existUserLyricText() - Search user textLyric for userid="+utilizador.getUserId()+", musicid="+music.getMusicid());
			q.setParameter("musicid", music.getMusicid()).setParameter("userid", utilizador.getUserId());
			saida=q.getSingleResult();
			logger.info("LyricFacade.existUserLyricText() produced LyricEntity.textLyric="+saida.getTextLyric());
		} catch (Exception e) {
			System.out.println("LyricFacade.existUserLyricText() - User doesn't have costumized Lyric: "+e.getMessage());
			logger.error("LyricFacade.existUserLyricText() - User doesn't have costumized Lyric: "+e.getMessage());
			return null;
		}
		return saida;
	}

	public boolean existUserLyric(MusicEntity music, UserEntity utilizador) {
		TypedQuery<Integer> q;
		q = em.createNamedQuery("Lyric.existLyric", Integer.class);
		q.setParameter("music", music).setParameter("utilizador", utilizador);
		int i = q.getSingleResult();
		return i == 1; //only returns true if i==1
	}

	public List<LyricEntity> userLyrics(UserEntity utilizador){
		List<LyricEntity> listlyrics = new ArrayList();
		TypedQuery<LyricEntity> q;
		q = em.createNamedQuery("Lyric.findLyricByUser", LyricEntity.class);
		q.setParameter("userid", utilizador.getUserId());
		listlyrics = q.getResultList();		
		
		return listlyrics;
	}
	
	public void editLyric(LyricEntity lyric) {
//		getEntityManager().merge(lyric);
//		UserEntity utilizador = lyric.getUtilizador();
//		MusicEntity m = lyric.getMusic();
//		// vai à tabela UserEntity e actualiza a lista de lyrics
//		for (LyricEntity l : utilizador.getUserLyrics()) {
//			if (Objects.equals(l.getUtilizador().getUserId(),
//					utilizador.getUserId())) {
//				l.setTextLyric(lyric.getTextLyric());
//				getEntityManager().merge(utilizador);
//			}
//		}
//	//	 vai à tabela Music e actualiza a lista de lyrics
//		for (LyricEntity l : m.getLyrics()) {
//			if (Objects.equals(l.getMusic().getMusicid(), m.getMusicid())) {
//				l.setTextLyric(lyric.getTextLyric());
//				getEntityManager().merge(m);
//			}
//		}
	}
}
