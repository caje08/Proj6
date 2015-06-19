/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dei.uc.pt.ar.paj.Facade;

import java.util.List;

import javax.ejb.Stateless;

import dei.uc.pt.ar.paj.Entities.MusicEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author
 */
@Stateless
public class MusicFacade extends AbstractFacade<MusicEntity> {
    @PersistenceContext(unitName = "myPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicFacade() {
        super(MusicEntity.class);
    }
    
    public List<MusicEntity> search(String searchTerm){
        TypedQuery<MusicEntity> q;
        q = em.createNamedQuery(MusicEntity.FIND_ALL,MusicEntity.class);
        q.setParameter("searchTerm","%"+searchTerm+"%");
        return q.getResultList();
    }

    public void updateSong(MusicEntity song, String title, String artist, String album, String releaseYear){
        song.setNomemusica(title);
        song.setInterprete(artist);
        song.setAlbum(album);
        song.setAnolancamento(releaseYear);
        super.edit(song);
    }   
    
}
