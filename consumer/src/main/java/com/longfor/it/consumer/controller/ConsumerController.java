package com.longfor.it.consumer.controller;

import com.longfor.it.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daiguanjun
 */
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("test1")
    public String test1() {
        consumerService.test1();
        return "success！";
    }

    @RequestMapping("test2")
    public String test2() {
        consumerService.test2();
        return "success！";
    }

}
