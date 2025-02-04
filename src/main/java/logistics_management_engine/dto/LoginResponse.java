package logistics_management_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String first_name;
    private String last_name;
    private String email;
    private String profile_picture;
    private String role;
    private String staff_id;
    private String id;
    private String phone_number;
    private String access_token;
    private String refresh_token;
    private String message;
}
