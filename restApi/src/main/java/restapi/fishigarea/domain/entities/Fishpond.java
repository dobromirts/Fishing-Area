package restapi.fishigarea.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "fishponds")
public class Fishpond extends BaseEntity{
    private String name;
    private String description;
    private String imageUrl;
    private Region region;

    public Fishpond() {
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
    @Column(name = "image_url",nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @ManyToOne(targetEntity = Region.class)
    @JoinColumn(name = "region_id",referencedColumnName = "id")
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
