package dei.uc.pt.ar.paj.web;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dei.uc.pt.ar.paj.Entities.MusicEntity;
import dei.uc.pt.ar.paj.Entities.PlaylistEntity;
import dei.uc.pt.ar.paj.render.Render;

import java.io.Serializable;


@Named
@SessionScoped
public class EditPlayList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3339826445044782486L;
	
	@Inject
	Render render;
	
	private PlaylistEntity playListToEdit;
	private MusicEntity musicToAddToPlayList;
	
	
	public PlaylistEntity getPlayListToEdit() {
		return playListToEdit;
	}

	public void setPlayListToEdit(PlaylistEntity playListToEdit) {
		this.playListToEdit = playListToEdit;
	}
	
	public MusicEntity getMusicToAddToPlayList() {
		return musicToAddToPlayList;
	}
	
	public void setMusicToAddToPlayList(MusicEntity musicToAddToPlayList) {
		this.musicToAddToPlayList = musicToAddToPlayList;
	}
	
	public void addMusicToPlayListStart(MusicEntity musicToAddToPlayList){
		this.musicToAddToPlayList=musicToAddToPlayList;
		this.render.setAddMusicToPlayList(true);
	}
	
	public PlaylistEntity addMusicToPlayListEnd(PlaylistEntity activePlayList){
		this.playListToEdit=activePlayList;
		this.playListToEdit.getSongs().add(musicToAddToPlayList);	
		
//		this.musicToAddToPlayList.getPlaylists().add(this.playListToEdit);
		
		return this.playListToEdit;
	}
	
	public void removeMusicFromPlayList(MusicEntity music){
		this.playListToEdit.getSongs().remove(music);
	}
	
	public void moveUpMusic(int index){
		if(!(index==0)){
			MusicEntity musicToMove=this.playListToEdit.getSongs().get(index);
			this.playListToEdit.getSongs().remove(index);
			index--;
			this.playListToEdit.getSongs().add(index, musicToMove);			
		}
	}
	
	public void moveDownMusic(int index){
		if(!(index==this.playListToEdit.getSongs().size()-1)){
			MusicEntity musicToMove=this.playListToEdit.getSongs().get(index);
			this.playListToEdit.getSongs().remove(index);
			index++;
			this.playListToEdit.getSongs().add(index, musicToMove);			
		}
	}
	
}
