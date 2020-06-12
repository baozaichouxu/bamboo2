package com.example.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="helloFallback")
    public String HelloService(){
        return restTemplate.getForEntity("http://pruducer/hello",String.class ).getBody();
    }

    public String helloFallback(){
        return "error";
    }
}
