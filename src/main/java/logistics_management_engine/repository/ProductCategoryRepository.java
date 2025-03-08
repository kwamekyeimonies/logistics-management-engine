package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import logistics_management_engine.models.Product;
import logistics_management_engine.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    @Query("SELECT c FROM ProductCategory c WHERE c.createdByEmployee.id = :employeeId")
    List<ProductCategory> findCategoriesByEmployee(@Param("employeeId") UUID employeeId);

    @Query("SELECT c FROM ProductCategory c WHERE c.isDeleted = false")
    List<ProductCategory> findAllActiveCategories();

    @Transactional
    @Modifying
    @Query("UPDATE ProductCategory c SET c.isDeleted = true, c.deletedDate = CURRENT_TIMESTAMP WHERE c.Id = :categoryId")
    void softDeleteCategory(@Param("Id") UUID Id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductCategory c WHERE c.Id = :Id")
    void hardDeleteCategory(@Param("Id") UUID Id);
}