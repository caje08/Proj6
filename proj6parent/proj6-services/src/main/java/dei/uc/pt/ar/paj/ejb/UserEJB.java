package dei.uc.pt.ar.paj.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB implements UserEJBLocal {

	@PersistenceContext(name = "myPU")
	private EntityManager em;

	String datanasc;
	// SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");

	// Number of logged users
	private static int userCount = 0;
	
	
	

	private static HashMap<UserEntity, Integer> loggedUsers = new HashMap<>();

	static Logger logger = LoggerFactory.getLogger(UserEJB.class);

	public UserEJB() {
		// TODO Auto-generated constructor stub
	}

	public void populate() {

		datanasc = "1970/06/13";
		em.persist(new UserEntity("Carlos", "123", "carlosantos@gmail.com",
				datanasc));

		datanasc = "1985/10/21";
		em.persist(new UserEntity("Duarte", "456", "duarte@gmail.com", datanasc));
	}

	@Override
	public List<UserEntity> getUsers() {
		
		logger.info("Sample Antes info message");
		
		System.out.println("Antes de criar a query");

		Query q = em.createQuery("from UserEntity u");
		List<UserEntity> users = q.getResultList();

		System.out.println(users);

		System.out.println("Depois de apresentar os resultados");
		
		logger.info("Sample Depois info message");
		

		return users;
	}

	public UserEntity getUserFromDBbyEmail(String email) {

		UserEntity tmpuser = null;
		Query q = em.createQuery("from UserEntity u where u.email= :" + email);
		tmpuser = (UserEntity) q.getSingleResult();
		return tmpuser;
	}

	public UserEntity findByEmail(String email) {
		TypedQuery<UserEntity> q = em.createNamedQuery("User.findByEmail",
				UserEntity.class);
		q.setParameter("email", email);
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			System.err.println("Single result not found: " + e);
			return null;
		}
	}

	public UserEntity findById(long id) {
		TypedQuery<UserEntity> q = em.createNamedQuery("User.findById",
				UserEntity.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			System.err.println("Single result not found: " + e);
			return null;
		}
	}

	public UserEntity findByName(String name) {
		TypedQuery<UserEntity> q = em.createNamedQuery("User.findByName",
				UserEntity.class);
		q.setParameter("name", name);
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			System.err.println("Single result not found: " + e);
			return null;
		}
	}

	public List<PlaylistEntity> getPlayByUser(UserEntity owner) {
		// String owner = "carlosantos3@gmail.com";

		TypedQuery<PlaylistEntity> q = em.createNamedQuery(
				"Playlist.playByOwnerID", PlaylistEntity.class);
		q.setParameter("utilizador", owner);
		try {
			return q.getResultList();
		} catch (Exception e) {
			System.err.println("Single result not found: " + e);
			return null;
		}

	}

	public static void increaseUserCount(UserEntity user) {
			if(loggedUsers.containsKey(user)){
				int moreSession = loggedUsers.get(user) + 1;
				loggedUsers.put(user, moreSession);
			}else{
				loggedUsers.put(user, 1);
			}
			
			userCount = loggedUsers.size();
		
	}

	public static void decreaseUserCount(UserEntity user) {
		if(loggedUsers.get(user)==1){
			loggedUsers.remove(user);
		}else{
			int lessSession = loggedUsers.get(user)-1;
			loggedUsers.put(user, lessSession);
		}
		
		userCount = loggedUsers.size();
	}

	public static int getUserCount() {
		return userCount;
	}

	public static HashMap<UserEntity, Integer> getLoggedUsers() {
		return loggedUsers;
	}

}
