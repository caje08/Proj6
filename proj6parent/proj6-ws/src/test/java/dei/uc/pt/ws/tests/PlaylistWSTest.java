package dei.uc.pt.ws.tests;

import java.net.URI;
import java.net.URISyntaxException;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistWSTest {
	
	URI uri;

	public PlaylistWSTest() {
		
	}
	
	@Before
	public void readURI() throws URISyntaxException{
	uri = new URI("http://localhost:9001/proj6-ws/rest/playlist-mgmt");
		
	}
	
	@Test
	public void shouldShowNumberOfPlaylists(){
		expect().statusCode(200).when().get(uri + "/playlistsnumber");
	}
	
	@Test
	public void shouldShowAllPlaylists(){
		expect().statusCode(200).when().get(uri + "/all");
	}
	
	@Test
	public void shouldGetSongsFromPlaylist(){
		expect().statusCode(200).when().get(uri + "/songs/playlist/44");
	}
	
	

}
