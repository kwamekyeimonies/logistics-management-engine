package logistics_management_engine.service.auth;

import logistics_management_engine.common.AccountStatus;
import logistics_management_engine.common.EmployeeRoles;
import logistics_management_engine.common.Messages;
import logistics_management_engine.dto.*;
import logistics_management_engine.models.Employee;
import logistics_management_engine.repository.EmployeeRepository;
import logistics_management_engine.service.aws_service.IAWSService;
import logistics_management_engine.utils.EmployeeIdUtil;
import logistics_management_engine.utils.FileUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class Employeeservice implements IEmployeeService {

    private final  EmployeeRepository _employeeRepository;
    private final  PasswordEncoder _passwordEncoder;
    private final IAWSService _awsService;

    @Override
    public CompletableFuture<CreateAccountResponse> CreateEmployeeAccount(CreateAccountRequest createAccountRequest) {
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

                EmployeeRoles role = validateRole(createAccountRequest.getRole());

                // Check if the files are images
                if (!FileUtils.isImageFile(Objects.requireNonNull(createAccountRequest.getProfile_picture().getContentType()))) {
                    throw new Exception("Profile picture must be an image file (JPEG, PNG, GIF, BMP).");
                }

                if (!FileUtils.isImageFile(Objects.requireNonNull(createAccountRequest.getIdentification_card_front().getContentType()))) {
                    throw new Exception("Identification card front must be an image file (JPEG, PNG, GIF, BMP).");
                }

                if (!FileUtils.isImageFile(Objects.requireNonNull(createAccountRequest.getIdentification_card_back().getContentType()))) {
                    throw new Exception("Identification card back must be an image file (JPEG, PNG, GIF, BMP).");
                }

                // Check file sizes
                if (!FileUtils.isFileSizeValid(createAccountRequest.getProfile_picture().getSize())) {
                    throw new Exception("Profile picture size exceeds 5MB. Please upload a smaller image.");
                }

                if (!FileUtils.isFileSizeValid(createAccountRequest.getIdentification_card_front().getSize())) {
                    throw new Exception("Identification card front size exceeds 5MB. Please upload a smaller image.");
                }

                if (!FileUtils.isFileSizeValid(createAccountRequest.getIdentification_card_back().getSize())) {
                    throw new Exception("Identification card back size exceeds 5MB. Please upload a smaller image.");
                }

                // Dynamically generate file extensions
                String profile_pic_extension = FileUtils.get_file_extension(createAccountRequest.getProfile_picture().getContentType());
                String id_card_front_extension = FileUtils.get_file_extension(createAccountRequest.getIdentification_card_front().getContentType());
                String id_card_back_extension = FileUtils.get_file_extension(createAccountRequest.getIdentification_card_back().getContentType());


                String profile_picture_url = _awsService.uploadFileToBucket(
                        "mydevfilestore",
                        "profile_pictures/" + createAccountRequest.getEmail() + "-profile" +profile_pic_extension,
                        createAccountRequest.getProfile_picture().getSize(),
                        createAccountRequest.getProfile_picture().getContentType(),
                        createAccountRequest.getProfile_picture().getInputStream()
                );

                // Upload identification card front to S3
                String id_card_front_url = _awsService.uploadFileToBucket(
                        "mydevfilestore",
                        "id_cards/" + createAccountRequest.getEmail() + "-id-front"+id_card_front_extension,
                        createAccountRequest.getIdentification_card_front().getSize(),
                        createAccountRequest.getIdentification_card_front().getContentType(),
                        createAccountRequest.getIdentification_card_front().getInputStream()
                );

                // Upload identification card back to S3
                String id_card_back_url = _awsService.uploadFileToBucket(
                        "mydevfilestore",
                        "id_cards/" + createAccountRequest.getEmail() + "-id-back" + id_card_back_extension,
                        createAccountRequest.getIdentification_card_back().getSize(),
                        createAccountRequest.getIdentification_card_back().getContentType(),
                        createAccountRequest.getIdentification_card_back().getInputStream()
                );
                Employee newEmployee = Employee.builder()
                        .email(createAccountRequest.getEmail().toLowerCase())
                        .phone_number(createAccountRequest.getPhone_number())
                        .about_me(createAccountRequest.getAbout_me())
                        .created_at(ZonedDateTime.now())
                        .updated_at(ZonedDateTime.now())
                        .is_deleted(false)
                        .role(role.getRoleName())
                        .status(AccountStatus.NOT_ACTIVE.getStatusName())
                        .first_name(createAccountRequest.getFirst_name())
                        .user_name(createAccountRequest.getFirst_name().toLowerCase())
                        .last_name(createAccountRequest.getLast_name())
                        .staff_id(EmployeeIdUtil.generateSupervisorId().toLowerCase())
                        .address(createAccountRequest.getAddress())
                        .date_of_birth(createAccountRequest.getDate_of_birth())
                        .profile_picture(profile_picture_url)
                        .identification_number(createAccountRequest.getIdentification_number())
                        .identification_card_front(id_card_front_url)
                        .identification_card_back(id_card_back_url)
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
                        .staff_id(newEmployee.getStaff_id())
                        .message(Messages.ACCOUNT_CREATED_SUCCESSFULLY)
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

    private EmployeeRoles validateRole(String roleName) throws Exception {
        try {
            return EmployeeRoles.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Invalid role: " + roleName);
        }
    }
}
