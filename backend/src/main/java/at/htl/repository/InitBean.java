package at.htl.repository;


import at.htl.entity.Artist;
import at.htl.entity.Interpretation;
import at.htl.entity.Song;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class InitBean {
    @Inject
    EntityManager em;

    @Transactional
    void onStart(@Observes StartupEvent event) {
        Artist a1 = new Artist("2Pac","Death-Row");
        Artist a2 = new Artist("Snoop-Dogg","Death-Row");
        Artist a3 = new Artist("Xzibit","Death-Row");
        this.em.persist(a1);
        this.em.persist(a2);
        this.em.persist(a3);

        Song s1 = new Song("Hit-'Em-Up",a1);
        this.em.persist(s1);
        Interpretation i1 = new Interpretation(s1,a1,95,"4:20",2000);

        Song s2 = new Song("Doggy-Dogg-Christmas",a2);
        this.em.persist(s2);
        Interpretation i2 = new Interpretation(s2,a2,97,"2:35",400);

        this.em.persist(i1);
        this.em.persist(i2);

    }

}
