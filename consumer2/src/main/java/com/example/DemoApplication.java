package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * EnableDiscoveryClient 注册为客户的，获取发现功能,如果用的是Eureka，推荐用EnableEurekaClient
 */
@EnableEurekaClient
@SpringBootApplication
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
