package logistics_management_engine.controllers;

import logistics_management_engine.dto.CreateAccountRequest;
import logistics_management_engine.dto.CreateAccountResponse;
import logistics_management_engine.dto.ErrorResponse;
import logistics_management_engine.service.auth.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/employee")
public class Employee_Controller {
    private final IEmployeeService employeeService;

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
}
