package restapi.fishigarea.web.models.request.fish;


import java.util.List;

public class FishAddModel {
    private String name;
    private String description;
    private String bate;
    private String gear;
    private List<String> fishponds;


    public FishAddModel() {
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

    public List<String> getFishponds() {
        return fishponds;
    }

    public void setFishponds(List<String> fishponds) {
        this.fishponds = fishponds;
    }
}

