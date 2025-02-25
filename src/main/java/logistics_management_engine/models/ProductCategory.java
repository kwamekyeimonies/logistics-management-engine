package logistics_management_engine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id",columnDefinition = "UUID")
    private UUID categoryId;
    @Column(name = "category_name",nullable = false)
    private String category_name;
    @Column(name = "description")
    private String description;
    private String status;
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "created_by_employee_id", nullable = false, columnDefinition = "VARCHAR")
    private Employee createdByEmployee;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "tax_rate")
    private Double taxRate;
    @Column(name = "discount_rules")
    private String discountRules;
    @Column(name = "keywords")
    private String keywords;
    @Column(name = "display_order")
    private Integer displayOrder;
    @Column(name = "is_deleted")
    @JsonIgnore
    private Boolean isDeleted;
    @Column(name = "deleted_at")
    private LocalDateTime deletedDate;


}
