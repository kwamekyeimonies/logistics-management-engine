package logistics_management_engine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import java.time.ZonedDateTime;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NaturalId
    private String staff_id;
    @NaturalId
    private String user_name;
    private String first_name;
    private String last_name;
    @NaturalId
    private String email;
    @NaturalId
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
