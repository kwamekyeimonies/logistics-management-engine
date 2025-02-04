package logistics_management_engine.service.auth;

import jakarta.annotation.PostConstruct;
import logistics_management_engine.common.EmployeeRoles;
import logistics_management_engine.models.EmployeeRole;
import logistics_management_engine.repository.EmployeeRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeRoleService {
    private final EmployeeRoleRepository employeeRoleRepository;

    @PostConstruct
    public void initRoles() {
        // Check if roles already exist in the database
        for (EmployeeRoles role : EmployeeRoles.values()) {
            employeeRoleRepository.findByRoleName(role).orElseGet(() -> {
                EmployeeRole employeeRole = new EmployeeRole(role);
                return employeeRoleRepository.save(employeeRole);
            });
        }
    }

}
