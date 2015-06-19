package dei.uc.pt.ar.paj.EJB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class MusicEJB implements MusicEJBLocal {

	@PersistenceContext(name = "myPU")
	private EntityManager em;

	String dataddmusic;

	// SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");

	static Logger logger = LoggerFactory.getLogger(MusicEJB.class);

	public MusicEJB() {
	}

	public void populateMusic() {
		UserEntity usertmp1;
		UserEntity usertmp2;
		dataddmusic = "2015/01/10";
		//		usertmp1 = new UserEntity("Carlos", "123", "carlos2antos@gmail.com",
		//				"1970/06/13");
		//		usertmp2 = new UserEntity("Duarte", "456", "duarte2@gmail.com",
		//				"1985/10/21");
		//		em.persist(new MusicEntity("nomemusic1", "interpret1", "album1",
		//				"2013", usertmp1, "c:\\path1", "2015/02/20", "tipo1"));
		//		em.persist(new MusicEntity("nomemusic2", "interpret2", "album2",
		//				"2014", usertmp2, "c:\\path2", "2014/01/10", "tipo2"));
		//		em.persist(new MusicEntity("nomemusic3", "interpret3", "album3",
		//				"2013", usertmp1, "c:\\path3", "2015/02/10", "tipo3"));
		//		em.persist(new MusicEntity("nomemusic4", "interpret4", "album4",
		//				"2012", usertmp2, "c:\\path4", "2015/03/10", "tipo4"));
		//		em.persist(new MusicEntity("nomemusic5", "interpret5", "album5",
		//				"2011", usertmp2, "c:\\path5", "2015/04/10", "tipo5"));
		//		em.persist(new MusicEntity("nomemusic6", "interpret6", "album6",
		//				"2014", usertmp2, "c:\\path6", "2015/05/10", "tipo6"));
		//		em.persist(new MusicEntity("nomemusic7", "interpret7", "album7",
		//				"2013", usertmp1, "c:\\path1", "2015/02/20", "tipo1"));
		//		em.persist(new MusicEntity("nomemusic8", "interpret8", "album8",
		//				"2014", usertmp2, "c:\\path2", "2014/01/10", "tipo2"));
		//		em.persist(new MusicEntity("nomemusic9", "interpret9", "album9",
		//				"2013", usertmp1, "c:\\path3", "2015/02/10", "tipo3"));
		//		em.persist(new MusicEntity("nomemusic10", "interpret10", "album10",
		//				"2012", usertmp1, "c:\\path4", "2015/03/10", "tipo4"));
		//		em.persist(new MusicEntity("nomemusic11", "interpret11", "album11",
		//				"2011", usertmp2, "c:\\path5", "2015/04/10", "tipo5"));
		//		em.persist(new MusicEntity("nomemusic12", "interpret12", "album12",
		//				"2014", usertmp2, "c:\\path6", "2015/05/10", "tipo6"));
	}

	@Override
	public List<MusicEntity> getMusicas() {
		// List<String> usernames = new LinkedList<>();
		logger.trace("Trace Antes getMusicas()");
		logger.debug("Sample Antes debug message");
		logger.info("Sample Antes info message");
		logger.warn("Sample Antes warn message");
		logger.error("Sample Antes error message");
		System.out.println("Antes de criar a query");
		System.out.println("Entrou no getMusicas() no MusicEJB e está antes da Query");
		Query q = em.createQuery("from MusicEntity m");
		List<MusicEntity> musicas = q.getResultList();

		System.out.println("Depois de apresentar os resultados");
		logger.trace("Trace Depois getUsers()");
		logger.debug("Sample Depois debug message");
		logger.info("Sample Depois info message");
		logger.warn("Sample Depois warn message");
		logger.error("Sample Depois error message");

		// for (User u : users) {
		// usernames.add(u.toString());
		// }

		return musicas;
	}

	//	public static enum Ordering {
	//		FIND_ALL, FIND_BY_INTERPRETE, FIND_BY_NOMEMUSICA, FIND_BY_ALBUM, FIND_BY_ANO, FIND_BY_OWNER,
	//
	//		//Pesquisas de ordenação
	//		FIND_ALL_ORDER_BY_NOME_ASC, FIND_ALL_ORDER_BY_NOME_DESC, FIND_ALL_ORDER_BY_INTERPETRE_ASC, FIND_ALL_ORDER_BY_TIME_ASC, FIND_ALL_ORDER_BY_TIME_DESC,
	//		FIND_ALL_ORDER_BY_ALBUM_ASC, FIND_ALL_ORDER_BY_ALBUM_DESC, FIND_ALL_ORDER_BY_YEAR_ASC, FIND_ALL_ORDER_BY_YEAR_DESC, FIND_ALL_ORDER_BY_OWNER_ASC, FIND_ALL_ORDER_BY__OWNER_DESC,
	//		
	//		//Pesquisas de ordenação por OWNER
	//		FIND_BY_OWNER_ORDER_BY_NOME_ASC, FIND_BY_OWNER_ORDER_BY_NOME_DESC, FIND_BY_OWNER_ORDER_BY_INTERPETRE_ASC, FIND_BY_OWNER_ORDER_BY_TIME_ASC, FIND_BY_OWNER_ORDER_BY_TIME_DESC,
	//		FIND_BY_OWNER_ORDER_BY_ALBUM_ASC, FIND_BY_OWNER_ORDER_BY_ALBUM_DESC, FIND_BY_OWNER_ORDER_BY_YEAR_ASC, FIND_BY_OWNER_ORDER_BY_YEAR_DESC, FIND_BY_OWNER_ORDER_BY_OWNER_ASC, FIND_BY_OWNER_ORDER_BY__OWNER_DESC
	//		
	//	};

	public List<MusicEntity> findOrdered(MusicEntity.Ordering order, UserEntity owner) {        
		logger.info("Entrou no metodo findOrdered()...");
		TypedQuery<MusicEntity> q;
		
		switch (order) {
		case FIND_ALL:
			q = em.createNamedQuery(MusicEntity.FIND_ALL, MusicEntity.class);
			break;
		case FIND_BY_OWNER:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_ALBUM:
			q = em.createNamedQuery(MusicEntity.FIND_BY_ALBUM, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;


			//Pesquisas de ordenação	
		case FIND_ALL_ORDER_BY_NOME_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_NOME_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_NOME_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_NOME_DESC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_INTERPRETE_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_INTERPRETE_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_INTERPRETE_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_INTERPRETE_DESC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_TIME_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_TIME_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_TIME_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_TIME_DESC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_ALBUM_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_ALBUM_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_ALBUM_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_ALBUM_DESC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_YEAR_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_YEAR_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_YEAR_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_YEAR_DESC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_OWNER_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_OWNER_ASC, MusicEntity.class);
			break;
		case FIND_ALL_ORDER_BY_OWNER_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_OWNER_DESC, MusicEntity.class);
			break;


			//Pesquisas de ordenação por OWNER
		case FIND_BY_OWNER_ORDER_BY_NOME_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_NOME_ASC, MusicEntity.class);
			q.setParameter("ownerId", owner);				
			break;
		case FIND_BY_OWNER_ORDER_BY_NOME_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_NOME_DESC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_TIME_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_TIME_ASC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_TIME_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_TIME_DESC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_ALBUM_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_ALBUM_ASC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_ALBUM_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_ALBUM_DESC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_YEAR_ASC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_YEAR_ASC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;
		case FIND_BY_OWNER_ORDER_BY_YEAR_DESC:
			q = em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_YEAR_DESC, MusicEntity.class);
			q.setParameter("ownerId", owner);
			break;






		default:
			return null;
		}
		
		try {
			List<MusicEntity> list = q.getResultList();
			return list;
		} catch (Exception e) {
			System.err.println("Ordering error " + e);
			return null;
		}
	}

	public void createMusic(MusicEntity song){
		em.persist(song);
	}

	public List<MusicEntity> search(String searchTerm){
		TypedQuery<MusicEntity> q;
		q = em.createNamedQuery(MusicEntity.FIND_BY_SEARCH,MusicEntity.class);
		q.setParameter("searchTerm","%"+searchTerm+"%");
		return q.getResultList();
	}

	@Override
	public List<MusicEntity> searchByTrack(String searchTerm) {
		TypedQuery<MusicEntity> q;
		q = em.createNamedQuery(MusicEntity.FIND_BY_NOMEMUSICA,MusicEntity.class);
		q.setParameter("searchTerm","%"+searchTerm+"%");
		return q.getResultList();
	}

	@Override
	public List<MusicEntity> searchByArtist(String searchTerm) {
		TypedQuery<MusicEntity> q;
		q = em.createNamedQuery(MusicEntity.FIND_BY_INTERPRETE,MusicEntity.class);
		q.setParameter("searchTerm","%"+searchTerm+"%");
		return q.getResultList();
	}
}
