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
import dei.uc.pt.ar.paj.ejb.PlaylistEJB;
import dei.uc.pt.ar.paj.ejb.UserEJB;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistEJBTest {
	
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
	@InjectMocks
	PlaylistEJB playlistejb;
	


@Test
public void testagetPlaylists(){

	List<PlaylistEntity> pl = null;
	when(em.createQuery("from PlaylistEntity p")).thenReturn(q);
	
    when(q.getResultList()).thenReturn(pl);

	pl=playlistejb.getPlaylists();
	verify(q).getResultList();
}

@Test
public void testafindOrdered_NAME_ASCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.NAME_ASCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.NAME_ASCEND, userentity);	
	verify(tp).getResultList();
}

@Test
public void testafindOrdered_NAME_DESCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.NAME_DESCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.NAME_DESCEND, userentity);	
	verify(tp).getResultList();
}

@Test
public void testafindOrdered_SIZE_ASCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.SIZE_ASCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.SIZE_ASCEND, userentity);	
	verify(tp).getResultList();
}

@Test
public void testafindOrdered_SIZE_DESCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.SIZE_DESCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.SIZE_DESCEND, userentity);	
	verify(tp).getResultList();
}

@Test
public void testafindOrdered_DATE_ASCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.DATE_ASCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.DATE_ASCEND, userentity);	
	verify(tp).getResultList();
}

@Test
public void testafindOrdered_DATE_DESCEND(){

	List<PlaylistEntity> pl = null;
	PlaylistEntity.Ordering order = null;
	
	when(em.createNamedQuery(PlaylistEntity.DATE_DESCEND, PlaylistEntity.class)).thenReturn(tp);
	
    when(tp.getResultList()).thenReturn(pl);
//    verify(q).getSingleResult();
	pl=playlistejb.findOrdered(PlaylistEntity.Ordering.DATE_DESCEND, userentity);	
	verify(tp).getResultList();
}
}
