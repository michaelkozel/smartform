package com.example.smartform;

import com.example.smartform.services.KopidlnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartformApplication implements CommandLineRunner {

    @Autowired
    private KopidlnoService kopidlnoService;

    public static void main(String[] args) {
        SpringApplication.run(SmartformApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kopidlnoService.fetchAndStoreData();
    }

}
