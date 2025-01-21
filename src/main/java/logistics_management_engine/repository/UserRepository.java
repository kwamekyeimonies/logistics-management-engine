package logistics_management_engine.repository;

import logistics_management_engine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    User findUserByStaffId(String staffId);
    User findUserByPhonenumber(String phone);
    List<User> fetchAllUsers();
}
