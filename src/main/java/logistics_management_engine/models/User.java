package logistics_management_engine.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String staff_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String password;
    private String role;
    private String about_me;
    private String address;
    private String profile_picture;
    private String identification_card;
    private String identification_number;
    private String status;
    private String date_of_birth;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    private ZonedDateTime deleted_at;
    private Boolean is_deleted;
}
