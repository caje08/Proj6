package pt.dei.uc.wscomsumers;

import javax.persistence.NoResultException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import pt.dei.uc.RESTentities.MusicREST;
import pt.dei.uc.RESTentities.MusicsREST;
import pt.dei.uc.RESTentities.NumberREST;

public class SongWSConsumer {

	public SongWSConsumer() {
		
	}
	
	public static void getNumberOfPlaylists(){
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/song-mgmt/songnumber");
			Response response = target.request().get();
			NumberREST number = response.readEntity(NumberREST.class);
			System.out.println("The App has "
					+ number.getUsernumber() + " songs.");
		} catch (ProcessingException e) {
			System.out.println("Could not connect to WebService!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
		
	}
	
	public static void getAllSongs(){
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/song-mgmt/all");
			Response response = target.request().get();
			MusicsREST musicsrest = response.readEntity(MusicsREST.class);
			
			for(MusicREST music : musicsrest.getMusics()){
				music.getInfo();
			}
		} catch (ProcessingException e) {
			System.out.println("Could not connect to WebService!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
		
	}
	
	public static void getSongInfo(long songid){
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/song-mgmt/infosong/");
			target = target.path(""+songid);
			Response response = target.request().get();
			MusicREST musicrest = response.readEntity(MusicREST.class);
			
			musicrest.getInfo();
			
			
		} catch (ProcessingException e) {
			System.out.println("Could not connect to WebService!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
		
	}
	
	public static void getSongsFromUser(long userid){
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/song-mgmt/usersong/");
			target = target.path(""+userid);
			Response response = target.request().get();
			MusicsREST musicsrest = response.readEntity(MusicsREST.class);
			
			for(MusicREST music : musicsrest.getMusics()){
				music.getInfo();
			}
			
			
		} catch (ProcessingException e) {
			System.out.println("Could not connect to WebService!");
		} catch(NoResultException e){
			System.out.println("No result found!");
		}
		
	}
	
	public static void removeSongFromUser(long userid, long songid){
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/song-mgmt/removesong/");
			target = target.path(""+userid);
			target = target.path(""+songid);
			
			
			Response response = target.request().get();
			
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}else{
				System.out.println("Song removed successfully!");
			}
			
		} catch (ProcessingException e) {
			System.out.println("Could not connect to WebService!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
		
	}
	
	

}
