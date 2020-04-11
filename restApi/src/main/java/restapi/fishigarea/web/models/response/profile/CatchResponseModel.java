package restapi.fishigarea.web.models.response.profile;

import restapi.fishigarea.domain.models.service.FishpondServiceModel;

public class CatchResponseModel {
    private String title;
    private String description;
    private String imageUrl;
    private FishpondServiceModel fishpond;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public FishpondServiceModel getFishpond() {
        return fishpond;
    }

    public void setFishpond(FishpondServiceModel fishpond) {
        this.fishpond = fishpond;
    }
}
