package logistics_management_engine.dto;

import jakarta.persistence.Id;

import java.time.ZonedDateTime;

public class CreateAccountResponse {
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
