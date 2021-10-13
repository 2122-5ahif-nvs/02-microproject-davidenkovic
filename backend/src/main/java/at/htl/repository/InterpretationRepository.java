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
public class InterpretationRepository implements PanacheRepository<Interpretation> {

    public InterpretationRepository() {
    }

    public void addInterpretation(Interpretation i){
        this.persist(i);
    }

    public boolean deleteInterpretation(){

        this.delete(findById(0L));
        return true;
    }

    public boolean updateInterpretationsByArtist(Interpretation fake, Interpretation updated){
        Interpretation interpretation = find("artist",fake.getArtist()).singleResult();

        if(fake == null || !fake.getArtist().equals(updated.getArtist())){
            return false;
        }

        this.delete(interpretation);
        this.persist(updated);
        return true;
    }

    public List<Interpretation> getInterpretations() {
        return this.findAll().list();
    }

    public List<Interpretation> getInterpretationsArtistName(String artistName) {
        return find("artistName",artistName).singleResult();
    }
}
