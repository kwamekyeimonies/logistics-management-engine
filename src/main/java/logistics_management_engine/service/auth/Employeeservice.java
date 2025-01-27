package logistics_management_engine.service.auth;

import logistics_management_engine.dto.*;
import logistics_management_engine.models.Employee;
import logistics_management_engine.repository.EmployeeRepository;
import logistics_management_engine.utils.EmployeeIdUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
@Data
public class Employeeservice implements IEmployeeService {

    private  EmployeeRepository _employeeRepository;
    private  PasswordEncoder _passwordEncoder;

    public Employeeservice(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        _employeeRepository = employeeRepository;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public CompletableFuture<CreateAccountResponse> CreateSupervisorAccount(CreateAccountRequest createAccountRequest) {
        return CompletableFuture.supplyAsync(()->{
            try {
                Optional<Employee> employee = _employeeRepository.findEmployeeByEmail(createAccountRequest.getEmail().toLowerCase());
                if (employee.isPresent()) {
                    throw new Exception("Email already exist: "+createAccountRequest.getEmail());
                }
                Optional<Employee> employee_phoneNumber = _employeeRepository.findEmployeeByPhoneNumber(createAccountRequest.getPhone_number());
                if (employee_phoneNumber.isPresent()) {
                    throw new Exception("Phone number already exist: "+createAccountRequest.getPhone_number());
                }
                Employee newEmployee = Employee.builder()
                        .email(createAccountRequest.getEmail())
                        .phone_number(createAccountRequest.getPhone_number())
                        .created_at(ZonedDateTime.now())
                        .updated_at(ZonedDateTime.now())
                        .is_deleted(false)
                        .first_name(createAccountRequest.getFirst_name())
                        .last_name(createAccountRequest.getLast_name())
                        .staff_id(EmployeeIdUtil.generateSupervisorId().toLowerCase())
                        .address(createAccountRequest.getAddress())
                        .date_of_birth(createAccountRequest.getDate_of_birth())
                        .profile_picture(createAccountRequest.getProfile_picture())
                        .identification_number(createAccountRequest.getIdentification_number())
                        .identification_card(createAccountRequest.getIdentification_card())
                        .password(_passwordEncoder.encode(createAccountRequest.getPassword()))
                        .id(UUID.randomUUID().toString())
                        .build();

                _employeeRepository.save(newEmployee);

                return CreateAccountResponse.builder()
                        .email(createAccountRequest.getEmail())
                        .phone_number(createAccountRequest.getPhone_number())
                        .first_name(createAccountRequest.getFirst_name())
                        .last_name(createAccountRequest.getLast_name())
                        .staff_id(newEmployee.getStaff_id())
                        .about_me(newEmployee.getAbout_me())
                        .address(newEmployee.getAddress())
                        .id(newEmployee.getId())
                        .profile_picture(newEmployee.getProfile_picture())
                        .identification_number(newEmployee.getIdentification_number())
                        .identification_card(newEmployee.getIdentification_card())
                        .staff_id(newEmployee.getStaff_id())
                        .created_at(newEmployee.getCreated_at())
                        .build();


            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
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
