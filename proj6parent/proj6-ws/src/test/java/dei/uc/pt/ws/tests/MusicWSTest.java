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
public class MusicWSTest {
	
	URI uri;

	public MusicWSTest() {
		
	}
	
	@Before
	public void readURI() throws URISyntaxException{
	uri = new URI("http://localhost:9001/proj6-ws/rest/song-mgmt");
	}
	
	@Test
	public void shouldShowNumberOfSongs(){
		expect().statusCode(200).when().get(uri + "/songnumber");		
	}
	
	@Test
	public void shouldShowAllSongs(){
		expect().statusCode(200).when().get(uri + "/all");		
	}
	
	@Test
	public void shouldShowSongInfo(){
		expect().statusCode(200).when().get(uri + "/infosong/2");		
	}
	
	@Test
	public void shouldFailIfSongDoesNotExist(){
		expect().statusCode(500).when().get(uri + "/infosong/1000");		
	}
	
	@Test
	public void shouldShowSongsFromUser(){
		expect().statusCode(200).when().get(uri + "/usersong/1");
	}
	

	
	
	
	
	
	

}
