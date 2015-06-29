package pt.dei.uc.mainApp;

import java.util.Scanner;

import pt.dei.uc.wscomsumers.*;
import pt.dei.uc.RESTentities.*;

public class StandaloneApp {

	public static void main(String[] args) {
		
		PlaylistWSConsumer.getAllPlaylists();
			
		PlaylistWSConsumer.getSongsFromPlaylist(63);
		
		
	}

}
