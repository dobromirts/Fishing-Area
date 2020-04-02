package restapi.fishigarea.web.models.request.fishpond;

public class FishpondAddRequestModel {
    private String name;
    private String description;

    public FishpondAddRequestModel() {
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
}
