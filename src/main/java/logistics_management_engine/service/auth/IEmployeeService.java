package logistics_management_engine.service.auth;

import logistics_management_engine.dto.*;

import java.util.concurrent.CompletableFuture;

public interface IEmployeeService {
    CompletableFuture<CreateAccountResponse> CreateSupervisorAccount(CreateAccountRequest createAccountRequest);
    LoginResponse LoginUserAccount(LoginRequest loginRequest);
    ForgetPasswordResponse ForgetUserAccount(ForgetPasswordRequest forgetPasswordRequest);
    VerifyAccountRequest VerifyUserAccount(VerifyAccountRequest verifyAccountRequest);
}
