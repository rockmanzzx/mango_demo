package org.example.mango_backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.mango_backup"})
public class MangoBackupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoBackupApplication.class, args);
    }

}
