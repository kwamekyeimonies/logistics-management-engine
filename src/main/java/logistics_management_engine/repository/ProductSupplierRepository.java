package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import logistics_management_engine.models.ProductCategory;
import logistics_management_engine.models.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, UUID> {
    ProductSupplier findBySupplierId(UUID supplierId);
    List<ProductSupplier> findByCreatedByEmployee(Employee createdByEmployee);
    List<ProductSupplier> findByStatus(String status);
    List<ProductSupplier> findByCountry(String country);
    List<ProductSupplier> findBySupplierNameContainingIgnoreCase(String supplierName);
}
