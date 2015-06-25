package pt.dei.uc.converter;

import java.util.ArrayList;
import java.util.List;

import pt.dei.uc.RESTentities.*;
import dei.uc.pt.ar.paj.Entities.*;

public class ConverterEntityToWS {

	public ConverterEntityToWS() {
	}
	
	public static List<UserREST> convertUserEntityToUserWS(List<UserEntity> userlist){
		List<UserREST> userlistREST = new ArrayList<>();
		for(UserEntity user : userlist){			
			userlistREST.add(convertUserEntityToUserWS(user));			
		}
		
		return userlistREST;
	}
	
	public static UserREST convertUserEntityToUserWS(UserEntity user){
		UserREST userrest = new UserREST();
		userrest.setName(user.getName());
		userrest.setUserid(user.getUserId());
		userrest.setEmail(user.getEmail());
		userrest.setPassword(user.getPassword());
		userrest.setDatanascimento(user.getDatanascimento());		
		return userrest;
	}
	
	public static List<PlaylistREST> convertPlaylistEntityToPlaylistWS(List<PlaylistEntity> playlists){
		List<PlaylistREST> playlistsREST = new ArrayList<>();
		for(PlaylistEntity play : playlists){
			playlistsREST.add(convertPlaylistEntityToPlaylistWS(play));
		}
		
		return playlistsREST;
	}
	
	public static PlaylistREST convertPlaylistEntityToPlaylistWS(PlaylistEntity playlist){
		PlaylistREST playlistrest = new PlaylistREST();
		playlistrest.setDatacriacao(playlist.getDatacriacao());
		playlistrest.setDesignacao(playlist.getDesignacao());
		playlistrest.setIdplaylist(playlist.getIdPlaylist());
		playlistrest.setSongs(convertMusicEntityToMusicWS(playlist.getSongs()));
		playlistrest.setUtilizador(convertUserEntityToUserWS(playlist.getUtilizador()));
		
		return playlistrest;
	}
	
	public static List<MusicREST> convertMusicEntityToMusicWS(List<MusicEntity> musics){
		List<MusicREST> musiclistrest = new ArrayList<>();
		for(MusicEntity music : musics){			
			musiclistrest.add(convertMusicEntityToMusicWS(music));
		}
		
		
		return musiclistrest;
	}
	
	public static MusicREST convertMusicEntityToMusicWS(MusicEntity music){
		MusicREST musicrest = new MusicREST();
		musicrest.setAlbum(music.getAlbum());
		musicrest.setAnolancamento(music.getAnolancamento());
		musicrest.setDatamusica(music.getDatamusica());
		musicrest.setInterprete(music.getInterprete());
		musicrest.setLength(music.getLength());
		musicrest.setMusicid(music.getMusicid());
		musicrest.setNomemusica(music.getNomemusica());
		musicrest.setPath(music.getPath());
		musicrest.setTipomusica(music.getTipomusica());
		musicrest.setUtilizador(convertUserEntityToUserWS(music.getUtilizador()));
		return musicrest;
	}
}
