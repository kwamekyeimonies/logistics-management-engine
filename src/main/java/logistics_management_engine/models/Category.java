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
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "UUID")
    private UUID id;


    @Column(name = "categoryName", nullable = false)
    private String categoryName;

    @Version
    @Column(name = "version")
    private Instant version;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "updatedBy")
    private String updatedBy;

    @Column(name = "taxRate")
    private Double taxRate;

    @Column(name = "discountRules")
    private String discountRules;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "displayOrder")
    private Integer displayOrder;

    @Column(name = "isDeleted")
    @JsonIgnore
    private Boolean isDeleted;

    @Column(name = "deletedDate")
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "createdByEmployeeId", nullable = false)
    private Employee createdByEmployee;


}
