package logistics_management_engine.controllers;

import logistics_management_engine.dto.CreateAccountRequest;
import logistics_management_engine.dto.CreateAccountResponse;
import logistics_management_engine.dto.ErrorResponse;
import logistics_management_engine.dto.LoginRequest;
import logistics_management_engine.service.auth.AuthService;
import logistics_management_engine.service.auth.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/employee")
public class Employee_Controller {
    private final IEmployeeService employeeService;
    private final AuthService authService;

    @PostMapping("/createSupervisorAccount")
    public @ResponseBody ResponseEntity<CreateAccountResponse> createSupervisorAccount(
            @ModelAttribute CreateAccountRequest createAccountRequest) {
        try {
            CreateAccountResponse response = employeeService.CreateSupervisorAccount(createAccountRequest).get();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            CreateAccountResponse errorResponse = CreateAccountResponse.builder()
                    .message("Error: " + e.getMessage())
                    .build();

            // Return a 500 error with the error response
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user and generate JWT token
            String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            // Return the JWT token as the response
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Return an error if authentication fails
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
