package logistics_management_engine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements UserDetails {
    @Id
    @Column(nullable = false, unique = true)
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
    private String identification_card_front;
    private String identification_card_back;
    private String identification_number;
    private String status;
    private String date_of_birth;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    private ZonedDateTime deleted_at;
    private Boolean is_deleted;
    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductCategory> createdCategories;

    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSupplier> createdSuppliers;

    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> createdProducts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return staff_id;
    }
}
