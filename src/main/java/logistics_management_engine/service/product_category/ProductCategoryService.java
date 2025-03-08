package logistics_management_engine.service.product_category;

import jakarta.persistence.EntityNotFoundException;
import logistics_management_engine.dto.CreateProductCategoryRequest;
import logistics_management_engine.dto.CreateProductCategoryResponse;
import logistics_management_engine.dto.UpdateProductCategoryRequest;
import logistics_management_engine.models.Category;
import logistics_management_engine.models.Employee;
import logistics_management_engine.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryRequest request, Employee employee) {
        try {

            Category productCategory = Category.builder()
                    .id(UUID.randomUUID()) // Ensure the field is 'id' (lowercase) as per the model
                    .categoryName(request.getCategoryName())
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
            Category savedCategory = categoryRepository.save(productCategory);

            // Map the entity to the DTO
            return CreateProductCategoryResponse.builder()
                    .categoryId(savedCategory.getId())
                    .categoryName(savedCategory.getCategoryName())
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category updateProductCategory(UUID categoryId, UpdateProductCategoryRequest request, Employee employee) {
        try {
            return categoryRepository.findById(categoryId)
                    .map(existingCategory -> {
                        existingCategory.setCategoryName(request.getCategoryName());
                        existingCategory.setDescription(request.getDescription());
                        existingCategory.setUpdatedDate(LocalDateTime.now());
                        existingCategory.setUpdatedBy(employee.getStaffId());
                        existingCategory.setTaxRate(request.getTaxRate());
                        existingCategory.setDiscountRules(request.getDiscountRules());
                        existingCategory.setDisplayOrder(request.getDisplayOrder());
                        return categoryRepository.save(existingCategory);
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for ID: " + categoryId));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating product category", e);
        }
    }

    @Override
    public List<Category> getAllProductCategories() {
        try {
            return categoryRepository.findAllByIsDeletedFalse(); // Fetch only non-deleted categories
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product categories", e);
        }
    }

    @Override
    public List<Category> getProductCategoriesByEmployee(Employee employee) {
        try {
            return categoryRepository.findAllByCreatedByEmployeeIdAndIsDeletedFalse(employee.getId()); // Fetch categories created by the employee and not deleted
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product categories by employee", e);
        }
    }

    @Override
    public Category getProductCategoryById(UUID categoryId) {
        try {
            return categoryRepository.findById(categoryId)
                    .filter(category -> !category.getIsDeleted()) // Ensure the category is not deleted
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for ID: " + categoryId));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product category", e);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteProductCategory(UUID categoryId, Employee employee) {
        try {
            Category productCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for ID: " + categoryId));

            // Soft delete the category
            productCategory.setIsDeleted(true);
            productCategory.setDeletedDate(LocalDateTime.now());
            productCategory.setUpdatedBy(employee.getStaffId());
            categoryRepository.save(productCategory);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Product Category deleted successfully");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting product category", e);
        }
    }


    public Category getProductCategoryByName(String categoryName) {
        try {
            return categoryRepository.findByCategoryName(categoryName)
                    .filter(category -> !category.getIsDeleted()) // Ensure the category is not deleted
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for name: " + categoryName));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product category by name", e);
        }
    }

    public Category getProductCategoryByNameAndEmployee(String categoryName, Employee employee) {
        try {
            return categoryRepository.findByCategoryNameAndCreatedByEmployeeIdAndIsDeletedFalse(categoryName, employee.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product Category not found for name: " + categoryName + " and employee ID: " + employee.getId()));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching product category by name and employee", e);
        }
    }
}