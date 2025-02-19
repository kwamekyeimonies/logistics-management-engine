package logistics_management_engine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductSupplierResponse {

    private UUID supplierId;
    private String supplierName;
    private String message;
}
