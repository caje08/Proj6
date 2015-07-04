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
public class UserWSTest {
	
	
	URI uri;

	public UserWSTest() {
	}
	
	@Before
	public void readURI() throws URISyntaxException{
	uri = new URI("http://localhost:9001/proj6-ws/rest/user-mgmt");
		
	}
	
	@Test
	public void shouldGetAllUsers(){
		
		expect().statusCode(200).when().get(uri + "/all");
		
	}
	
	@Test
	public void shouldShowUsersSize(){
		
		expect().statusCode(200).when().get(uri + "/usernumber");
	}
	
	@Test
	public void shouldFailIfUserInfoParameterIsNotAnInt(){
		expect().statusCode(404).when().get(uri + "/string");
	}
	
	@Test
	public void shouldPassIfUserInfoParameterIsAValidUser(){
		expect().statusCode(200).when().get(uri + "/1");
	}
	
	@Test
	public void shouldFailIfUserInfoParameterIsNotAValidUser(){
		expect().statusCode(500).when().get(uri + "/1000");
	}
	
	@Test
	public void shouldShowLoggedUserNumbers(){
		expect().statusCode(200).when().get(uri + "/loggedusersnumber");
	}
	
	@Test
	public void shouldShowLoggedUsers(){
		expect().statusCode(200).when().get(uri + "/loggedusers");
	}
	
	
	

}
