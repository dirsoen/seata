package com.longfor.it.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@FeignClient("seata-provider2")
public interface Provider2 {
    @RequestMapping("test1")
    String test1();

    @RequestMapping("test2")
    void test2();
}
