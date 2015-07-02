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
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;
import dei.uc.pt.ar.paj.ejb.VirtualEJB;


@Stateless
@Path("/playlist-mgmt")
public class PlaylistWS {
	
	@EJB
	private MusicEJBLocal musicejb;
	
	@Inject
	private VirtualEJB virtualejb;
	
	@EJB
	private UserEJBLocal userejb;
	
	@EJB PlaylistEJBLocal playlistejb;
	public PlaylistWS() {
		
	}
	
	@GET
    @Path("/playlistsnumber")
    @Produces("application/xml")
	public Response getPlaylistNumber(){
		
		List<PlaylistEntity> playlists = playlistejb.getPlaylists();
		List<PlaylistREST> playlistsREST = ConverterEntityToWS.convertPlaylistEntityToPlaylistWS(playlists);
		
		NumberREST number = new NumberREST();
		number.setUsernumber(playlistsREST.size());
		return Response.status(200).entity(number).build();
	}
	
	@GET
    @Path("/all")
    @Produces("application/xml")
	public Response getAllPlaylists(){
		PlaylistsREST allplaylists = new PlaylistsREST();
		List<PlaylistEntity> playlists = playlistejb.getPlaylists();
		List<PlaylistREST> playlistsREST = ConverterEntityToWS.convertPlaylistEntityToPlaylistWS(playlists);
		allplaylists.setPlaylists(playlistsREST);
		return Response.status(200).entity(allplaylists).build();		
	}
	
	@GET
    @Path("/songs/playlist/{id: \\d+}")
    @Produces("application/xml")
	public Response getSongsFromPlaylist(@PathParam("id") int id){
		PlaylistsREST allplaylists = new PlaylistsREST();
		List<PlaylistEntity> playlists = playlistejb.getPlaylists();
		List<PlaylistREST> playlistsREST = ConverterEntityToWS.convertPlaylistEntityToPlaylistWS(playlists);
		allplaylists.setPlaylists(playlistsREST);
		
		PlaylistREST requestedplaylist = new PlaylistREST();
		requestedplaylist = null;
		for(PlaylistREST play : playlistsREST){
			if(id==play.getIdplaylist()){
				requestedplaylist = play;
			}
		}
		List<MusicREST> songs = requestedplaylist.getSongs();
		MusicsREST songsfromplaylist = new MusicsREST();
		songsfromplaylist.setMusics(songs);
		
		return Response.status(200).entity(songsfromplaylist).build();
		
	}
	
	@GET
    @Path("/user/{id: \\d+}")
    @Produces("application/xml")
	public Response getPlaylistByUser(@PathParam("id") int id){
		PlaylistsREST allplaylists = new PlaylistsREST();
		List<PlaylistEntity> playlists = playlistejb.getPlaylists();
		List<PlaylistREST> playlistsREST = ConverterEntityToWS.convertPlaylistEntityToPlaylistWS(playlists);
		allplaylists.setPlaylists(playlistsREST);		
		
		List<PlaylistREST> playlistsFromUser = new ArrayList<>();
		for(PlaylistREST play : playlistsREST){
			if(id==play.getUtilizador().getUserid()){
				playlistsFromUser.add(play);
			}
		}
		
		PlaylistsREST requestedPlaylists = new PlaylistsREST();
		
		requestedPlaylists.setPlaylists(playlistsFromUser);
		
		return Response.status(200).entity(requestedPlaylists).build();
		
	}
	
	@Path("/addsong/{playid: \\d+}/{songid: \\d+}")
	@GET
	@Produces("application/xml")
	public Response addSongToPlaylist(@PathParam("songid")long songid, @PathParam("playid") long playid){
		PlaylistEntity play = playlistejb.findByID(playid);
		MusicEntity music = musicejb.getMusicByID(songid);
		play.addMusicPlaylist(music);
		
		virtualejb.update(play);
		
		return Response.status(200).build();
	}
	
	@Path("/removesong/{playid: \\d+}/{songid: \\d+}")
	@GET
	@Produces("application/xml")
	public Response removeSongFromPlaylist(@PathParam("songid")long songid, @PathParam("playid") long playid){
		PlaylistEntity play = playlistejb.findByID(playid);
		MusicEntity music = musicejb.getMusicByID(songid);
		play.getSongs().remove(music);		
		play.setSongs(play.getSongs());
		
		virtualejb.update(play);
		
		return Response.status(200).build();
	}
	
	
	

}
