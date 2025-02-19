package logistics_management_engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateProductSupplierRequest {
    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    private String contactName;

    @Email(message = "Invalid email format")
    private String contactEmail;

    private String contactPhone;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;
}
