package logistics_management_engine.repository;

import logistics_management_engine.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee e WHERE e.user_name = :userName")
    Optional<Employee> findEmployeeByUserName(@Param("userName") String userName);
    Optional<Employee> findEmployeeByEmail(String email);
    @Query("SELECT e FROM Employee e WHERE e.staff_id = :staffId")
    Optional<Employee> findEmployeeByStaff_id(@Param("staffId") String staffId);
    @Query("SELECT e FROM Employee e WHERE e.phone_number = :phoneNumber")
    Optional<Employee> findEmployeeByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query("SELECT e FROM Employee e WHERE e.staff_id = :identifier OR e.user_name = :identifier OR e.phone_number = :identifier")
    Optional<Employee> findEmployeeByStaffIdOrUserNameOrPhoneNumber(@Param("identifier") String identifier);
}
