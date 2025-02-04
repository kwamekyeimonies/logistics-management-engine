package logistics_management_engine.repository;

import logistics_management_engine.common.EmployeeRoles;
import logistics_management_engine.models.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {
    Optional<EmployeeRole> findByRoleName(EmployeeRoles roleName);

}
