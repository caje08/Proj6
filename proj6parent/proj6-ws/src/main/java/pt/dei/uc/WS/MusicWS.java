package pt.dei.uc.WS;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pt.dei.uc.RESTentities.*;
import pt.dei.uc.converter.ConverterEntityToWS;
import pt.dei.uc.converter.ConverterWStoEntity;
import dei.uc.pt.ar.paj.Entities.*;
import dei.uc.pt.ar.paj.ejb.MusicEJB;
import dei.uc.pt.ar.paj.ejb.MusicEJBLocal;
import dei.uc.pt.ar.paj.ejb.PlaylistEJBLocal;
import dei.uc.pt.ar.paj.ejb.UserEJB;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
@Path("/song-mgmt")
public class MusicWS {
	
	@EJB
	private MusicEJBLocal musicejb;
	
	@EJB
	private UserEJBLocal userejb;
	
	@Inject
	private VirtualEJB virtualejb;
	
	static Logger logger = LoggerFactory.getLogger(MusicWS.class);

	public MusicWS() {
		
	}
	
	@GET
	@Path("/songnumber")
	@Produces("application/xml")
	public Response getSongsNumber(){
		List<MusicEntity> musics = musicejb.getMusicas();
		List <MusicREST> musicsREST = ConverterEntityToWS.convertMusicEntityToMusicWS(musics);
		
		NumberREST number = new NumberREST();
		number.setUsernumber(musicsREST.size());
		return Response.status(200).entity(number).build();
	}
	
	@GET
	@Path("/all")
	@Produces("application/xml")
	public Response getAllSongs(){
		try {
			List<MusicEntity> music = musicejb.getMusicas();
			List <MusicREST> musicREST = ConverterEntityToWS.convertMusicEntityToMusicWS(music);
			MusicsREST musicsrest = new MusicsREST();
			musicsrest.setMusics(musicREST);
			return Response.status(200).entity(musicsrest).build();
		} catch (NullPointerException e) {
			logger.info("No music found!");
			return Response.status(200).build();
		}	
	}
	
	@GET
	@Path("/infosong/{songid: \\d+}")
	@Produces("application/xml")
	public Response getSongInfo(@PathParam("songid") int id){
		try {
			MusicEntity music;			
			try {
				music = musicejb.getMusicByID(id);
			} catch ( NoResultException e) {
				logger.info("No song found with that ID!");
				return Response.status(200).build();
				
			}		
			
			MusicREST song = ConverterEntityToWS.convertMusicEntityToMusicWS(music);			
			return Response.status(200).entity(song).build();
		} catch (NullPointerException | NoResultException e) {
			
			return Response.status(304).build();
		}
	}
	
	@GET
	@Path("/usersong/{userid: \\d+}")
	@Produces("application/xml")
	public Response getUserSongs(@PathParam("userid") long id){
		
		UserEntity user;
		
		user = userejb.findById(id);		
		List<MusicREST> songs = ConverterEntityToWS.convertMusicEntityToMusicWS(musicejb.findOrdered(MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_ASC, user)) ;
		
		MusicsREST songslist = new MusicsREST();
		songslist.setMusics(songs);
		return Response.status(200).entity(songslist).build();
	}
	
	@GET
	@Path("/removesong/{userid: \\d+}/{songid: \\d+}")
	@Produces("application/xml")
	public Response removeSongFromUser(@PathParam("userid") long userid, @PathParam("songid") long songid ){
		MusicEntity music = musicejb.getMusicByID(songid);
		UserEntity user = userejb.findById(userid);
		
		if(music.getOwner().getUserId()==user.getUserId()){
			music.setOwner(userejb.findById(1));
			user.getUsermusicas().remove(music);
		
		}else{
			return Response.notModified().build();
		}
				
		
		return Response.status(200).build();
	}
	
}
