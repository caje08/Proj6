package dei.uc.pt.ar.paj.web;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.LyricEntity;
import dei.uc.pt.ar.paj.Entities.LyricEntityId;
import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.ejb.LyricControl;

import java.io.Serializable;

@Named
@SessionScoped
public class EditMusic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1528717472172712711L;

	private String newName;
	private String newAlbum;
	private String newArtist;
	private String genre;
	private String path;
	private String musicOriginalLyric;
	private String musicMyLyricVersion;
	private boolean lyricowner;
	private MusicEntity music;

	// @EJB
	private LyricControl lirycontrol;

	private LyricEntity lyricentity;

	private Date added;

	private Date launched;

	private UserEntity user;

	public String getNewName() {
		return newName;
	}

	public MusicEntity getMusic() {
		return music;
	}

	public void setMusic(MusicEntity music) {
		this.music = music;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewAlbum() {
		return newAlbum;
	}

	public void setNewAlbum(String newAlbum) {
		this.newAlbum = newAlbum;
	}

	public LyricEntity getLyricentity() {
		return lyricentity;
	}

	public void setLyricentity(LyricEntity lyricentity) {
		this.lyricentity = lyricentity;
	}

	public String getNewArtist() {
		return newArtist;
	}

	public void setNewArtist(String newArtist) {
		this.newArtist = newArtist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Date getLaunched() {
		return launched;
	}

	public void setLaunched(Date launched) {
		this.launched = launched;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getMusicOriginalLyric() {
		return musicOriginalLyric;
	}

	public void setMusicOriginalLyric(String musicOriginalLyric) {
		this.musicOriginalLyric = musicOriginalLyric;
	}

	public String getMusicMyLyricVersion() {
		return musicMyLyricVersion;
	}

	public void setMusicMyLyricVersion(String musicMyLyricVersion) {
		this.musicMyLyricVersion = musicMyLyricVersion;
	}

	public boolean isLyricowner() {
		return lyricowner;
	}

	public void setLyricowner(boolean lyricowner) {
		this.lyricowner = lyricowner;
	}

	public boolean editThisMusic(MusicEntity music, UserEntity user) {
		if (music.getOwner().getUserId() == user.getUserId()) {
			this.newName = music.getNomemusica();
			this.newArtist = music.getInterprete();
			this.newAlbum = music.getAlbum();
			this.musicMyLyricVersion = music.getMylyricversion();
			this.music = music;
			return true;
		}
		return false;
	}

	public boolean editThisLyric(MusicEntity music, UserEntity user) {
		if (music.getOwner().getUserId() == user.getUserId()) {
			this.lyricowner = true;
			this.newName = music.getNomemusica();
			this.newArtist = music.getInterprete();
			this.newAlbum = music.getAlbum();
			this.musicOriginalLyric = music.getOriginalLyric();
			this.musicMyLyricVersion = music.getMylyricversion();
			if ((this.musicMyLyricVersion == null || this.musicMyLyricVersion == "")
					&& this.musicOriginalLyric.length() > 0)
				this.musicMyLyricVersion = this.musicOriginalLyric;
			this.music = music;
			return true;
		} else if (music.isLyricExist()) {
			this.lyricowner = false;

			try {
				musicMyLyricVersion = lirycontrol.prepareEdit(music, user);
				System.out.println("MusicMyLyricVersion = "
						+ musicMyLyricVersion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Error at EditMusic.editThisLyric() "
						+ e.getMessage());
				musicMyLyricVersion = "";
			}

			this.newName = music.getNomemusica();
			this.newArtist = music.getInterprete();
			this.newAlbum = music.getAlbum();
			this.musicOriginalLyric = music.getOriginalLyric();
			// this.musicMyLyricVersion=lyricentity.getTextLyric();
			if ((this.musicMyLyricVersion == null || this.musicMyLyricVersion == "")
					&& this.musicOriginalLyric.length() > 0) {
				this.musicMyLyricVersion = this.musicOriginalLyric;
				System.out.println("musicOriginalLyric="
						+ this.musicOriginalLyric);
				System.out.println("musicMyLyricVersion="
						+ this.musicMyLyricVersion);
			}
			this.music = music;
			return true;
		}

		return false;
	}

	public MusicEntity saveChanges() {
		if (!this.newName.equals(""))
			this.music.setNomemusica(this.newName);
		if (!this.newArtist.equals(""))
			this.music.setInterprete(this.newArtist);
		if (!this.newAlbum.equals(""))
			this.music.setAlbum(this.newAlbum);

		return this.music;
	}

	public MusicEntity saveLyricChanges(MusicEntity music) {

		this.music.setMylyricversion(music.getMylyricversion());
		this.music.setChangeoriginal(true);
		return this.music;
	}
}
