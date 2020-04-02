package restapi.fishigarea.domain.models.service;

import restapi.fishigarea.domain.models.service.RegionServiceModel;

public class FishpondServiceModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private RegionServiceModel regionServiceModel;

    public FishpondServiceModel() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RegionServiceModel getRegionServiceModel() {
        return regionServiceModel;
    }

    public void setRegionServiceModel(RegionServiceModel regionServiceModel) {
        this.regionServiceModel = regionServiceModel;
    }
}
