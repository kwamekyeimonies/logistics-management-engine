package logistics_management_engine.controllers;

import logistics_management_engine.common.Messages;
import logistics_management_engine.config.JwtUtil;
import logistics_management_engine.dto.*;
import logistics_management_engine.models.Employee;
import logistics_management_engine.models.EmployeeRole;
import logistics_management_engine.repository.EmployeeRepository;
import logistics_management_engine.repository.EmployeeRoleRepository;
import logistics_management_engine.service.auth.AuthService;
import logistics_management_engine.service.auth.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/employee")
public class Employee_Controller {
    private final IEmployeeService employeeService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeRoleRepository employeeRoleRepository;

    @PostMapping("/createSupervisorAccount")
    public @ResponseBody ResponseEntity<CreateAccountResponse> createSupervisorAccount(
            @ModelAttribute CreateAccountRequest createAccountRequest) {
        try {
            CreateAccountResponse response = employeeService.CreateEmployeeAccount(createAccountRequest).get();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            CreateAccountResponse errorResponse = CreateAccountResponse.builder()
                    .message("Error: " + e.getMessage())
                    .build();

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = authService.authenticate(loginRequest.getStaff_id(), loginRequest.getPassword());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            LoginResponse errorResponse = LoginResponse.builder().message("Error :" + e.getMessage()).build();
            return ResponseEntity.status(401).body(errorResponse);
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            if (jwtUtil.isTokenExpired(refreshTokenRequest.getRefresh_token())) {
                return ResponseEntity.status(401).body(new RefreshTokenResponse("","","Refresh token is expired"));
            }
            String username = jwtUtil.extractUsername(refreshTokenRequest.getRefresh_token());
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Employee employee = employeeRepository.findEmployeeByStaff_id(username)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            String newAccessToken = jwtUtil.generateAccessToken(userDetails, employee);

            String newRefreshToken = jwtUtil.generateRefreshToken(userDetails, employee);

            RefreshTokenResponse response = new RefreshTokenResponse(newAccessToken, newRefreshToken, Messages.REFRESH_TOKEN_CREATED_SUCCESSFULLY);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new RefreshTokenResponse("","","Invalid refresh token"));
        }
    }

    @GetMapping("/roles")
    public @ResponseBody List<EmployeeRole> getAllRoles() {
        return employeeRoleRepository.findAll();
    }


}
