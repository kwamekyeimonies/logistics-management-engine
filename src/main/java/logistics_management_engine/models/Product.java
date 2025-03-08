package logistics_management_engine.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productId", columnDefinition = "UUID")
    private UUID Id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Version
    @Column(name = "version")
    private Instant version;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false, columnDefinition = "UUID")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false, columnDefinition = "UUID")
    private ProductSupplier supplier;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "unitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "costPrice", nullable = false)
    private Double costPrice;

    @Column(name = "quantityInStock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "reorderLevel")
    private Integer reorderLevel;

    @Column(name = "unitOfMeasurement")
    private String unitOfMeasurement;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "createdByEmployeeId", nullable = false)
    private Employee createdByEmployee;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "warrantyPeriod")
    private String warrantyPeriod;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "taxRate")
    private Double taxRate;

    @Column(name = "discount")
    private Double discount;

}
