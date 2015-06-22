package dei.uc.pt.ar.paj.EJB;

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

import dei.uc.pt.ar.paj.Facade.LyricFacade;
import dei.uc.pt.ar.paj.Facade.MusicFacade;
import dei.uc.pt.ar.paj.login.UserSession;
import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity;


/**
 *
 * @author 
 */
@Named(value = "lyricControl")
@ViewScoped
public class LyricControl implements Serializable {

    @Inject
    private LyricFacade lyricFacade;
    @Inject
    private MusicFacade musicFacade;
    @Inject
    private UserSession loggedUser;
//    @Inject
//    private GeneralController generalController;
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

    public UserSession getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserSession loggedUser) {
        this.loggedUser = loggedUser;
    }

//    public GeneralController getGeneralController() {
//        return generalController;
//    }
//
//    public void setGeneralController(GeneralController generalController) {
//        this.generalController = generalController;
//    }

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

    public void webServiceSOAP(MusicEntity m, String func) {
        if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            switch (func) {
                case "chartLyric":
                    this.originalLyric = webServiceControl.getLyricSong(m);
                    break;
               /* case "LikiWiki": {
                    try {
                        this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
                    } catch (RemoteException ex) {
                        Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;*/
            }
            music = m;
            update = false;
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                switch (func) {
                    case "chartLyric":
                        this.originalLyric = webServiceControl.getLyricSong(m);
                        break;
                   /* case "LikiWiki":
                        this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
                        break;*/
                }
                music = m;
                update = true;
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void webServiceREST(MusicEntity m, String func) {
        if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            switch (func) {
                case "chartLyric":
                    try {
                        this.originalLyric = webServiceControl.songRESTResult(m);
                    } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                        Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
//                case "LikiWiki":
//                    this.originalLyric = webServiceControl.lyricRESTResult(m);
//                    break;

            }
            music = m;
            update = false;
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                switch (func) {
                    case "chartLyric":
                        this.originalLyric = webServiceControl.songRESTResult(m);
                        break;
//                    case "LikiWiki":
//                        this.originalLyric = webServiceControl.lyricRESTResult(m);
//                        break;
                }
                music = m;
                update = true;
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void chartLyricREST(MusicEntity m) {
        if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            try {
                this.originalLyric = webServiceControl.songRESTResult(m);
                music = m;
                update = false;
            } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                this.originalLyric = webServiceControl.songRESTResult(m);
                music = m;
                update = true;
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void lyricWikiSOAP(MusicEntity m) {
        /*if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            try {
                this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
                music = m;
                update = false;
            } catch (RemoteException ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                this.originalLyric = webServiceControl.songLyricWikiSOAP(m);
                music = m;
                update = true;
            } catch (RemoteException ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    public void lyricWikiREST(MusicEntity m) {
       /* if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            this.originalLyric = webServiceControl.lyricRESTResult(m);
            music = m;
            update = false;
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                this.originalLyric = webServiceControl.lyricRESTResult(m);
                music = m;
                update = true;
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    public void prepareEdit(MusicEntity m) throws Exception {
        if (!lyricFacade.existLyric(m, loggedUser.getLoggedUser())) {
            lyricWikiREST(m);
            update = false;
        } else {
            try {
                this.selectLyric = lyricFacade.findLyric(m, loggedUser.getLoggedUser());
                this.originalLyric = selectLyric.getTextLyric();
                music = m;
                update = true;
            } catch (Exception ex) {
                Logger.getLogger(LyricControl.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("No original lyric is available! ");
            }
        }
    }

    public void save() {
        if (update) {
            LyricEntity updatedLyric = selectLyric;
            updatedLyric.setTextLyric(originalLyric);
            lyricFacade.editLyric(updatedLyric);

        } else {
            lyric = new LyricEntity();
            lyric.setUtilizador(loggedUser.getLoggedUser());
            lyric.setMusic(music);
            lyric.setTextLyric(originalLyric);
            lyricFacade.create(lyric);
        }
        update = false;
    }
}
