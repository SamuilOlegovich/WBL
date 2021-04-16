package com.samuilolegovich.WBL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@EntityScan("db")
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"controller", "service", "repo"})
public class WblApplication {

	public static void main(String[] args) {
		SpringApplication.run(WblApplication.class, args);
	}

}
