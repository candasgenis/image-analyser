package com.candasgenis.imageanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.candasgenis.springbootkafkaproducer")
public class ImageAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageAnalyserApplication.class, args);
	}

}
