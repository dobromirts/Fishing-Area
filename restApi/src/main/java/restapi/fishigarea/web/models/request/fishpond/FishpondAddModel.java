package restapi.fishigarea.web.models.request.fishpond;

public class FishpondAddModel {
    private String name;
    private String description;
    private String regionName;


    public FishpondAddModel() {
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
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
