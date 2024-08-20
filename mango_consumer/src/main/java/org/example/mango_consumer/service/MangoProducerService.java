package org.example.mango_consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "mango-producer")
public interface MangoProducerService {

    @RequestMapping("/hello")
    public String hello();

}
