package logistics_management_engine.common;

public enum AccountStatus {
    NOT_ACTIVE("Not Active"),
    SUSPENDED("Suspended"),
    ACTIVE("Active"),
    DELETED("Deleted"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    ARCHIVED("Archived");

    private final String statusName;

    // Constructor to associate a String value with each status
    AccountStatus(String statusName) {
        this.statusName = statusName;
    }

    // Getter to retrieve the status's name
    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return this.statusName;
    }
}
