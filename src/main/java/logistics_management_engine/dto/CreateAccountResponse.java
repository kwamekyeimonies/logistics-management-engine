package logistics_management_engine.dto;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class CreateAccountResponse {
    private String staff_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String message;

}
