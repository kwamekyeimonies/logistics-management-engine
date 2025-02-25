package logistics_management_engine.service.product_category;

import logistics_management_engine.dto.CreateProductCategoryRequest;
import logistics_management_engine.dto.UpdateProductCategoryRequest;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IProductCategoryService {
    ProductCategory createProductCategory(CreateProductCategoryRequest dto, Employee employee);
    ProductCategory updateProductCategory(UUID categoryId, UpdateProductCategoryRequest dto, Employee employee);
    List<ProductCategory> getAllProductCategories();
    List<ProductCategory> getProductCategoriesByEmployee(Employee employee);
    ProductCategory getProductCategoryById(UUID categoryId);
    ResponseEntity<Map<String, String>> deleteProductCategory(UUID categoryId, Employee employee);
}
