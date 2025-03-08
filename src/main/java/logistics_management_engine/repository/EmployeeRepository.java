package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findEmployeeByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.staffId = :staffId")
    Optional<Employee> findEmployeeByStaffId(@Param("staffId") String staffId);

    @Query("SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber")
    Optional<Employee> findEmployeeByPhoneNumber(@Param("phoneNumber") String phoneNumber);

//    @Query("SELECT e FROM Employee e WHERE e.staffId = :identifier OR e.username = :identifier OR e.phoneNumber = :identifier")
//    Optional<Employee> findEmployeeByStaffIdOrUsernameOrPhoneNumber(@Param("identifier") String identifier);
}
