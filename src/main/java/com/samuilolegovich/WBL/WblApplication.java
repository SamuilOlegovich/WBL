package com.samuilolegovich.WBL;

import com.samuilolegovich.WBL.model.Start;
import com.samuilolegovich.WBL.model.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WblApplication {

	public static void main(String[] args) {
		SpringApplication.run(WblApplication.class, args);
//		new Start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		Test test = Test.getInstance();
//
//		test.tt();
	}

}
