package dei.uc.pt.ar.paj.login;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.mockito.runners.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity.Ordering;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import dei.uc.pt.ar.paj.ejb.PasswordEJB;
import dei.uc.pt.ar.paj.web.ActiveSession;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginMBTest {
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

	@Mock
	private UserSession userSession;

	@Mock
	ActiveSession session;

	@Mock
	PasswordEJB pw;

	@InjectMocks
	LoginMB loginMB;



	@Test
	public void testaSearchUser_Sem_Sucesso(){
		String email=loginMB.getEmail();
		String password=loginMB.getPassword();
		when(em.createNamedQuery(UserEntity.FIND_BY_EMAIL_AND_PASS, UserEntity.class)).thenReturn(tq);

		when(tq.getSingleResult()).thenReturn(userentity);
		userentity=userfacade.findByEmailPass(email, password);

		Assert.assertEquals(loginMB.searchUser(), "login");
		Assert.assertEquals(loginMB.getErrorMessage(),"Email/Password combination not found! Please try again");
	}

//	@Test
//	public void testaSearchUser_Com_Sucesso(){
//		String email="carlos1@gmail.com";
//		String password="1";
//		String passw=pw.encrypt(password);
//
//		userentity=userfacade.findByEmailPass(email, passw);
//		when(em.createNamedQuery(UserEntity.FIND_BY_EMAIL_AND_PASS, UserEntity.class)).thenReturn(tq);
//
//		when(tq.getSingleResult()).thenReturn(userentity);
//		userentity=userfacade.findByEmailPass(email, passw);
//		loginMB.setEmail(email);
//		loginMB.setPassword(password);
//		Assert.assertEquals(loginMB.searchUser(), "index");
//	}

}
