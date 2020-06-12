package com.example.producer.controller;

import com.example.producer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class ProducerController {
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Value("${server.port}")
    public int port;

    /**
     *  服务发现客户端
     */
    @Autowired
    private DiscoveryClient client;

    /**
     * 服务注册
     */
    @Autowired
    private Registration registration;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloConsumer() throws InterruptedException {
      ServiceInstance instance = serviceInstance();
        String result = "/testBalance, host:port=" + instance.getUri()  + ", "
                + "service_id:" + instance.getServiceId();
        logger.info(result);
        //Hystrix默认超时时间为2000ms
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:"+sleepTime);
        Thread.sleep(sleepTime);
        return "helloProducer";
    }
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                if(itm.getPort() == port) {
                    return itm;
                }
            }
        }
       throw new RuntimeException("服务未找到");
    }

    @RequestMapping(value = "hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "hello:"+name;
    }

    @RequestMapping(value = "hello2",method = RequestMethod.GET)
    public User hello(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }

    @RequestMapping(value = "hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
    return "hello"+user.getName() +","+ user.getAge();
    }
}
