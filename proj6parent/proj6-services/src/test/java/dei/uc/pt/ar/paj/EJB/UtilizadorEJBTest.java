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
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.EJB.PasswordEJB;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilizadorEJBTest {
	@Mock
	Query q;
	@Mock
	EntityManager em;
	@Mock
	TypedQuery<UserEntity> tq;
	@Mock
	TypedQuery<PlaylistEntity> tp;
	
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
	
	
//@Test
//
//public void testaGetUtilizadorFromId(){
//	long id=0;
//	when(em.createQuery("from Utilizador u where u.id = :id")).thenReturn(q);
//	ejb.getUtilizadorFromId(id);
//	verify(q).setParameter("id", id);
//	}
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

}
