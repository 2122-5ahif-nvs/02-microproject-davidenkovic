package at.htl.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Interpretation.getInterpretationsArtistName", query = "SELECT i FROM INT i WHERE i.artist.artistName LIKE :artistName"),
        @NamedQuery(name = "Interpretation.getInterpretations", query = "SELECT i FROM INT i "),
        @NamedQuery(name = "Interpretation.getById",query = "SELECT i FROM INT i WHERE i.id = :ID")


})
@XmlRootElement
@Entity(name ="INT")
public class Interpretation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @XmlElement
    @ManyToOne(cascade = CascadeType.ALL)
    private Song song;

    @XmlElement
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;

    @XmlElement
    private int bpm;

    @XmlElement
    private String songDuration;

    @XmlElement
    private int unit;

    public Interpretation(Song song, Artist artists, int bpm, String songDuration,int unit) {
        this.song = song;
        this.artist = artists;
        this.bpm = bpm;
        this.songDuration = songDuration;
        this.unit = unit;
    }

    public Interpretation() {
    }

    public Song getSong() {
        return song;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public int getUnit() {
        return unit;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.song.getSongname() + " by " + this.artist.getArtistName() + ": " + this.songDuration;
    }
}
