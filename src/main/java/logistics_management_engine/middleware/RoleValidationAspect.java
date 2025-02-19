package logistics_management_engine.middleware;

import logistics_management_engine.models.Employee;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class RoleValidationAspect {
    @Around("@annotation(requiresRole)") // Intercept methods annotated with @RequiresRole
    public Object validateRole(ProceedingJoinPoint joinPoint, RequiresRole requiresRole) throws Throwable {
        // Get the authenticated user from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) authentication.getPrincipal();

        // Normalize the user's role to lowercase (or uppercase)
        String userRole = employee.getRole().toLowerCase();

        // Normalize the allowed roles to lowercase (or uppercase)
        List<String> allowedRoles = Arrays.stream(requiresRole.value())
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        // Check if the user's role is in the list of allowed roles
        if (!allowedRoles.contains(userRole)) {
            throw new RuntimeException("Access denied. Required roles: " + Arrays.toString(requiresRole.value()));
        }

        // Proceed with the method execution if the role is valid
        return joinPoint.proceed();
    }
}
