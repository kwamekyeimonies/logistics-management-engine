package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import logistics_management_engine.models.Product;
import logistics_management_engine.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    ProductCategory findProductCategoryByCategoryId(UUID categoryId);
    List<ProductCategory> findByCreatedByEmployee(Employee createdByEmployee);
    List<ProductCategory> findByStatus(String status);
    List<ProductCategory> findByIsDeleted(Boolean isDeleted);
    List<ProductCategory>findByCreatedByEmployeeAndIsDeleted(Employee createdByEmployee, Boolean isDeleted);
    Optional<ProductCategory> findByIdAndIsDeleted(UUID id, Boolean isDeleted);

}
