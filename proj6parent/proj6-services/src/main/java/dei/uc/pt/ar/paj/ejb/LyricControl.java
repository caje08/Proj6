package dei.uc.pt.ar.paj.ejb;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.LyricEntityId;
import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Facade.LyricFacade;
import dei.uc.pt.ar.paj.Facade.MusicFacade;

/**
 *
 * @author
 */
@Named(value = "lyricControl")
@ViewScoped
public class LyricControl implements Serializable {

	private static final long serialVersionUID = -6775828227183156525L;
	@Inject
	private LyricFacade lyricFacade;
	@Inject
	private MusicFacade musicFacade;
	
	private PlaylistEntity playlistentity;
	// @Inject
	// private GeneralController generalController;

	@Inject
	private WebServiceControl webServiceControl;

	private LyricEntity lyric, selectLyric;
	private String originalLyric;
	private MusicEntity music;
	private Integer musicId;
	private boolean update;

	/**
	 * Creates a new instance of LyricControl
	 */
	public LyricControl() {
	}

	@PostConstruct
	public void init() {
		this.lyric = new LyricEntity();
	}

	public LyricFacade getLyricFacade() {
		return lyricFacade;
	}

	public void setLyricFacade(LyricFacade lyricFacade) {
		this.lyricFacade = lyricFacade;
	}

	public LyricEntity getLyric() {
		return lyric;
	}

	public void setLyric(LyricEntity lyric) {
		this.lyric = lyric;
	}

	public LyricEntity getSelectLyric() {
		return selectLyric;
	}

	public void setSelectLyric(LyricEntity selectLyric) {
		this.selectLyric = selectLyric;
	}

	// public GeneralController getGeneralController() {
	// return generalController;
	// }
	//
	// public void setGeneralController(GeneralController generalController) {
	// this.generalController = generalController;
	// }

	public WebServiceControl getWebServiceControl() {
		return webServiceControl;
	}

	public void setWebServiceControl(WebServiceControl webServiceControl) {
		this.webServiceControl = webServiceControl;
	}

	public String getOriginalLyric() {
		return originalLyric;
	}

	public void setOriginalLyric(String originalLyric) {
		this.originalLyric = originalLyric;
	}

	public MusicEntity getMusic() {
		return music;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}

	public MusicFacade getMusicFacade() {
		return musicFacade;
	}

	public void setMusicFacade(MusicFacade musicFacade) {
		this.musicFacade = musicFacade;
	}

	public Integer getMusicId() {
		return musicId;
	}

	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}

	public String getLyricsToMusic(MusicEntity m, String site) throws Exception, Throwable, SAXException, IOException {	
		switch (site) {
		case "getLyricsoap":
			this.originalLyric = webServiceControl.getSoapLyricMusic(m);
			break;
		case "getLyricrest":
			this.originalLyric = webServiceControl.getMusicRESTResult(m);
			break;
		case "getLikiwiki": {				
			this.originalLyric = webServiceControl.getLyricWikiRESTResult(m);
			}
			break;
		}
		music = m;
		update = true;
		return this.originalLyric;
	}
	
public String callGetLyricsService(MusicEntity m, String site) throws Exception, Throwable, SAXException, IOException {
		
		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
			
			switch (site) {
			case "getLyricsoap":
				this.originalLyric = webServiceControl.getSoapLyricMusic(m);
				break;
			case "getLyricrest":
				this.originalLyric = webServiceControl.getMusicRESTResult(m);
				break;
			case "getLikiwiki": {				
				this.originalLyric = webServiceControl.getLyricWikiRESTResult(m);
				}
				break;
			}
			music = m;
			update = false;
			//return this.originalLyric;
		}else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				switch (site) {
				case "getLyricsoap":
					this.originalLyric = webServiceControl.getSoapLyricMusic(m);
					break;
				case "getLyricrest":
					this.originalLyric = webServiceControl.getMusicRESTResult(m);
					break;
				case "getLikiwiki": {				
						this.originalLyric = webServiceControl.getLyricWikiRESTResult(m);
					}
					break;
				}
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
		return this.originalLyric;
	}
	
	public void callLyricsService(MusicEntity m, String site) {
		
		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
			
			switch (site) {
			case "chartLyric":
				this.originalLyric = webServiceControl.getLyricSong(m);
				break;
		/*	case "LikiWiki": {
				try {
					this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
				} catch (RemoteException ex) {
					Logger.getLogger(LyricControl.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
				break;*/
			}
			music = m;
			update = false;
		} else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				switch (site) {
				case "chartLyric":
					this.originalLyric = webServiceControl.getLyricSong(m);
					break;
//				case "LikiWiki":
//					this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
//					break;
				}
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	public void webServiceREST(MusicEntity m, String func) {
		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
			switch (func) {
			case "chartLyric":
				try {
					this.originalLyric = webServiceControl.songRESTResult(m);
				} catch (ParserConfigurationException | SAXException
						| IOException | XPathExpressionException ex) {
					Logger.getLogger(LyricControl.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				break;
			// case "LikiWiki":
			// this.originalLyric = webServiceControl.lyricRESTResult(m);
			// break;

			}
			music = m;
			update = false;
		} else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				switch (func) {
				case "chartLyric":
					this.originalLyric = webServiceControl.songRESTResult(m);
					break;
				// case "LikiWiki":
				// this.originalLyric = webServiceControl.lyricRESTResult(m);
				// break;
				}
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	public void chartLyricREST(MusicEntity m) {
		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
			try {
				this.originalLyric = webServiceControl.songRESTResult(m);
				music = m;
				update = false;
			} catch (ParserConfigurationException | SAXException | IOException
					| XPathExpressionException ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		} else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				this.originalLyric = webServiceControl.songRESTResult(m);
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

//	public void lyricWikiSOAP(MusicEntity m) {
//		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
//			try {
//				this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
//				music = m;
//				update = false;
//			} catch (RemoteException ex) {
//				Logger.getLogger(LyricControl.class.getName()).log(
//						Level.SEVERE, null, ex);
//			}
//		} else {
//			try {
//				this.selectLyric = lyricFacade.findLyric(m,
//						playlistentity.getUtilizador());
//				this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
//				music = m;
//				update = true;
//			} catch (RemoteException ex) {
//				Logger.getLogger(LyricControl.class.getName()).log(
//						Level.SEVERE, null, ex);
//			} catch (Exception ex) {
//				Logger.getLogger(LyricControl.class.getName()).log(
//						Level.SEVERE, null, ex);
//			}
//		}
//	}

	public void lyricWikiREST(MusicEntity m) {
		if (!lyricFacade.existLyric(m, playlistentity.getUtilizador())) {
			this.originalLyric = webServiceControl.lyricRESTResult(m);
			music = m;
			update = false;
		} else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				this.originalLyric = webServiceControl.lyricRESTResult(m);
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	public String prepareEdit(MusicEntity m, UserEntity user) throws Exception {
		String saida="";
		
		if (!lyricFacade.existLyric(m, user)) {
			lyricWikiREST(m);
			update = false;
		} else {
			try {
				this.selectLyric = lyricFacade.findLyric(m,
						playlistentity.getUtilizador());
				saida = selectLyric.getTextLyric();
				music = m;
				update = true;
			} catch (Exception ex) {
				Logger.getLogger(LyricControl.class.getName()).log(
						Level.SEVERE, null, ex);
				System.out.println("No original lyric is available! ");
			}
		}
		return saida;
	}

	public void save() {
		if (update) {
			LyricEntity updatedLyric = selectLyric;
			updatedLyric.setTextLyric(originalLyric);
			lyricFacade.editLyric(updatedLyric);

		} else {
			LyricEntityId lyricid = new LyricEntityId(playlistentity.getUtilizador().getUserId(),music.getMusicid());
			lyric = new LyricEntity();
//			lyric.setUtilizador(playlistentity.getUtilizador());
//			lyric.setMusic(music);
			lyric.setId(lyricid);
			lyric.setTextLyric(originalLyric);
			lyricFacade.create(lyric);
		}
		update = false;
	}
}
