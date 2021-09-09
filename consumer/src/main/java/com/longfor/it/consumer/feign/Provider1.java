package com.longfor.it.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@FeignClient("seata-provider1")
public interface Provider1 {
    @RequestMapping("test1")
    String test1();
}
