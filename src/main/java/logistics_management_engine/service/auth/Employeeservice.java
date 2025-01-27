package logistics_management_engine.service.auth;

import logistics_management_engine.dto.*;
import logistics_management_engine.repository.EmployeeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Data
public class Employeeservice implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public CreateAccountResponse CreateSupervisorAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }

    @Override
    public LoginResponse LoginUserAccount(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ForgetPasswordResponse ForgetUserAccount(ForgetPasswordRequest forgetPasswordRequest) {
        return null;
    }

    @Override
    public VerifyAccountRequest VerifyUserAccount(VerifyAccountRequest verifyAccountRequest) {
        return null;
    }
}
