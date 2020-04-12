package restapi.fishigarea.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fishes")
public class Fish extends BaseEntity{
    private String name;
    private String description;
    private String bate;
    private String gear;
    private String imageUrl;
    private List<Fishpond> fishponds;

    public Fish() {
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "bate",nullable = false,columnDefinition = "TEXT")
    public String getBate() {
        return bate;
    }

    public void setBate(String bate) {
        this.bate = bate;
    }
    @Column(name = "gear",nullable = false,columnDefinition = "TEXT")
    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }
    @Column(name = "image_url",nullable = false,columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @ManyToMany(targetEntity = Fishpond.class)
    @JoinTable(name = "fishes_fishponds", joinColumns = @JoinColumn(name = "fish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fishpond_id", referencedColumnName = "id"))
    public List<Fishpond> getFishponds() {
        return fishponds;
    }

    public void setFishponds(List<Fishpond> fishponds) {
        this.fishponds = fishponds;
    }
}
