package logistics_management_engine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreateProductCategoryResponse {
    private UUID categoryId;
    private String categoryName;
    private String description;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedBy;
    private Double taxRate;
    private String discountRules;
    private String keywords;
    private Integer displayOrder;
}
