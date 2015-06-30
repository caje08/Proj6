package pt.dei.uc.WS;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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


@Stateless
@Path("/song-mgmt")
public class MusicWS {
	
	@EJB
	private MusicEJBLocal musicejb;
	
	@EJB
	private UserEJB userejb;
	
	@Inject
	private VirtualEJB virtualejb;

	public MusicWS() {
		
	}
	
	@GET
	@Path("/songnumber")
	@Produces("text/plain")
	public int getSongsNumber(){
		List<MusicEntity> musics = musicejb.getMusicas();
		List <MusicREST> musicsREST = ConverterEntityToWS.convertMusicEntityToMusicWS(musics);
		
		return musicsREST.size();
	}
	
	@GET
	@Path("/all")
	@Produces("application/xml")
	public Response getAllSongs(){
		List<MusicEntity> music = musicejb.getMusicas();
		List <MusicREST> musicREST = ConverterEntityToWS.convertMusicEntityToMusicWS(music);
		MusicsREST musicsrest = new MusicsREST();
		musicsrest.setMusics(musicREST);
		return Response.status(200).entity(musicsrest).build();	
	}
	
	@GET
	@Path("/infosong/{songid: \\d+}")
	@Produces("application/xml")
	public Response getSongInfo(@PathParam("songid") int id){
		MusicEntity song = musicejb.getMusicByID(id);		
		
		return Response.status(200).entity(song).build();
	}
	
	@GET
	@Path("/usersong/{userid: \\d+}")
	@Produces("application/xml")
	public Response getUserSongs(@PathParam("userid") long id){
		
		UserEntity user = userejb.findById(id);
		
		
		
		List<MusicREST> songs = ConverterEntityToWS.convertMusicEntityToMusicWS(musicejb.findOrdered(MusicEntity.Ordering.FIND_BY_OWNER_ORDER_BY_NOME_ASC, user)) ;
		
		MusicsREST songslist = new MusicsREST();
		songslist.setMusics(songs);
		return Response.status(200).entity(songslist).build();
	}
	
	@GET
	@Path("/removesong/{userid: d\\+}/{songid: d\\+}")
	@Produces("application/xml")
	public Response removeSongFromUser(@PathParam("userid") long userid, @PathParam("songid") long songid ){
		UserEntity user = userejb.findById(userid);
		user.getUsermusicas().remove(musicejb.getMusicByID(songid));
		
		virtualejb.update(user);
		return Response.status(200).build();
	}
	
	
	
	

}
