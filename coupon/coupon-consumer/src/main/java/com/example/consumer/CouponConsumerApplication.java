package com.example.consumer;

import com.example.core.CouponCoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CouponCoreConfiguration.class)
@SpringBootApplication
public class CouponConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponConsumerApplication.class, args);
	}

}
