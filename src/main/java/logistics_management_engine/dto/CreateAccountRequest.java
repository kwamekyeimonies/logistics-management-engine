package logistics_management_engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class CreateAccountRequest {

    @NotNull(message = "first name is required")
    private String first_name;

    @NotNull(message = "last name is required")
    private String last_name;

    @Email(message = "email must be a valid email address")
    @NotNull(message = "email cannot be empty")
    @NotBlank(message = "email cannot be blank")
    private String email;

    private String phone_number;

    @NotBlank(message = "password cannot be blank/empty")
    @NotNull(message = "password is required")
    private String password;

    private String about_me;
    private String address;
    private String role;

    private MultipartFile profile_picture;
    private MultipartFile identification_card_front;
    private MultipartFile identification_card_back;

    private String identification_number;
    private String date_of_birth;
}
