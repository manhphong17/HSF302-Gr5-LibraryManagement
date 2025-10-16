package com.groupTuAnh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibararyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibararyManagementApplication.class, args);
	}

}
