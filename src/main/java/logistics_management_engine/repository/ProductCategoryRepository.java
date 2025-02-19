package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import logistics_management_engine.models.Product;
import logistics_management_engine.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    // Fetch category by ID
    ProductCategory findProductCategoryByCategoryId(UUID categoryId);
    List<ProductCategory> findByCreatedByEmployee(Employee createdByEmployee);
    // Fetch categories by status
    List<ProductCategory> findByStatus(String status);
}
