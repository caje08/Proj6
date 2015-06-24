package dei.uc.pt.ar.paj.login;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.ejb.MusicEJBLocal;
import dei.uc.pt.ar.paj.ejb.PlaylistEJBLocal;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;
import dei.uc.pt.ar.paj.web.ActiveSession;

@Named
@RequestScoped
public class Login {
	@EJB
	private UserEJBLocal userEJB;
	@EJB
	private MusicEJBLocal musicEJB;
	@EJB
	private PlaylistEJBLocal playlistEJB;
		
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

	public void populateMusic() {
		System.out.println("Vai entrar no populateMusic()");
		musicEJB.populateMusic();
	}

	public List<MusicEntity> getMusicas() {
		return musicEJB.getMusicas();
	}

	public void populatePlaylist() {
//		playlistEJB.populatePlaylist();
		playlistEJB.generatePlaylists();
	}

	public List<PlaylistEntity> getPlaylists() {
		return playlistEJB.getPlaylists();
	}
	
	public List<PlaylistEntity> findOrdered() {
//		return playlistEJB.findOrdered();
		return null;
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
