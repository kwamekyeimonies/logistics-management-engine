package logistics_management_engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequest {

    @NotNull(message = "first name is required")
    private String first_name;
    @NotNull(message = "last name is required")
    private String last_name;
    @Email(message = "email must be a vlaid email address")
    @NotNull(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    private String email;
    private String phone_number;
    @NotBlank(message = "password cannot be blank/empty")
    @NotNull(message = "password is requried")
    private String password;
    private String about_me;
    private String address;
    private String profile_picture;
    private String identification_card;
    private String identification_number;
    private String date_of_birth;
}
