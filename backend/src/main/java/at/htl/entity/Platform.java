package at.htl.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Platform.getPlatforms", query = "SELECT platform FROM PLA "),
})
@Entity(name ="PLA")
public class Platform {
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    private Integer id;

    private StreamingPlatform platform;
    @ManyToOne(cascade = CascadeType.ALL)
    private Interpretation interpretation;

    public Platform(StreamingPlatform platform,Interpretation interpretation) {
        this.platform = platform;
        this.interpretation = interpretation;
    }

    public Platform() {
    }

    public StreamingPlatform getPlatform() {
        return platform;
    }

    public int getId() {
        return id;
    }

    public void setPlatform(StreamingPlatform platform) {
        this.platform = platform;
    }

    public Interpretation getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Interpretation interpretation) {
        this.interpretation = interpretation;
    }
}
