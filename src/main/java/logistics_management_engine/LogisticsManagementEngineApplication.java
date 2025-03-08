package logistics_management_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LogisticsManagementEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsManagementEngineApplication.class, args);
    }

}
