package dei.uc.pt.ar.paj.EJB;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.mockito.runners.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity.Ordering;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import dei.uc.pt.ar.paj.ejb.MusicEJB;
import dei.uc.pt.ar.paj.ejb.PasswordEJB;
import dei.uc.pt.ar.paj.ejb.UserEJB;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MusicEJBTest {
	private static final Object FIND_ALL = null;
	@Mock
	Query q;
	@Mock
	EntityManager em;
	@Mock
	TypedQuery<UserEntity> tq;
	@Mock
	TypedQuery<PlaylistEntity> tp;
	@Mock
	TypedQuery<MusicEntity> tm;
	
	@Mock
	UserEntity userentity;
	@Mock
	UserFacade userfacade;
	@Mock
	PlaylistEntity playlistentity;
	@Mock
	MusicEntity musicentity;
	
	@InjectMocks
	UserEJB ejb;
	@InjectMocks
	MusicEJB musicejb;
	
	
@Test
public void testaGetUtilizadorFromEmail(){
//	String email="";
	
	when(em.createQuery("from UserEntity u where u.email= :email")).thenReturn(q);
	when(q.getSingleResult()).thenReturn(userentity);
	userentity=ejb.getUserFromDBbyEmail("email");	
//	verify(q).setParameter("email", "email");
	verify(q).getSingleResult();
}

@Test
public void testaLoginFromEmailPass(){
//	String email="";
	
	when(em.createNamedQuery(UserEntity.FIND_BY_EMAIL_AND_PASS, UserEntity.class)).thenReturn(tq);
	
    when(tq.getSingleResult()).thenReturn(userentity);
//    verify(q).getSingleResult();
	userentity=userfacade.findByEmailPass("email", "password");	
//	verify(q).setParameter("email", "email");
//    verify(q).setParameter("password","password");
//	verify(q).setParameter("email", "email");
	
}
@Test
public void testafindByEmail(){
//	String email="";
	
	when(em.createNamedQuery("User.findByEmail", UserEntity.class)).thenReturn(tq);
	
    when(tq.getSingleResult()).thenReturn(userentity);
//    verify(q).getSingleResult();
	userentity=ejb.findByEmail("email");		
}
@Test
public void testafindByName(){
//	String email="";
	
	when(em.createNamedQuery("User.findByName", UserEntity.class)).thenReturn(tq);
	
    when(tq.getSingleResult()).thenReturn(userentity);
//    verify(q).getSingleResult();
	userentity=ejb.findByName("name");		
}
@Test
public void testagetPlayByUser(){
//	String email="";
	List<PlaylistEntity> pl = null;
	when(em.createNamedQuery("Playlist.playByOwnerID", PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=ejb.getPlayByUser(userentity);
	
}
@Test
public void testafindOrderedFIND_ALL(){
//	String email="";
	List<MusicEntity> pm = null;
//	MusicEntity.Ordering order;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL, MusicEntity.class)).thenReturn(tm);
	
    when(tm.getResultList()).thenReturn(pm);
//    verify(q).getSingleResult();
	pm=musicejb.findOrdered(Ordering.FIND_ALL, userentity);	
	verify(tm).getResultList();
}
@Test
public void testafindOrderedFIND_BY_OWNER(){
//	String email="";
	List<MusicEntity> pm = null;
//	MusicEntity.Ordering order;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER, MusicEntity.class)).thenReturn(tm);
	
    when(tm.getResultList()).thenReturn(pm);
//    verify(q).getSingleResult();
	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER, userentity);
	 verify(tm).getResultList();
}
@Test
public void testafindOrderedFIND_BY_ALBUM(){
//	String email="";
	List<MusicEntity> pm = null;
//	MusicEntity.Ordering order;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_ALBUM, MusicEntity.class)).thenReturn(tm);
	
    when(tm.getResultList()).thenReturn(pm);
//    verify(q).getSingleResult();
	pm=musicejb.findOrdered(Ordering.FIND_BY_ALBUM, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrderedFIND_ALL_ORDER_BY_NOME_ASC(){
//	String email="";
	List<MusicEntity> pm = null;
//	MusicEntity.Ordering order;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_NOME_ASC, MusicEntity.class)).thenReturn(tm);
	
    when(tm.getResultList()).thenReturn(pm);
//    verify(q).getSingleResult();
	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_NOME_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrderedFIND_ALL_ORDER_BY_NOME_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_NOME_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_NOME_DESC, userentity);
	 verify(tm).getResultList();
}
@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_INTERPRETE_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_INTERPRETE_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_INTERPRETE_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_INTERPRETE_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_INTERPRETE_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_INTERPRETE_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_TIME_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_TIME_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_TIME_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_TIME_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_TIME_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_TIME_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_ALBUM_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_ALBUM_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_ALBUM_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_ALBUM_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_ALBUM_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_ALBUM_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_YEAR_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_YEAR_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_YEAR_ASC, userentity);
	 verify(tm).getResultList();
}
@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_YEAR_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_YEAR_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_YEAR_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_OWNER_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_OWNER_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_OWNER_ASC, userentity);
	 verify(tm).getResultList();
}
@Test
public void testafindOrdered_FIND_ALL_ORDER_BY_OWNER_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_ALL_ORDER_BY_OWNER_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_ALL_ORDER_BY_OWNER_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_NOME_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_NOME_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_NOME_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_NOME_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_NOME_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_NOME_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_TIME_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_TIME_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_TIME_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_TIME_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_TIME_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_TIME_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_ALBUM_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_ALBUM_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_ALBUM_ASC, userentity);
	 verify(tm).getResultList();
}
@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_ALBUM_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_ALBUM_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_ALBUM_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_YEAR_ASC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_YEAR_ASC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_YEAR_ASC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testafindOrdered_FIND_BY_OWNER_ORDER_BY_YEAR_DESC(){
	List<MusicEntity> pm = null;
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_OWNER_ORDER_BY_YEAR_DESC, MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.findOrdered(Ordering.FIND_BY_OWNER_ORDER_BY_YEAR_DESC, userentity);
	 verify(tm).getResultList();
}

@Test
public void testaSearchMusicEJB(){
	List<MusicEntity> pm = null;
	String searchterm="searchterm";
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_SEARCH,MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.search(searchterm);
	 verify(tm).getResultList();
}

@Test
public void testaSearchByTrackMusicEJB(){
	List<MusicEntity> pm = null;
	String searchterm="searchterm";
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_NOMEMUSICA,MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.searchByTrack(searchterm);
	 verify(tm).getResultList();
}


@Test
public void testaSearchByArtistMusicEJB(){
	List<MusicEntity> pm = null;
	String searchterm="searchterm";
	
	when(em.createNamedQuery(MusicEntity.FIND_BY_INTERPRETE,MusicEntity.class)).thenReturn(tm);	
    when(tm.getResultList()).thenReturn(pm);

	pm=musicejb.searchByArtist(searchterm);
	 verify(tm).getResultList();
}
}
