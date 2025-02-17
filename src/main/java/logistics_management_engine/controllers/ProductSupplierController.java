package logistics_management_engine.controllers;

import jakarta.validation.Valid;
import logistics_management_engine.dto.CreateProductSupplierRequest;
import logistics_management_engine.dto.CreateProductSupplierResponse;
import logistics_management_engine.dto.UpdateProductSupplierResponse;
import logistics_management_engine.middleware.RequiresRole;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductSupplier;
import logistics_management_engine.service.product_supplier.ProductSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1.0/product-supplier")
@RequiredArgsConstructor
public class ProductSupplierController {
    private final ProductSupplierService productSupplierService;
    @PostMapping
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public @ResponseBody ResponseEntity<CreateProductSupplierResponse> addProductSupplier(
            @Valid @RequestBody CreateProductSupplierRequest productSupplierRequest,
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();

        CreateProductSupplierResponse productSupplier = productSupplierService.addProductSupplier(productSupplierRequest, employee);

        return ResponseEntity.ok(productSupplier);
    }

    @PutMapping("/{productSupplierId}")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public @ResponseBody ResponseEntity<UpdateProductSupplierResponse> updateProductSupplier(
            @PathVariable UUID productSupplierId,
            @Valid @RequestBody CreateProductSupplierRequest productSupplierRequest,
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();

        UpdateProductSupplierResponse updatedProductSupplier =
                productSupplierService.updateProductSupplier(productSupplierId, productSupplierRequest, employee);

        return ResponseEntity.ok(updatedProductSupplier);
    }

    @GetMapping
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public @ResponseBody ResponseEntity<List<ProductSupplier>> getAllProductSuppliersCreatedByEmployee(
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();
        List<ProductSupplier> productSuppliers = productSupplierService.getAllProductSuppliersCreateByEmployee(employee);
        return ResponseEntity.ok(productSuppliers);
    }

    @GetMapping("/all")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public @ResponseBody ResponseEntity<List<ProductSupplier>> getAllProductSuppliers(
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();
        List<ProductSupplier> productSuppliers = productSupplierService.getAllProductSuppliers();
        return ResponseEntity.ok(productSuppliers);
    }

    @GetMapping("/{productSupplierId}")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public @ResponseBody ResponseEntity<ProductSupplier> getProductSupplier(
            @PathVariable UUID productSupplierId,
            Authentication authentication) {
        Employee employee = (Employee) authentication.getPrincipal();
        ProductSupplier productSupplier = productSupplierService.getProductSupplier(employee, productSupplierId);
        return ResponseEntity.ok(productSupplier);
    }
}
