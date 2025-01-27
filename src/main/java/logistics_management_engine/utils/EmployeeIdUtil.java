package logistics_management_engine.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Random;


public class EmployeeIdUtil {
    private static final String SUPERVISOR_PREFIX = "SP";
    private static final String SALES_PREFIX = "SL";
    private static final int RANDOM_NUMBER_LENGTH = 8;

    private static String generateRandomEmployeeId() {
        Random random = new Random();
        int randomNumber = random.nextInt(900_000)+100_000;
        return String.valueOf(randomNumber);
    }

    public static String generateSupervisorId() {
        return SUPERVISOR_PREFIX + generateRandomEmployeeId();
    }

    public static String generateSalesId() {
        return SALES_PREFIX + generateRandomEmployeeId();
    }
}
