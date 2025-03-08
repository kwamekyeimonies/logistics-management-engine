package logistics_management_engine.repository;

import logistics_management_engine.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAll();
    List<Category> findAllByIsDeletedFalse();
    List<Category> findAllByCreatedByEmployeeId(UUID employeeId);
    List<Category> findAllByCreatedByEmployeeIdAndIsDeletedFalse(UUID employeeId);
    @Modifying
    @Query("DELETE FROM Category c WHERE c.id = :categoryId")
    void hardDeleteById(@Param("categoryId") UUID categoryId);
    @Modifying
    @Query("UPDATE Category c SET c.isDeleted = true, c.deletedDate = CURRENT_TIMESTAMP WHERE c.id = :categoryId")
    void softDeleteById(@Param("categoryId") UUID categoryId);
    Optional<Category> findByCategoryName(String categoryName);
    Category findByCategoryNameAndCreatedByEmployeeId(String categoryName, UUID employeeId);
    Optional<Category> findByCategoryNameAndCreatedByEmployeeIdAndIsDeletedFalse(String categoryName, UUID employeeId);
}
