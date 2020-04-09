package restapi.fishigarea.domain.models.service;

import java.util.List;

public class FishServiceModel {
    private String id;
    private String name;
    private String description;
    private String bate;
    private String gear;
    private String imageUrl;
    private List<FishpondServiceModel> fishponds;

    public FishServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBate() {
        return bate;
    }

    public void setBate(String bate) {
        this.bate = bate;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<FishpondServiceModel> getFishponds() {
        return fishponds;
    }

    public void setFishponds(List<FishpondServiceModel> fishponds) {
        this.fishponds = fishponds;
    }
}
