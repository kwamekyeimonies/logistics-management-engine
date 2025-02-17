package logistics_management_engine.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "product_supplier")
public class ProductSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "supplier_id", columnDefinition = "UUID")
    private UUID supplierId;
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "created_by_employee_id", nullable = false, columnDefinition = "UUID")
    private Employee createdByEmployee;
    @Column(name = "updated_by")
    private String updatedBy;

}
