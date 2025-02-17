package logistics_management_engine.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", columnDefinition = "UUID")
    private UUID productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, columnDefinition = "UUID")
    private ProductCategory category;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, columnDefinition = "UUID")
    private ProductSupplier supplier;
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;
    @Column(name = "cost_price", nullable = false)
    private Double costPrice;
    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;
    @Column(name = "reorder_level")
    private Integer reorderLevel;
    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;
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
    @Column(name = "warranty_period")
    private String warrantyPeriod;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "tax_rate")
    private Double taxRate;
    @Column(name = "discount")
    private Double discount;

}
