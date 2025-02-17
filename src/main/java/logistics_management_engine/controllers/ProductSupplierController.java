package logistics_management_engine.controllers;

import jakarta.validation.Valid;
import logistics_management_engine.dto.CreateProductSupplierRequest;
import logistics_management_engine.dto.CreateProductSupplierResponse;
import logistics_management_engine.middleware.RequiresRole;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductSupplier;
import logistics_management_engine.service.product_supplier.ProductSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/product-supplier")
@RequiredArgsConstructor
public class ProductSupplierController {
    private final ProductSupplierService productSupplierService;
    @PostMapping
    @RequiresRole({"SUPERVISOR", "Administrator", "MANAGER"})
    public @ResponseBody ResponseEntity<CreateProductSupplierResponse> addProductSupplier(
            @Valid @RequestBody CreateProductSupplierRequest productSupplierRequest,
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();

        CreateProductSupplierResponse productSupplier = productSupplierService.addProductSupplier(productSupplierRequest, employee);

        return ResponseEntity.ok(productSupplier);
    }
}
