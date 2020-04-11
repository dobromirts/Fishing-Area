package restapi.fishigarea.web.models.request.profile;

public class ProfileAddModel {
    private String name;
    private String information;


    public ProfileAddModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
