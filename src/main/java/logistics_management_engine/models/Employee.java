package logistics_management_engine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @NaturalId
    private String staffId;

    @NaturalId
    private String username;

    @Version
    @Column(name = "version")
    private Instant version;

    private String firstName;
    private String lastName;

    @NaturalId
    private String email;

    @NaturalId
    private String phoneNumber;

    private String password;
    private String role;
    private String aboutMe;
    private String address;
    private String profilePicture;
    private String identificationCardFront;
    private String identificationCardBack;
    private String identificationNumber;
    private String status;
    private String dateOfBirth;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime deletedAt;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductCategory> createdCategories;

    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSupplier> createdSuppliers;

    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> createdProducts;
    @OneToMany(mappedBy = "createdByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> createdCategoriesList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return staffId;
    }
}