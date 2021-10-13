package at.htl.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Artist.getArtistByLabel", query = "SELECT a FROM ART a WHERE a.currentLabel LIKE :label"),
        @NamedQuery(name = "Artist.getArtists", query = "SELECT a FROM ART a "),
        @NamedQuery(name = "Artist.getById",query = "SELECT a FROM ART a WHERE a.id = :ID")

})
@XmlRootElement
@Entity(name = "ART")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    private String artistName;
    @XmlElement
    private String currentLabel;


    public Artist(String artistName, String currentLabel) {
        this.artistName = artistName;
        this.currentLabel = currentLabel;
    }

    public Artist() {
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCurrentLabel() {
        return currentLabel;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.artistName + " is signed by " + this.currentLabel;
    }
}
