package logistics_management_engine.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UpdateProductCategoryRequest {
    private String categoryName;
    private String description;
    private Double taxRate;
    private String discountRules;
    private Integer displayOrder;

}
