package logistics_management_engine.repository;

import logistics_management_engine.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.createdByEmployee.id = :employeeId")
    List<Product> findProductsByEmployee(@Param("employeeId") String employeeId);

    @Query("SELECT p FROM Product p WHERE p.status != 'DELETED'")
    List<Product> findAllActiveProducts();

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'DELETED' WHERE p.Id = :Id")
    void softDeleteProduct(@Param("Id") UUID Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.Id = :Id")
    void hardDeleteProduct(@Param("Id") UUID Id);

}
