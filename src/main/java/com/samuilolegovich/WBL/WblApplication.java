package com.samuilolegovich.WBL;

import com.samuilolegovich.WBL.model.Start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WblApplication {

	public static void main(String[] args) {
		SpringApplication.run(WblApplication.class, args);
		new Start();
	}

}
