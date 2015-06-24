package dei.uc.pt.ar.paj.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.Entities.UserEntity;
import dei.uc.pt.ar.paj.Entities.MusicEntity.Ordering;
import dei.uc.pt.ar.paj.Facade.MusicFacade;
import dei.uc.pt.ar.paj.Facade.PlaylistFacade;
import dei.uc.pt.ar.paj.Facade.UserFacade;

@SessionScoped
public class VirtualEJB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EJB
	private UserEJBLocal userEJB;
	@EJB
	private UserFacade userFacade;



	@EJB
	private MusicEJBLocal musicEJB;
	@EJB
	private MusicFacade musicFacade;

	private MusicEntity.Ordering orderMusic;


	@EJB
	private PlaylistEJBLocal playlistEJB;
	@EJB
	private PlaylistFacade playlistFacade;

	private PlaylistEntity.Ordering orderPlayList;

	public void populate() {
	}

	// Queria � BD para devolver as PlayLists do User
	public List<PlaylistEntity> getPlayLists(UserEntity user, int order) {
		orderPlayList = PlaylistEntity.Ordering.NAME_ASCEND;

		switch (order){
		case 1:
			orderPlayList = PlaylistEntity.Ordering.NAME_ASCEND;
			break;
		case 2:
			orderPlayList = PlaylistEntity.Ordering.NAME_DESCEND;
			break;
		case 3:
			orderPlayList = PlaylistEntity.Ordering.SIZE_ASCEND;
			break;
		case 4:
			orderPlayList = PlaylistEntity.Ordering.SIZE_DESCEND;
			break;
		case 5:
			orderPlayList = PlaylistEntity.Ordering.DATE_ASCEND;
			break;
		case 6:
			orderPlayList = PlaylistEntity.Ordering.DATE_DESCEND;
			break;
		}

		return playlistEJB.findOrdered(orderPlayList, user);
	}


	// Querie � BD para devolver todas as m�sicas
	public ArrayList<MusicEntity> getMusics() {
		return (ArrayList<MusicEntity>) musicEJB.getMusicas();
	}

	// Querie � BD para devolver as m�sicas por User
	public ArrayList<MusicEntity> getMusics(UserEntity user) {
		return (ArrayList<MusicEntity>) musicEJB.findOrdered(Ordering.FIND_BY_OWNER, user);
	}

	// Querie � BD para devolver as m�sicas ordenadas (todas ou por user)
	public ArrayList<MusicEntity> getMusics(UserEntity user, int order, boolean getMusicsByUser) {

		if(!getMusicsByUser){
			//Pesquisas de ordenação
			switch (order){
			case 1:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_NOME_ASC;
				break;
			case 2:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_NOME_DESC;
				break;
			case 3:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_INTERPRETE_ASC;
				break;
			case 4:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_INTERPRETE_DESC;
				break;
			case 5:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_TIME_ASC;
				break;
			case 6:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_TIME_DESC;
				break;
			case 7:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_ALBUM_ASC;
				break;
			case 8:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_ALBUM_DESC;
				break;
			case 9:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_YEAR_ASC;
				break;
			case 10:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_YEAR_DESC;
				break;
			case 11:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_OWNER_ASC;
				break;
			case 12:
				orderMusic = MusicEntity.Ordering.FIND_ALL_ORDER_BY_OWNER_DESC;
				break;
			}
		} else {
			//Pesquisas de ordenação por User
			switch (order){
			case 1:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_ASC;
				break;
			case 2:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_DESC;
				break;
			case 3:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_INTERPRETE_ASC;
				break;
			case 4:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_INTERPRETE_DESC;
				break;
			case 5:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_TIME_ASC;
				break;
			case 6:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_TIME_DESC;
				break;
			case 7:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_ALBUM_ASC;
				break;
			case 8:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_ALBUM_DESC;
				break;
			case 9:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_YEAR_ASC;
				break;
			case 10:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_YEAR_DESC;
				break;
			case 11:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_ASC;
				break;
			case 12:
				orderMusic = MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_DESC;
				break;
			}
		}

		return (ArrayList<MusicEntity>) musicEJB.findOrdered(orderMusic, user);
	}


	// Search Start
	// Querie � BD para devolver a pesquisa por m�sica/artista
	public List<MusicEntity> searchMusic(String search) {
		return this.musicEJB.search(search);
	}

	// Querie � BD para devolver a pesquisa por m�sica
	public List<MusicEntity> searchMusicByTrack(String search) {
		return this.musicEJB.searchByTrack(search);
	}

	// Querie � BD para devolver a pesquisa por artista
	public List<MusicEntity> searchMusicByArtist(String search) {
		return this.musicEJB.searchByArtist(search);
	}
	// Search End


	// Queries gen�ricas Start
	// PlayList
	public void add(PlaylistEntity playList) {
		// adiciona a nova PlayList � BD
		playList.setArraySize(playList.getSongs().size());
		this.playlistFacade.merge(playList);
	}
	
	public void addByPersist(PlaylistEntity playList) {
		this.playlistFacade.create(playList);
	}

	public void update(PlaylistEntity playList) {
		playList.setArraySize(playList.getSongs().size());
		this.playlistFacade.edit(playList);
	}

	public void remove(PlaylistEntity playList) {
		this.playlistFacade.remove(playList);
	}
	// PlayList

	// User
	public UserEntity getUserByEmail(String email){
		List<UserEntity>users=this.userEJB.getUsers();
		
		for(UserEntity u:users){
			if(u.getEmail().equals(email))
				return u;
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

	// Music
	public void add(MusicEntity music) {
		// adiciona uma nova Musica � BD
		this.musicFacade.merge(music);
	}

	public void update(MusicEntity music) {
		// actualiza os dados na BD
		this.musicFacade.edit(music);
	}

	public void remove(MusicEntity music) {
		this.musicFacade.remove(music);
	}
	// Music
	// Queries gen�ricas End
}