package at.htl.repository;

import at.htl.entity.Artist;
import at.htl.entity.Song;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {


    public ArtistRepository() {

    }

    @Transactional
    public void addArtist(Artist artist){
        this.persist(artist);
    }

    @Transactional
    public boolean deleteArtist(){

        delete(findById(0L));
        return true;

    }
    @Transactional
    public boolean updateArtist(String label, Artist updated){
        Artist artist = find("currentLabel",label).singleResult();

        if(artist == null || !artist.getArtistName().equals(updated.getArtistName())){
            return false;
        }

        this.delete(artist);
        this.persist(updated);
        return true;
    }

    public List<Artist> getArtistByLabel (String label){
        return find("currentLabel",label).singleResult();
    }

    public List<Artist> getArtists (){
        return this.findAll().list();
    }

    public Artist getArtistById(Long id){
        return this.findById(id);
    }
}
