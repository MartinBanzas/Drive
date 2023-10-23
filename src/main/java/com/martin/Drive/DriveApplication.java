package com.martin.Drive;

import com.martin.Drive.service.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DriveApplication implements CommandLineRunner {

	@Resource
	FileStorageService fileStorageService;

	public static void main(String[] args) {
		SpringApplication.run(DriveApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//    fileStorageService.deleteAll();
		fileStorageService.init();
	}

}
