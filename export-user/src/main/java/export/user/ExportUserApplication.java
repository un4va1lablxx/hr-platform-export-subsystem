package export.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExportUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportUserApplication.class, args);
    }

}
