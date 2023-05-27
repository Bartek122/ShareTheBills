package pl.cholewa.sharethebills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShareTheBillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareTheBillsApplication.class, args);
    }

}
