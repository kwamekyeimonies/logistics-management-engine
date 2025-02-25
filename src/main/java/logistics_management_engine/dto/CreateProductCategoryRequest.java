package logistics_management_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateProductCategoryRequest {
    private String categoryName;
    private String description;
    private Double taxRate;
    private String discountRules;
    private String keywords;
    private Integer displayOrder;
}
