package pt.dei.uc.mainApp;

import java.util.Scanner;

import pt.dei.uc.wscomsumers.*;
import pt.dei.uc.RESTentities.*;

public class StandaloneApp {

	public static void main(String[] args) {
		int exitcondition = 1;
		Scanner sc = new Scanner(System.in);

		while (exitcondition > 0) {
			System.out.println("Select your option: ");

			System.out.println("User Management - 1");
			System.out.println("Playlist Management - 2");
			System.out.println("Song Management - 3");
			System.out.println("Exit - 4");

			switch (sc.nextInt()) {

			case 1:
				userManagement();
				break;

			case 2:
				playlistManagement();
				break;

			case 3:
				songManagement();
				break;
			case 4:

				exitcondition = -1;
			}

		}

	}

	public static void songManagement() {
		Scanner sc = new Scanner(System.in);
		int exitcondition = 1;
		while (exitcondition > 0) {
			System.out.println();
			System.out.println("Number of songs - 1");
			System.out.println("Show all songs - 2 ");
			System.out.println("Show song info - 3");
			System.out.println("Show songs from user - 4");
			System.out.println("Remove Song belonging to user - 5");
			System.out.println("Exit - 6");

			switch (sc.nextInt()) {
			case 1:
				SongWSConsumer.getNumberOfPlaylists();
				break;
			case 2:
				SongWSConsumer.getAllSongs();
				break;
			case 3:
				showSongsInfo();
				break;
			case 4:
				showSongsFromUser();
				break;
			case 5:
				removeSongFromUser();
				break;
			case 6:
				exitcondition = -1;
				break;
			}
		}
	}

	private static void removeSongFromUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert user ID: ");
		int userid = sc.nextInt();
		System.out.println("Insert song ID: ");
		int songid = sc.nextInt();

		SongWSConsumer.removeSongFromUser(userid, songid);
	}

	private static void showSongsFromUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert user ID: ");
		SongWSConsumer.getSongsFromUser(sc.nextInt());
	}

	private static void showSongsInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert song ID: ");
		SongWSConsumer.getSongInfo(sc.nextInt());
	}

	public static void playlistManagement() {

		Scanner sc = new Scanner(System.in);
		int exitcondition = 1;
		while (exitcondition > 0) {
			System.out.println();
			System.out.println("Select your option: ");
			System.out.println("Number of existing playlists - 1");
			System.out.println("Show all playlists - 2");
			System.out.println("Show songs belonging to a playlist - 3");
			System.out.println("Show playlists belonging to a user - 4");
			System.out.println("Add song to playlist: - 5");
			System.out.println("Remove song from playlist - 6");
			System.out.println("Exit - 7");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				PlaylistWSConsumer.getNumberOfPlaylists();
				break;
			case 2:
				PlaylistWSConsumer.getAllPlaylists();
				break;
			case 3:
				songsFromPlaylist();
				break;
			case 4:
				playlistsFromUser();
				break;
			case 5:
				addSongToPlaylist();
				break;
			case 6:

			case 7:
				exitcondition = -1;
				break;
			}
		}

	}

	public static void addSongToPlaylist() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert the ID of the playlist you want to add"
				+ " the song to: ");
		int playid = sc.nextInt();

		System.out.println("Insert the ID of the song you want to add: ");
		int songid = sc.nextInt();

		PlaylistWSConsumer.addSongToPlaylist(playid, songid);
	}

	public static void removeSongFromPlaylist() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert the ID of the playlist you want to remove"
				+ " the song from: ");
		int playid = sc.nextInt();

		System.out.println("Insert the ID of the song you want to remove: ");
		int songid = sc.nextInt();

		PlaylistWSConsumer.removeSongFromPlaylist(playid, songid);
	}

	public static void playlistsFromUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert the user ID: ");
		PlaylistWSConsumer.getPlaylistByUser(sc.nextInt());

	}

	public static void songsFromPlaylist() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert the ID of the playlist:");
		PlaylistWSConsumer.getSongsFromPlaylist(sc.nextInt());
	}

	public static void userManagement() {
		Scanner sc = new Scanner(System.in);
		int exitcondition = 1;
		while (exitcondition > 0) {

			System.out.println();
			System.out.println("Select your option: ");
			System.out.println("Show number of App users - 1");
			System.out.println("Show all users - 2");
			System.out.println("Show info from user - 3");
			System.out.println("Number of logged users - 4");
			System.out.println("List of logged users - 5");
			System.out.println("Add User - 6");
			System.out.println("Remove User - 7");
			System.out.println("Edit user's password - 8");
			System.out.println("Exit - 9");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				UserWSConsumer.getNumberOfUsers();
				break;
			case 2:
				UserWSConsumer.getAllUsers();
				break;
			case 3:
				infoFromUser();
				break;
			case 4:
				UserWSConsumer.getLoggedUserNumber();
				break;
			case 5:
				UserWSConsumer.getLoggedUsers();
				break;
			case 6:
				addUser();
				break;
			case 7:
				removeUser();
				break;
			case 8:
				editUserPassword();
				break;
			case 9:
				exitcondition = -1;
			}

		}
	}

	public static void infoFromUser() {
		System.out.println("Insert Id of user");

		Scanner sc = new Scanner(System.in);
		int userid = sc.nextInt();

		UserWSConsumer.getUserInfo(userid);

	}

	public static void addUser() {
		UserREST user = new UserREST();
		int exitcondition = 1;
		Scanner sc = new Scanner(System.in);

		System.out.println("Insert user's name");
		user.setName(sc.next());

		while (exitcondition > 0) {
			System.out.println("Insert user's password");
			String password = sc.next();
			System.out.println("Confirm user's password");
			String confpassword = sc.next();
			if (password.equals(confpassword)) {
				user.setPassword(password);
				exitcondition = -1;
			}
		}

		System.out.println("Insert user's birthdate");
		user.setDatanascimento(sc.next());

		System.out.println("Insert user's email");
		user.setEmail(sc.next());

		UserWSConsumer.addUser(user);
	}

	public static void removeUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert Id of user you wish to remove");

		int id = sc.nextInt();

		UserWSConsumer.removeUser(id);

	}

	public static void editUserPassword() {
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Insert the Id of the user who's password you"
				+ " want to edit");
		int id = sc.nextInt();
		System.out.println("Insert the new password");
		String password = sc1.next();

		UserWSConsumer.editUserPassword(id, password);
	}

}
