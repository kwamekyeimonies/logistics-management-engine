package logistics_management_engine.service.auth;


import logistics_management_engine.config.JwtUtil;
import logistics_management_engine.models.Employee;
import logistics_management_engine.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private CustomeUserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    private EmployeeRepository employeeRepository;

    public String authenticate(String identifier, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(identifier, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(identifier);

        Employee employee = employeeRepository.findEmployeeByStaffIdOrUserNameOrPhoneNumber(identifier)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtUtil.generateToken(userDetails, employee.getRole(), employee.getProfile_picture(),employee.getPhone_number());
    }
}
