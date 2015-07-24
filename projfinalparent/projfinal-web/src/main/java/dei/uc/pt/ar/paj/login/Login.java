package dei.uc.pt.ar.paj.login;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;

@Named
@RequestScoped
public class Login {
	@EJB
	private UserEJBLocal userEJB;

	private String username;
	private String password;

	public Login() {
		super();

	}

	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public void populate() {
		userEJB.populate();
	}

	public List<UserEntity> getUsers() {
		return userEJB.getUsers();
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
