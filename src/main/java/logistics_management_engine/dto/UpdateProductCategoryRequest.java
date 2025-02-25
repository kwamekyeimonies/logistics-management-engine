package logistics_management_engine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdateProductCategoryRequest {
    private String categoryName;
    private String description;
}
