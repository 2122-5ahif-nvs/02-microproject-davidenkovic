package at.htl.repository;

import at.htl.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.annotation.Inherited;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlatformRepository implements PanacheRepository<Platform> {


    public PlatformRepository() {

    }

    public void addPlatform(Platform p){
        this.persist(p);
    }

    public boolean deletePlatform(){

        this.delete(findById(0L));
        return true;
    }

    public List<Platform> getPlatforms() {
        return this.findAll().list();

    }
}
