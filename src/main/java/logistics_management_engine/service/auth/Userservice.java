package logistics_management_engine.service.auth;

import logistics_management_engine.dto.*;
import logistics_management_engine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;

@Service
@RequiredArgsConstructor
public class Userservice implements IUserService{
    private final UserRepository userRepository;

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
