package restapi.fishigarea.web.models.request.profile;

public class CatchAddModel {
    private String title;
    private String description;
    private String fishpondName;

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

    public String getFishpondName() {
        return fishpondName;
    }

    public void setFishpondName(String fishpondName) {
        this.fishpondName = fishpondName;
    }
}
