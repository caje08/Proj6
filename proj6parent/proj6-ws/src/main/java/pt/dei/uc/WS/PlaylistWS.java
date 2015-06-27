package pt.dei.uc.WS;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import pt.dei.uc.RESTentities.*;
import pt.dei.uc.converter.ConverterEntityToWS;
import dei.uc.pt.ar.paj.Entities.*;
import dei.uc.pt.ar.paj.ejb.PlaylistEJBLocal;
import dei.uc.pt.ar.paj.ejb.UserEJBLocal;


@Stateless
@Path("/playlist-mgmt")
public class PlaylistWS {
	
	@EJB
	private UserEJBLocal userejb;
	
	@EJB PlaylistEJBLocal playlistejb;
	public PlaylistWS() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
    @Path("/playlistsnumber")
    @Produces("text/plain")
	public int getPlaylistNumber(){
		
		List<PlaylistEntity> playlists = playlistejb.getPlaylists();
		List<PlaylistREST> playlistsREST = ConverterEntityToWS.convertPlaylistEntityToPlaylistWS(playlists);
		
		return playlistsREST.size();
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
	
	@Path("/addsong")
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response addSongToPlaylist(MusicREST musicrest){
		
		return Response.status(200).build();
	}
	
	@Path("/removesong")
	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response removeSongFromPlaylist(MusicREST musicrest){
		
		return Response.status(200).build();
	}
	
	
	

}
