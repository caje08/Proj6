package pt.dei.uc.wscomsumers;

import pt.dei.uc.RESTentities.*;

import javax.persistence.NoResultException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class PlaylistWSConsumer {

	public PlaylistWSConsumer() {
	}

	public static void getNumberOfPlaylists() {

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/playlistsnumber");
			Response response = target.request().get();
			NumberREST number = response.readEntity(NumberREST.class);
			System.out.println("The App has "
					+ number.getUsernumber() + " playlists.");
		} catch (ProcessingException e) {
			System.out.println("Could not connect retrieve information!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
	}

	public static void getAllPlaylists() {
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/all");
			Response response = target.request().get();
			PlaylistsREST playlistsrest = response
					.readEntity(PlaylistsREST.class);

			for (PlaylistREST play : playlistsrest.getPlaylists()) {
				play.getInfo();
			}

		} catch (ProcessingException e) {
			System.out.println("Could not connect retrieve information!");
			
		}catch(NoResultException e){
			System.out.println("No result found!");
		}

	}

	public static void getSongsFromPlaylist(int id) {

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;

		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/songs/playlist/");

			target = target.path("" + id);

			Response response = target.request().get();
			MusicsREST musicsrest = response.readEntity(MusicsREST.class);

			for (MusicREST music : musicsrest.getMusics()) {
				music.getInfo();
			}
		} catch (ProcessingException e) {

			System.out.println("Could not connect retrieve information!");
			
		}catch(NoResultException e){
			System.out.println("No result found!");
		}

	}

	public static void getPlaylistByUser(int id) {

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;

		try {

			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/user/");
			target = target.path("" + id);

			Response response = target.request().get();

			PlaylistsREST playlistsrest = response
					.readEntity(PlaylistsREST.class);

			for (PlaylistREST play : playlistsrest.getPlaylists()) {
				play.getInfo();
			}

		} catch (ProcessingException e) {

			System.out.println("Could not connect retrieve information!");
			

		} catch(NoResultException e){
			System.out.println("No result found!");
		}

	}

	public static void addSongToPlaylist(int playid, int songid) {
		long playid1 = playid;
		long songid1 = songid;

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;

		try {

			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/addsong");

			target = target.path("" + playid1);
			target = target.path("" + songid1);

			Response response = target.request().get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("The song was added successfully!");

		} catch (ProcessingException e) {

			System.out.println("Could not connect retrieve information!");
			

		}catch(NoResultException e){
			System.out.println("No result found!");
		}

	}

	public static void removeSongFromPlaylist(long playid, long songid) {
		long playid1 = playid;
		long songid1 = songid;

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;

		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/playlist-mgmt/removesong");

			target = target.path("" + playid1);
			target = target.path("" + songid1);

			Response response = target.request().get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("The song was removed successfully!");

		} catch (ProcessingException e) {

			System.out.println("Could not connect retrieve information!");
			

		}catch(NoResultException e){
			System.out.println("No result found!");
		}

	}

}
