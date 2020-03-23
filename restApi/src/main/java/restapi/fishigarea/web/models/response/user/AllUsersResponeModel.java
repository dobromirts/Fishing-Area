package restapi.fishigarea.web.models.response.user;

public class AllUsersResponeModel {
    private String id;

    private String username;

    private String role;

    public AllUsersResponeModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
