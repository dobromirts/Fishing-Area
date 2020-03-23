package restapi.fishigarea.domain.models.service.user;

import javax.validation.constraints.NotBlank;

public class UserRegisterServiceModel {

    @NotBlank
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
