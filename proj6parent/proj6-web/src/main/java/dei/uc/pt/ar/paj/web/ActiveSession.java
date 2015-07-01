package dei.uc.pt.ar.paj.web;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.LyricEntityId;
import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.PlaylistFacade;
import dei.uc.pt.ar.paj.Facade.UserFacade;
import dei.uc.pt.ar.paj.ejb.MusicEJBLocal;
import dei.uc.pt.ar.paj.ejb.PlaylistEJB;
import dei.uc.pt.ar.paj.ejb.PlaylistEJBLocal;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;
import dei.uc.pt.ar.paj.ejb.WebServiceControl;
import dei.uc.pt.ar.paj.login.Login;
import dei.uc.pt.ar.paj.login.LoginMB;
import dei.uc.pt.ar.paj.login.UserSession;
import dei.uc.pt.ar.paj.render.Render;
import dei.uc.pt.ar.paj.upload.UploadFile;
import dei.uc.pt.ar.paj.util.UtilMessage;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Named
@SessionScoped
public class ActiveSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = LoggerFactory.getLogger(ActiveSession.class);

	@Inject
	VirtualEJB ejb;

	@Inject
	Render render;

	@Inject
	EditPlayList editPlayList;

	@Inject
	UserSession usersession;

	@Inject
	EditUser editUser;

	@Inject
	EditMusic editMusic;

	@Inject
	NewMusic newMusic;

	@Inject
	private WebServiceControl webservicecontrol;

	// @EJB
	// private MusicEJBLocal musicEJB;
	@EJB
	private PlaylistEJBLocal playlistEJB;
	@EJB
	private UserFacade userFacade;
	@EJB
	private PlaylistFacade playlistFacade;

	private HttpSession session;

	private UserEntity activeUser;

	private PlaylistEntity activePlayList;

	// private ArrayList <PlaylistEntity> userPlayLists;

	private MusicEntity activeMusic;
	private LoginMB loginmb;
	private ArrayList<MusicEntity> musicSearch;

	private String newPlayListName;
	private String search;

	private String fileName;
	private String path;

	private String editUserNewUserName;
	private String editUserNewEmail;
	private String backupEmail;
	private String showmylyric;
	private boolean createnewlyricDB = true;
	private Date date;
	private SimpleDateFormat sf;

	private Part file;

	private int order;

	private List<PlaylistEntity> userPlaylists;
	private boolean getMusicsByUser;
	private boolean getMusicsBySort;
	private boolean sessionLoggedIn;
	private boolean isSearch;
	private String mensagem;

	private MusicEntity musicToUploadFile;

	public void init(UserEntity user) {
		startSession();

		this.render.setActive(true);

		this.isSearch = false;

		this.getMusicsByUser = this.getMusicsBySort = false;

		this.newPlayListName = "";

		this.render.init();
		this.render.setUploadMusic(false);

		this.activeUser = user;

		this.backupEmail = this.activeUser.getEmail();

		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 0);

		this.activeUser.setUserplaylists(userPlaylists);
		this.sessionLoggedIn = true;
		this.mensagem = "";
	}

	public String getEditUserNewUserName() {
		return this.editUser.getNewUserName();
	}

	public void setEditUserNewUserName(String editUserNewUserName) {
		this.editUser.setNewUserName(editUserNewUserName);
		this.editUserNewUserName = editUserNewUserName;
	}

	public String getEditUserNewEmail() {
		return this.editUser.getNewEmail();
	}

	public void setEditUserNewEmail(String editUserNewEmail) {
		this.editUser.setNewEmail(editUserNewEmail);
		this.editUserNewEmail = editUserNewEmail;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public List<PlaylistEntity> getUserPlaylists() {
		return this.userPlaylists;
	}

	public void setUserPlaylists(List<PlaylistEntity> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getNewPlayListName() {
		return newPlayListName;
	}

	public void setNewPlayListName(String newPlayListName) {
		this.newPlayListName = newPlayListName;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isGetMusicsByUser() {
		return getMusicsByUser;
	}

	public String getShowmylyric() {
		showmylyric = showMyLyricVersion();
		return showmylyric;
	}

	public void setShowmylyric(String showmylyric) {
		this.showmylyric = showmylyric;
	}

	public void setGetMusicsByUser(boolean getMusicsByUser) {
		this.getMusicsByUser = getMusicsByUser;
	}

	public UserEntity getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(UserEntity activeUser) {
		this.activeUser = activeUser;
	}

	public void setActivePlayList(PlaylistEntity activePlayList) {
		this.activePlayList = activePlayList;
		this.editPlayList.setPlayListToEdit(activePlayList);
		this.render.setEditPlayList(true);
	}

	public PlaylistEntity getActivePlayList() {
		return activePlayList;
	}

	public MusicEntity getActiveMusic() {
		return activeMusic;
	}

	public void setActiveMusic(MusicEntity activeMusic) {
		this.activeMusic = activeMusic;
	}

	public String showMyLyricVersion() {
		String lyrictxt = "";

		lyrictxt = ejb.getLyricfromFacade(activeMusic, activeUser);
		logger.info("Em ActiveSession.getMyLyricVersion() Lyrictxt=" + lyrictxt);
		if (lyrictxt.equals("error")) {
			lyrictxt = activeMusic.getOriginalLyric();
			createnewlyricDB = true;
		} else
			createnewlyricDB = false;
		this.showmylyric = lyrictxt;
		return lyrictxt;
	}

	// Music Browser
	public void browseMusics() {
		this.render.browseMusics();
		this.getMusicsByUser = false;
		this.getMusicsBySort = false;
		this.isSearch = false;
		getMusicLibrary();
	}

	public void browseMusicsFromUser() {
		this.render.browseMusics();
		this.getMusicsByUser = true;
		this.getMusicsBySort = false;
		this.isSearch = false;
		getMusicLibrary();
	}

	public ArrayList<MusicEntity> getMusicLibrary() {
		if (isSearch)
			return this.musicSearch;

		// BD
		if (this.getMusicsBySort)
			return this.ejb.getMusics(this.activeUser, this.order,
					this.getMusicsByUser);

		// BD
		if (!this.getMusicsByUser)
			return this.ejb.getMusics();

		// BD
		return this.ejb.getMusics(this.activeUser);
	}

	public void sortPlayListDisplay(int order) {
		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, order);
	}

	public void sortMusicLibrary(int order) {
		if (!this.isSearch) {
			this.getMusicsBySort = true;
			this.order = order;
		}
	}

	// Search
	public void searchMusic() {
		this.render.browseMusics();
		this.isSearch = true;
		this.getMusicsBySort = false;
		// BD
		this.musicSearch = (ArrayList<MusicEntity>) this.ejb
				.searchMusic(this.search.toLowerCase());
	}

	public void searchMusicByTrack() {
		this.render.browseMusics();
		this.isSearch = true;
		this.getMusicsBySort = false;
		// BD
		this.musicSearch = (ArrayList<MusicEntity>) this.ejb
				.searchMusicByTrack(this.search.toLowerCase());
	}

	public void searchMusicByArtist() {
		this.render.browseMusics();
		this.isSearch = true;
		this.getMusicsBySort = false;
		// BD
		this.musicSearch = (ArrayList<MusicEntity>) this.ejb
				.searchMusicByArtist(this.search.toLowerCase());
	}

	// Music Browser

	// User Edits
	public void editUser() {
		this.editUser.init(this.activeUser);
		this.render.setEditUser(true);
	}

	public void editUserCancel() {

		this.mensagem = "";
		render.cancelEditUser();
	}

	public void saveUserChanges() {
		// this.render.cancelEditUser();
		//
		// this.activeUser = this.editUser.saveUserChanges();
		//
		// this.ejb.update(this.activeUser);

		this.mensagem = "";
		logger.info("Entrou em ActiveSession.saveUserChanges()");
		this.activeUser = this.editUser.saveUserChanges();
		try {
			logger.info("Vai tentar actualizar dados do user em ActiveSession.saveUserChanges()");
			this.ejb.update(this.activeUser);
		} catch (EJBTransactionRolledbackException e) {
			Throwable t = e.getCause();
			while ((t != null) && !(t instanceof ConstraintViolationException)) {
				t = t.getCause();
			}
			if (t instanceof ConstraintViolationException) {
				// Here you're sure you have a ConstraintViolationException, you
				// can handle it
				this.mensagem = "This Email already exists in DB!";
				logger.error(mensagem);
				this.activeUser = this.ejb.getUserByEmail(this.backupEmail);
				this.editUser.init(this.activeUser);
			}
		}
		if (this.mensagem.equals("")) {
			this.render.cancelEditUser();
			this.activeUser = this.editUser.saveUserChanges();
			this.ejb.update(this.activeUser);
			this.editUser.setNewEmail("");
			this.editUser.setMensagem("");
		}
	}

	public void saveUserPasswordChanges() {
		if (editUser.checkPw()) {
			this.render.cancelEditUser();
			// BD
			this.ejb.update(this.activeUser);
		}
	}

	public void deleteUser() {
		this.activePlayList = null;
		this.editPlayList.setPlayListToEdit(null);

		this.userPlaylists = getUserPlaylists();

		// Remove as Playlists do User
		for (PlaylistEntity pl : this.userPlaylists) {
			removePlayList(pl);
		}

		this.userPlaylists = new ArrayList<PlaylistEntity>();

		// Limpa a Lista de PlayLists do User
		this.activeUser.setUserplaylists(new ArrayList<PlaylistEntity>());

		this.getMusicsByUser = true;
		this.isSearch = false;
		ArrayList<MusicEntity> musics = getMusicLibrary();

		UserEntity admin = userFacade.findByEmailPass("admin@admin",
				"d033e22ae348aeb5660fc2140aec35850c4da997");

		// Desassocia as Músicas
		for (MusicEntity m : musics) {
			m.setUtilizador(admin);
			this.ejb.update(m);
		}

		// Remove o User sem PlayLists e sem Músicas
		this.ejb.remove(this.activeUser);
		logout();
	}

	// User Edits End

	// Music Edits Start
	public void editThisMusic(MusicEntity music) {
		this.render.setUploadable(false);
		if (this.editMusic.editThisMusic(music, this.activeUser)) {
			this.render.setEditMusic(true);
			this.musicToUploadFile = music;

			if (music.getPath().equals("path"))
				this.render.setUploadable(true);

		} else
			this.render.setEditMusic(false);
	}

	// Edit Lyric start
	public void editThisLyric(MusicEntity music, int i) {
		this.render.setEditPlayList(false);
		if (this.editMusic.editThisLyric(music, this.activeUser)) {
			this.activeMusic = music;
			if (i == 1)
				this.render.setEditLyric(true);
			else
				this.render.setReadLyric(true);
		} else {
			this.render.setEditLyric(false);
			this.render.setReadLyric(false);
		}
	}

	public String userPresentationMusic(MusicEntity music) {
		if (music.getOwner().getUserId() == this.activeUser.getUserId())
			return "Yours! Edit this Music";
		return music.getOwner().getName();
	}

	public void saveLyricMusicChanges() {

		this.render.setEditLyric(false);
		// BD
		logger.info("activeUser em ActiveSession.saveLyricMusicChanges() = "
				+ this.activeUser.toString());
		logger.info("activeMusic em ActiveSession.saveLyricMusicChanges() = "
				+ this.activeMusic.toString());
		this.ejb.updateLyric(this.editMusic.saveLyricChanges(activeMusic),
				this.activeUser, createnewlyricDB, showmylyric);
		this.render.setEditPlayList(true);
	}

	public void saveMusicChanges() {
		this.render.setEditMusic(false);

		// BD
		this.ejb.update(this.editMusic.saveChanges());
	}

	public void updateMusic(MusicEntity m) {

		// BD
		this.ejb.update(m);
	}

	public void cancelLyricMusicChanges() {
		this.render.setEditLyric(false);
		this.render.setEditPlayList(true);
	}

	public void cancelMusicChanges() {
		this.render.setEditMusic(false);
		this.render.setUploadable(false);
		this.render.setUploadMusic(false);
	}

	// New Music
	public void newMusicStart() {
		this.newMusic.init();
		this.render.setUploadMusic(false);
		this.render.setNewMusic(true);
	}

	public void newMusicEnd() throws Throwable {
		this.date = new Date();
		this.sf = new SimpleDateFormat("yyyy/MM/dd");
		String textlyric = "";

		if (this.newMusic.verify()) {
			MusicEntity music = new MusicEntity(this.newMusic.getNewName(),
					this.newMusic.getNewArtist(), this.newMusic.getNewAlbum(),
					"2015", this.activeUser, "path", sf.format(date),
					"Genérico");
			// BD
			logger.info("MusicID antes de persistir na BD = "
					+ music.getMusicid());
			this.ejb.add(music);
			this.search = music.getNomemusica();
			searchMusicByTrack();
			if (this.musicSearch.size() == 1) {
				music = this.musicSearch.get(0);
			} else {
				for (int i = 0; i < this.musicSearch.size(); i++) {
					if (this.musicSearch.get(i).getInterprete()
							.equals(music.getInterprete())) {
						music = this.musicSearch.get(i);
						i = this.musicSearch.size();
					} else if ((i + 1) == this.musicSearch.size()) {
						logger.error("Music not Found in DB with: Track name ="
								+ this.search + " and Artist = "
								+ music.getInterprete());
					}
				}
			}
			logger.info("MusicID depois de persistir na BD = "
					+ music.getMusicid());
			textlyric = webservicecontrol.getLyricsMusicExists(music);
			logger.info("Lyric text found is = " + textlyric);
			if (textlyric != null && textlyric != ""
					&& !textlyric.equals("Not found")) {
				logger.info("Antes de criar a entidade Lyric em ActiveSession.newMusicEnd()");
				LyricEntityId id = new LyricEntityId(music.getMusicid(),
						activeUser.getUserId());
				LyricEntity lyric = new LyricEntity();
				logger.info("Depois de criar a entidade Lyric e antes do setId em ActiveSession.newMusicEnd()");
				lyric.setId(id);
				logger.info("Depois do setId e antes do setMusic em ActiveSession.newMusicEnd()");

				logger.info("Depois do setUtilizador() em ActiveSession.newMusicEnd()");
				lyric.setTextLyric(textlyric);
				music.setLyricExist(true);
				music.setOriginalLyric(textlyric);
				music.setMylyricversion(textlyric);

				logger.info("Lyric found for object Music = ", music.toString());
				logger.info("Lyric created with text = " + textlyric);
				this.ejb.addLyric(lyric, false);
				updateMusic(music);
			}
			// render.editLyric
			// this.ejb.add(music);
			// render.editPlayList
			this.render.setEditPlayList(false);
			this.render.setEditLyric(false);
			this.render.setNewMusic(false);
		}
	}

	public void newMusicCancel() {
		this.render.setNewMusic(false);
		this.render.setUploadMusic(false);
	}

	// Envia o ficheiro para uma pasta do servidor
	public void uploadMusicStart() {
		this.render.setUploadMusic(true);
	}

	public void uploadMusic() {
		this.render.setUploadable(false);

		Properties props = System.getProperties();

		this.path = "/music/" + this.musicToUploadFile.getMusicid() + ".mp3";

		try {
			file.write(props.getProperty("user.dir") + "\\music\\"
					+ this.musicToUploadFile.getMusicid() + ".mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.musicToUploadFile.setPath(this.path);
		this.ejb.update(this.musicToUploadFile);
		this.uploadMusicCancel();
		this.file = null;
	}

	// Extrai o nome do ficheiro a fazer o upload
	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return "";
	}

	// Verifica de o ficheiro tem extensão mp3
	private boolean validExtension() {
		if (this.file != null) {
			this.fileName = getFilename(this.file);
			if (this.fileName.length() > 4) {
				String extension = path.substring(path.length() - 3);
				if (extension.equals("mp3")) {
					return true;
				} else {
					this.file = null;
				}
			}
		}
		return false;
	}

	// Termina/Cancela o upload do ficheiro da música
	public void uploadMusicCancel() {
		this.render.setUploadMusic(false);
		this.file = null;
	}

	// New Music

	// Disassociate Music
	public void disassociateMusic() {
		UserEntity admin = userFacade.findByEmailPass("admin@admin",
				"d033e22ae348aeb5660fc2140aec35850c4da997");
		MusicEntity music = this.editMusic.getMusic();
		music.setUtilizador(admin);
		this.ejb.update(music);
		this.render.setEditMusic(false);
	}

	// Music Edits End

	// PlayList Edits Start
	// New PlayList
	public void createPlayList() {
		if (!this.newPlayListName.equals("")) {
			this.date = new Date();
			this.sf = new SimpleDateFormat("yyyy/MM/dd");

			PlaylistEntity newPlayList = new PlaylistEntity();
			newPlayList.setDesignacao(this.newPlayListName);
			newPlayList.setUtilizador(this.activeUser);
			newPlayList.setDatacriacao(sf.format(date));

			this.newPlayListName = "";

			// BD
			this.ejb.add(newPlayList);
			this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 0);
		}
	}

	// Add Music To PlayList
	public void addMusicToPlayListStart(MusicEntity musicToAddToPlayList) {
		this.editPlayList.addMusicToPlayListStart(musicToAddToPlayList);
	}

	public void addMusicToPlayListEnd(PlaylistEntity playList) {
		this.activePlayList = this.editPlayList.addMusicToPlayListEnd(playList);

		// BD
		this.ejb.update(this.activePlayList);

		this.render.setEditPlayList(true);
		this.render.setAddMusicToPlayList(false);
		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 0);
	}

	// Remove music from Playlist
	public void removeMusicFromPlayList(MusicEntity music) {
		this.editPlayList.removeMusicFromPlayList(music);
		this.ejb.update(this.activePlayList);
		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 0);
	}

	// Actualiza as alterações da PlayList
	public void saveChangesToPlayList() {
		this.ejb.add(surrogatePlayList(this.editPlayList.getPlayListToEdit()));

		this.removePlayList(this.editPlayList.getPlayListToEdit());

		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 1);
	}

	// Clona PlayList
	public PlaylistEntity surrogatePlayList(PlaylistEntity playlistIn) {
		PlaylistEntity playlistOut = new PlaylistEntity();

		playlistOut.setDatacriacao(playlistIn.getDatacriacao());
		playlistOut.setDesignacao(playlistIn.getDesignacao());
		playlistOut.setSongs(playlistIn.getSongs());
		playlistOut.setUtilizador(playlistIn.getUtilizador());
		playlistOut.setArraySize(playlistOut.getSongs().size());

		return playlistOut;
	}

	// Remove PlayList
	public void removePlayList(PlaylistEntity playList) {
		this.render.init();

		List<MusicEntity> emptyList = new ArrayList<MusicEntity>();
		playList.setSongs(emptyList);
		playList.setUtilizador(null);

		// BD
		this.ejb.remove(playList);
		this.userPlaylists = this.ejb.getPlayLists(this.activeUser, 0);
	}

	// Clona a Música
	public MusicEntity surrogateMusic(MusicEntity musicIn) {
		MusicEntity musicOut = new MusicEntity();

		musicOut.setAlbum(musicIn.getAlbum());
		musicOut.setAnolancamento(musicIn.getAnolancamento());
		musicOut.setDatamusica(musicIn.getDatamusica());
		musicOut.setInterprete(musicIn.getInterprete());
		musicOut.setLength(musicIn.getLength());
		musicOut.setNomemusica(musicIn.getNomemusica());
		musicOut.setOwner(musicIn.getOwner());
		musicOut.setPath(musicIn.getPath());
		musicOut.setPlaylists(musicIn.getPlaylists());
		musicOut.setTipomusica(musicIn.getTipomusica());
		musicOut.setUtilizador(musicIn.getUtilizador());

		return musicOut;
	}

	// Remove a música
	public void removeMusic(MusicEntity music) {
		List<PlaylistEntity> emptyList = new ArrayList<PlaylistEntity>();

		music.setPlaylists(emptyList);
		music.setOwner(null);
		music.setUtilizador(null);

		this.ejb.remove(music);
	}

	// PlayList Edits End

	// Logout
	public void logout() {
		this.render.setActive(false);
		// redirect();
		logger.info("ActiveSession.logout() - antes de fazlogout()");
		fazlogout();
		// loginmb.logout();
		this.activeUser = null;
		this.activePlayList = null;
		// this.userPlayLists.clear();
		this.activeMusic = null;
		logger.info("No ActiveSession.logout() antes do endSession()");
		endSession();
		redirect();
	}

	public String fazlogout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		try {
			request.logout();

			logger.info("No ActiveSession.fazlogout() depois de 'request.logout()'");
			return "/login.xhtml";
		} catch (ServletException e) {
			UtilMessage.addErrorMessage("Logout Failed.");
		}
		logger.info("No ActiveSession.fazlogout() antes do return '/login'");
		return "/login.xhtml?faces-redirect=true";
	}

	private void redirect() {
		String redirect = "/proj6-web/login.xhtml";
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect(redirect);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Logout

	// In�cio e Fim da sess�o http
	public void startSession() {
		this.session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.session.setAttribute("sessionLoggedIn", true);
	}

	public void endSession() {
		if (this.session != null)
			this.session.invalidate();
		logger.info("Em ActiveSession.endSession() no final da endSession()");
		this.sessionLoggedIn = false;
	}
	// In�cio e Fim da sess�o http
}
