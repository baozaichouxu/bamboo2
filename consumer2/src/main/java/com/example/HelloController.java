package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer2",method = RequestMethod.GET)
    public String helloConsumer() {
        //return "string";
        //访问地址是服务名，而不是具体的地址
        return restTemplate.getForEntity("http://producer/hello",String.class).getBody();
    }
}
