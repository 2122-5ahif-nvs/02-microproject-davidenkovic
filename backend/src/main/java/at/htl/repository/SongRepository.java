package at.htl.repository;

import at.htl.entity.Artist;
import at.htl.entity.Interpretation;
import at.htl.entity.Song;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@ApplicationScoped
public class SongRepository implements PanacheRepository<Song> {

    public SongRepository() {

    }

    @Transactional
    @PostConstruct
    public void addSong(Song s){
        this.persist(s);
    }
    @Transactional
    @PostConstruct
    public boolean deleteSong(){
        this.delete(findById(0L));
        return true;
    }
    @Transactional
    @PostConstruct
    public boolean updateSong(String artist,Song updated){
        Song song = find("artistName",artist).singleResult();

        if(song == null || !song.getArtist().getArtistName().equals(updated.getArtist().getArtistName())){
            return false;
        }

        this.delete(song);
        this.addSong(updated);
        return true;
    }


    public List<Song> getSongs() {
        return this.findAll().list();
    }


    public List<Song> getSongsByArtistName(String name) {
        //return (List<Song>) getEntityManager().createNamedQuery("Song.getSongsByArtistName").setParameter("artistName",artistName).getResultList();
        return list("artist.artistName",name);
    }
}
