package logistics_management_engine.controllers;


import jakarta.validation.Valid;
import logistics_management_engine.dto.CreateProductCategoryRequest;
import logistics_management_engine.dto.CreateProductCategoryResponse;
import logistics_management_engine.dto.UpdateProductCategoryRequest;
import logistics_management_engine.middleware.RequiresRole;
import logistics_management_engine.models.Category;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductCategory;
import logistics_management_engine.service.product_category.IProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/product-category")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final IProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<?> createProductCategory(
            @Valid @RequestBody CreateProductCategoryRequest dto,
            Authentication authentication) {
        try {
            Employee employee = (Employee) authentication.getPrincipal();
            CreateProductCategoryResponse productCategory = productCategoryService.createProductCategory(dto, employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(productCategory);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Database constraint violation: " + e.getMessage()));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error occurred: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred: " + e.getMessage()));
        }
    }


    @PutMapping("/{categoryId}")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public ResponseEntity<?> updateProductCategory(
            @PathVariable UUID categoryId,
            @RequestBody UpdateProductCategoryRequest dto,
            Authentication authentication) {
        try {
            Employee employee = (Employee) authentication.getPrincipal();
            Category updatedCategory = productCategoryService.updateProductCategory(categoryId, dto, employee);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProductCategories() {
        try {
            List<Category> categories = productCategoryService.getAllProductCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/employee")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public ResponseEntity<?> getProductCategoriesByEmployee(Authentication authentication) {
        try {
            Employee employee = (Employee) authentication.getPrincipal();
            List<Category> categories = productCategoryService.getProductCategoriesByEmployee(employee);
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getProductCategoryById(@PathVariable UUID categoryId) {
        try {
            Category productCategory = productCategoryService.getProductCategoryById(categoryId);
            return ResponseEntity.ok(productCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{categoryId}")
    @RequiresRole({"Supervisor", "Administrator", "Manager"})
    public ResponseEntity<?> deleteProductCategory(
            @PathVariable UUID categoryId,
            Authentication authentication) {
        try {
            Employee employee = (Employee) authentication.getPrincipal();
            return productCategoryService.deleteProductCategory(categoryId, employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
}
