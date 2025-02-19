package logistics_management_engine.service.auth;


import logistics_management_engine.common.Messages;
import logistics_management_engine.config.JwtUtil;
import logistics_management_engine.dto.LoginResponse;
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
    private final AuthenticationManager authenticationManager;
    private final CustomeUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final EmployeeRepository employeeRepository;

    public LoginResponse authenticate(String staff_id, String password) {

        Employee employee = employeeRepository.findEmployeeByStaff_id(staff_id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(staff_id, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(staff_id);

        String access_token= jwtUtil.generateAccessToken(userDetails, employee);
        String refresh_token= jwtUtil.generateRefreshToken(userDetails, employee);

        LoginResponse loginResponse = LoginResponse.builder()
                .access_token(access_token)
                .refresh_token(refresh_token)
                .email(employee.getEmail())
                .first_name(employee.getFirst_name())
                .last_name(employee.getLast_name())
                .phone_number(employee.getPhone_number())
                .id(employee.getId())
                .staff_id(employee.getStaff_id())
                .profile_picture(employee.getProfile_picture())
                .message(Messages.LOGIN_SUCCESSFULLY)
                .role(employee.getRole())
                .build();

        return loginResponse;
    }
}
