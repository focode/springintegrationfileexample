package com.adp.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@Configuration
//@ComponentScan(basePackages = {"com.adp.org"})
@ImportResource("classpath:SpringContext.xml")
@SpringBootApplication
public class VehicleidentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleidentifierApplication.class, args);
	}

}
