package dei.uc.pt.ar.paj.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.UserFacade;

@SessionScoped
public class VirtualEJB implements Serializable {

	private static final long serialVersionUID = 1L;


	@EJB
	private UserEJBLocal userEJB;
	@EJB
	private UserFacade userFacade;

	static Logger logger = LoggerFactory.getLogger(VirtualEJB.class);

	public void populate() {
	}

	// User
	public UserEntity getUserByEmail(String email){
		List<UserEntity>users=this.userEJB.getUsers();

		for(UserEntity u:users){
			if(u.getEmail().equals(email)) {
				return u;
			}
		}

		return null;
	}

	public void add(UserEntity user) {
		// adiciona um novo User � BD, devolve o novo User da BD
		this.userFacade.merge(user);
	}

	public void update(UserEntity user) {
		// actualiza os dados do utilizador na BD
		this.userFacade.edit(user);
	}

	public void remove(UserEntity user) {
		this.userFacade.remove(user);
	}
	// User

}