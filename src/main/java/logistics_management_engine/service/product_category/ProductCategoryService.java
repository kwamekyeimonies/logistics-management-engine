package logistics_management_engine.service.product_category;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import logistics_management_engine.dto.CreateProductCategoryRequest;
import logistics_management_engine.dto.CreateProductCategoryResponse;
import logistics_management_engine.dto.UpdateProductCategoryRequest;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductCategory;
import logistics_management_engine.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryRequest request, Employee employee) {
        try {
            ProductCategory productCategory = ProductCategory.builder()
                    .categoryId(UUID.randomUUID())
                    .category_name(request.getCategoryName())
                    .description(request.getDescription())
                    .status("ACTIVE")
                    .createdDate(LocalDateTime.now())
                    .updatedDate(null)
                    .createdByEmployee(employee)
                    .updatedBy(null)
                    .taxRate(request.getTaxRate())
                    .discountRules(request.getDiscountRules())
                    .keywords(request.getKeywords())
                    .displayOrder(request.getDisplayOrder())
                    .isDeleted(false)
                    .deletedDate(null)
                    .build();

            // Save the entity
            ProductCategory savedCategory = productCategoryRepository.save(productCategory);

            // Map the entity to the DTO
            return CreateProductCategoryResponse.builder()
                    .categoryId(savedCategory.getCategoryId())
                    .categoryName(savedCategory.getCategory_name())
                    .description(savedCategory.getDescription())
                    .status(savedCategory.getStatus())
                    .createdDate(savedCategory.getCreatedDate())
                    .updatedDate(savedCategory.getUpdatedDate())
                    .updatedBy(savedCategory.getUpdatedBy())
                    .taxRate(savedCategory.getTaxRate())
                    .discountRules(savedCategory.getDiscountRules())
                    .keywords(savedCategory.getKeywords())
                    .displayOrder(savedCategory.getDisplayOrder())
                    .build();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating product category", e);
        }
    }

    @Override
    public ProductCategory updateProductCategory(UUID categoryId, UpdateProductCategoryRequest request, Employee employee) {
        try {
            return productCategoryRepository.findById(categoryId)
                    .map(existingCategory -> {
                        existingCategory.setCategory_name(request.getCategoryName());
                        existingCategory.setDescription(request.getDescription());
                        existingCategory.setUpdatedDate(LocalDateTime.now());
                        existingCategory.setUpdatedBy(employee.getStaff_id());
                        return productCategoryRepository.save(existingCategory);
                    })
                    .orElseThrow(() -> new RuntimeException("Product Category not found"));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating product category", e);
        }
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        try {
            return productCategoryRepository.findByIsDeleted(false);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product categories", e);
        }
    }

    @Override
    public List<ProductCategory> getProductCategoriesByEmployee(Employee employee) {
        try {
            return productCategoryRepository.findByCreatedByEmployeeAndIsDeleted(employee, false);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product categories by employee", e);
        }
    }

    @Override
    public ProductCategory getProductCategoryById(UUID categoryId) {
        try {
            ProductCategory productCategory= productCategoryRepository.findByCategoryIdAndIsDeleted(categoryId, false)
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for ID: " + categoryId));
            return productCategory;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product category", e);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteProductCategory(UUID categoryId, Employee employee) {
        try {
            ProductCategory productCategory = productCategoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Product Category not found"));

            productCategory.setIsDeleted(true);
            productCategory.setDeletedDate(LocalDateTime.now());
            productCategory.setUpdatedBy(employee.getStaff_id());
            productCategoryRepository.save(productCategory);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Product Category deleted successfully");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting product category", e);
        }
    }
}
