package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import logistics_management_engine.models.Product;
import logistics_management_engine.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByProductId(UUID productId);
    List<Product> findByCreatedByEmployee(Employee createdByEmployee);
    List<Product> findByCategory(ProductCategory category);
    List<Product> findBySupplierSupplierId(UUID supplierId);
    List<Product> findByStatus(String status);
    Product findBySku(String sku);
    List<Product> findByProductNameContainingIgnoreCase(String productName);
}
