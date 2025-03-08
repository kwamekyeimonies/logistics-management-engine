package logistics_management_engine.repository;

import jakarta.transaction.Transactional;
import logistics_management_engine.models.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, UUID> {
    @Query("SELECT s FROM ProductSupplier s WHERE s.createdByEmployee.id = :employeeId")
    List<ProductSupplier> findSuppliersByEmployee(@Param("employeeId") UUID employeeId);

    @Query("SELECT s FROM ProductSupplier s WHERE s.deleted = false")
    List<ProductSupplier> findAllActiveSuppliers();

    @Transactional
    @Modifying
    @Query("UPDATE ProductSupplier s SET s.deleted = true, s.deletedAt = CURRENT_TIMESTAMP WHERE s.Id = :Id")
    void softDeleteSupplier(@Param("Id") UUID Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductSupplier s WHERE s.Id = :Id")
    void hardDeleteSupplier(@Param("Id") UUID Id);
}
