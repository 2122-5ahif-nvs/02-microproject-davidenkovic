package at.htl.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NamedQueries({
            @NamedQuery(name = "Song.getSongsByArtistName", query = "SELECT s FROM SON s JOIN ART a ON s.artist.artistName = a.artistName WHERE a.artistName LIKE :artistName"),
        @NamedQuery(name = "Song.getSongs", query = "SELECT s FROM SON s "),
        @NamedQuery(name = "Song.getById",query = "SELECT s FROM SON s WHERE s.id = :ID")


})
@Entity(name = "SON")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @XmlElement
    private String songname;
    @XmlElement
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;
    @XmlElement
    private String genre;

    public Song(String songname, Artist artist) {
        this.songname = songname;
        this.artist = artist;
    }

    public Song() {
    }

    public String getSongname() {
        return songname;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getId() {
        return id;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return this.songname + " by " + this.artist.getArtistName();
    }
}
