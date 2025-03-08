package logistics_management_engine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_supplier")
public class ProductSupplier {
    @Id
    @Column(name = "Id", columnDefinition = "UUID")
    private UUID Id;

    @Column(name = "supplierName", nullable = false)
    private String supplierName;

    @Column(name = "contactName")
    private String contactName;

    @Column(name = "contactEmail")
    private String contactEmail;

    @Column(name = "contactPhone")
    private String contactPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "postalCode")
    private String postalCode;

    @Version
    @Column(name = "version")
    private Instant version;

    @JsonIgnore
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "createdByEmployeeId", nullable = false, columnDefinition = "VARCHAR")
    private Employee createdByEmployee;

    @Column(name = "updatedBy")
    private UUID updatedBy;

    @JsonIgnore
    @Column(name = "isDeleted", nullable = false)
    private boolean deleted = false;

    @JsonIgnore
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;
}
