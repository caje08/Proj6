package pt.dei.uc.wscomsumers;

import java.net.ConnectException;

import pt.dei.uc.RESTentities.*;

import javax.persistence.NoResultException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class UserWSConsumer {
	

	public UserWSConsumer() {
		
	}

	public static void getNumberOfUsers() {
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/user-mgmt/usernumber");
			Response response = target.request().get();
			NumberREST usernumber = response.readEntity(NumberREST.class);
			System.out.println("The App has " + usernumber.getUsernumber()
					+ " users.");
		} catch (NullPointerException e) {
			System.out.println("Could not connect to client!");
			e.printStackTrace();
		}catch (ProcessingException e) {
			System.out.println("Could not connect retrieve information!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}

		
		
		
	}

	public static void getAllUsers() {		
		
		ResteasyClient client = new ResteasyClientBuilder().build();
	
		ResteasyWebTarget target;
		
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/user-mgmt/all");
			
			Response response = target.request().get();

			UsersREST usersrest = response.readEntity(UsersREST.class);

			for (UserREST user : usersrest.getUsers()) {
				user.getInfo();
			}
		} catch (ProcessingException e) {
			System.out.println("Could not connect to server!");			
		} catch (NoResultException | NullPointerException e1){
			System.out.println("No Users to show!");
		}
		
		

	}

	public static void getUserInfo(int id) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/");
		
		target = target.path(""+id);
		
		try {
			Response response = target.request().get();
			
			UserREST user = response.readEntity(UserREST.class);
			
			user.getInfo();
		} catch (ProcessingException e) {
			System.out.println("Could not connect to server!");
		}catch(NoResultException | NullPointerException e){
			System.out.println("No result found!");
		}
		
		
		
	}
	
	public static void getLoggedUserNumber(){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target;
		
		
		NumberREST usernumber;
		try {
			target = client
					.target("http://localhost:9001/proj6-ws/rest/user-mgmt/loggedusersnumber");
			Response response = target.request().get();
			usernumber = response.readEntity(NumberREST.class);
			
			System.out.println("The App has " + usernumber.getUsernumber()
					+ " logged users.");
			
		} catch (ProcessingException e) {
			System.out.println("Could not connect to server!");
		}catch(NoResultException | NullPointerException e){
			System.out.println("No result found!");
		}
		
		
		
		
		
	}
	
	public static void getLoggedUsers(){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/loggedusers");

		Response response = target.request().get();

		try {
			UsersREST usersrest = response.readEntity(UsersREST.class);

			for (UserREST user : usersrest.getUsers()) {
				user.getInfo();
			}
		} catch (ProcessingException e) {
			System.out.println("Could not connect to server!");
		}catch(NoResultException | NullPointerException e){
			System.out.println("No result found!");
		}
		
		
		
	}
	
	public static void removeUser(int id){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/remove/");
		
		target = target.path(""+id);

		try {
			Response response = target.request().get();
			
			UserREST user = response.readEntity(UserREST.class);
			
			System.out.println("The user with the following info was removed");
			
			user.getInfo();
		} catch (ProcessingException e) {
			System.out.println("Could not connect to server!");
		}catch(NoResultException e){
			System.out.println("No result found!");
		}
		
		
	}
	
	public static void addUser(UserREST user){
		ResteasyClient client = new ResteasyClientBuilder().build();		
			
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/adduser");
		
		Response response = target.request(MediaType.APPLICATION_XML).post(
				Entity.entity(user, "application/xml"));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		System.out.println("User added successfully!");
		
		
	}
	
	public static void editUserPassword(int id, String password){
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		UserREST user = new UserREST();		
		
		user.setUserid(id);
		user.setPassword(password);
		
		ResteasyWebTarget target = client
				.target("http://localhost:9001/proj6-ws/rest/user-mgmt/setpassword");
		
		Response response = target.request(MediaType.APPLICATION_XML).post(
				Entity.entity(user, "application/xml"));
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		System.out.println("The password was edited successfully!");
		
		
	}
	
	
	
	

}
