package pt.dei.uc.converter;

import java.util.ArrayList;
import java.util.List;

import pt.dei.uc.RESTentities.*;
import dei.uc.pt.ar.paj.Entities.*;

public class ConverterWStoEntity {

	public ConverterWStoEntity() {
	}
	
	public static UserEntity convertUserRESTtoEntity(UserREST userrest){
		
		UserEntity userent = new UserEntity();
		userent.setName(userrest.getName());		
		userent.setEmail(userrest.getEmail());
		userent.setPassword(userrest.getPassword());
		userent.setDatanascimento(userrest.getDatanascimento());
		return userent;
	}
	
	public static MusicEntity convertMusicRESTtoEntity(MusicREST musicrest){
		MusicEntity musicent = new MusicEntity();
		
		musicent.setAlbum(musicrest.getAlbum());
		musicent.setAnolancamento(musicrest.getAnolancamento());
		musicent.setDatamusica(musicrest.getDatamusica());
		musicent.setInterprete(musicrest.getInterprete());
		musicent.setMusicid(musicrest.getMusicid());
		musicent.setNomemusica(musicrest.getNomemusica());
		
		return musicent;
	}

}
