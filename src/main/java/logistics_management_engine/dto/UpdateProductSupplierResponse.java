package logistics_management_engine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class UpdateProductSupplierResponse {
    private UUID supplierId;
    private String supplierName;
    private String message;

}
