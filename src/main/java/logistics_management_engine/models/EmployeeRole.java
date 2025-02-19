package logistics_management_engine.models;

import jakarta.persistence.*;
import logistics_management_engine.common.EmployeeRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private EmployeeRoles roleName;

    public EmployeeRole(EmployeeRoles roleName) {
        this.roleName = roleName;
    }

}
