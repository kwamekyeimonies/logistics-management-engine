package logistics_management_engine.service.product_category;

import logistics_management_engine.dto.CreateProductCategoryRequest;
import logistics_management_engine.dto.CreateProductCategoryResponse;
import logistics_management_engine.dto.UpdateProductCategoryRequest;
import logistics_management_engine.models.Category;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IProductCategoryService {
    CreateProductCategoryResponse createProductCategory(CreateProductCategoryRequest dto, Employee employee);
    Category updateProductCategory(UUID categoryId, UpdateProductCategoryRequest dto, Employee employee);
    List<Category> getAllProductCategories();
    List<Category> getProductCategoriesByEmployee(Employee employee);
    Category getProductCategoryById(UUID categoryId);
    ResponseEntity<Map<String, String>> deleteProductCategory(UUID categoryId, Employee employee);
}
