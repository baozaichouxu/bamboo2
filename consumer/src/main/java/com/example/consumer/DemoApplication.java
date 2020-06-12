package com.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker//开启断路器功能
@EnableEurekaClient
@SpringBootApplication
//以上三个也可以被@SpringCloudApplication代替
public class DemoApplication {
	@Bean
	@LoadBalanced
	/**
	 * LoadBalanced 开启负载均衡
	 */
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
