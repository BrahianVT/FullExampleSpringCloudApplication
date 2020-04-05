package com.example.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 *Config Service for more information check:
 * https://github.com/BrahianVT/FullExampleSpringCloudApplication/wiki/8.--Hystrix-dashboard
 *
 * @author  Brahian VT
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardApplication.class, args);
	}

}
