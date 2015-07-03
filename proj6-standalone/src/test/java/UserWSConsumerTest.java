import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.mockito.Mock;

import pt.dei.uc.RESTentities.NumberREST;




public class UserWSConsumerTest {
	
	private static Client client= ClientBuilder.newClient();
	
	@Mock
	NumberREST numberrest;

	public UserWSConsumerTest() {
		
	}
	
	@Test
	public void shouldGetLoggedUserNumber() {
	    String responseBody = "";
	    String output;
	    Boolean result = true;
	    Boolean expResult = true;
	    
	   
	 
	    try {
	        URL url = new URL("http://localhost:9001/proj6-ws/rest/user-mgmt/usernumber");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Authorization", "Bearer " + getJwt());
	        conn.setRequestProperty("Accept", "application/xml");
	        
	        Response response = client.target("http://localhost:9001/proj6-ws/rest/user-mgmt/usernumber").
	        		request().get();
	        numberrest = response.readEntity(NumberREST.class);
	        if (conn.getResponseCode() != 200) {
	            // if not 200 response code then fail test
	            result = false;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));
	        while ((output = br.readLine()) != null) {
	            responseBody = output;
	        }
	        if (responseBody.length() < 1) {
	            // if response body is empty then fail test
	            result = false;
	        }
	        if()
	        conn.disconnect();
	    } catch (IOException e) {
	        // if MalformedURLException, ConnectException, etc. then fail test
	        result = false;
	    }
	    assertEquals(expResult, result);
	}
	
	@Test
	public void shouldShowAllUsers() {
	    String responseBody = "";
	    String output;
	    Boolean result = true;
	    Boolean expResult = true;
	 
	    try {
	        URL url = new URL("http://localhost:9001/proj6-ws/rest/user-mgmt/all");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Authorization", "Bearer " + getJwt());
	        conn.setRequestProperty("Accept", "application/xml");
	 
	        if (conn.getResponseCode() != 200) {
	            // if not 200 response code then fail test
	            result = false;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));
	        while ((output = br.readLine()) != null) {
	            responseBody = output;
	        }
	        if (responseBody.length() < 1) {
	            // if response body is empty then fail test
	            result = false;
	        }
	        conn.disconnect();
	    } catch (IOException e) {
	        // if MalformedURLException, ConnectException, etc. then fail test
	        result = false;
	    }
	    assertEquals(expResult, result);
	}
	
	@Test
	public void testUserInfo() {
	    String responseBody = "";
	    String output;
	    Boolean result = true;
	    Boolean expResult = true;
	 
	    try {
	        URL url = new URL("http://localhost:9001/proj6-ws/rest/user-mgmt/");
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Authorization", "Bearer " + getJwt());
	        conn.setRequestProperty("Accept", "application/xml");
	 
	        if (conn.getResponseCode() != 200) {
	            // if not 200 response code then fail test
	            result = false;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));
	        while ((output = br.readLine()) != null) {
	            responseBody = output;
	        }
	        if (responseBody.length() < 1) {
	            // if response body is empty then fail test
	            result = false;
	        }
	        conn.disconnect();
	    } catch (IOException e) {
	        // if MalformedURLException, ConnectException, etc. then fail test
	        result = false;
	    }
	    assertEquals(expResult, result);
	}
	
	

}
