package logistics_management_engine.common;

public enum EmployeeRoles {
    MANAGER("Manager"),
    SUPERVISOR("Supervisor"),
    SALES("Sales"),
    ADMINISTRATOR("Administrator");

    private final String roleName;

    // Constructor to associate a String value with each role
    EmployeeRoles(String roleName) {
        this.roleName = roleName;
    }

    // Getter to retrieve the role's name
    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
