package pt.dei.uc.WScore;

import java.util.Set;

import javax.ws.rs.core.Application;

import pt.dei.uc.WS.*;

@javax.ws.rs.ApplicationPath("/rest")
public class AppConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>> resources = new java.util.HashSet<>();
	    addRestResourceClasses(resources);
	    // extra.. for uploading files via form !
	    //resources.add(MultiPartFeature.class);
	    return resources;
	}
	

	/*
	 * Responsible for adding our "service" classes
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
	    // resources.add(xpto.class);
		resources.add(UserWS.class);
		resources.add(PlaylistWS.class);
		resources.add(MusicWS.class);
		
	}
	
}