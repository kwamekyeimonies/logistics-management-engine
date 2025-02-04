package logistics_management_engine.service.auth;

import logistics_management_engine.models.Employee;
import logistics_management_engine.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String staff_id) throws UsernameNotFoundException {
        // Try to find the user by staffId, username, or phone_number
        Employee employee = employeeRepository.findEmployeeByStaff_id(staff_id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with staff_id: " + staff_id));

        return new User(employee.getStaff_id(), employee.getPassword(), Collections.emptyList());
    }
}
