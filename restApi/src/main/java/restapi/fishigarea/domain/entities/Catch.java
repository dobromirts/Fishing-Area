package restapi.fishigarea.domain.entities;

import javax.persistence.*;


@Entity
@Table(name = "catches")
public class Catch extends BaseEntity{
    private String title;
    private String description;
    private String imageUrl;
    private Fishpond fishpond;
    private UserProfile userProfile;

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "description", nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne(targetEntity = Fishpond.class)
    @JoinColumn(name = "fishpond_id",referencedColumnName = "id")
    public Fishpond getFishpond() {
        return fishpond;
    }

    public void setFishpond(Fishpond fishpond) {
        this.fishpond = fishpond;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
