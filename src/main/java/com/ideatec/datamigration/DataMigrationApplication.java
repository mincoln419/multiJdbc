package com.ideatec.datamigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataMigrationApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DataMigrationApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}

}
