package logistics_management_engine.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateProductCategoryRequest {
    @NotBlank(message = "Category name is required")
    private String categoryName;
    private String description;
    private Double taxRate;
    private String discountRules;
    private String keywords;
    private Integer displayOrder;
}
