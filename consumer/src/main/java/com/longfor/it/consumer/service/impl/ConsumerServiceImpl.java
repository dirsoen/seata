package com.longfor.it.consumer.service.impl;

import com.longfor.it.consumer.feign.Provider1;
import com.longfor.it.consumer.feign.Provider2;
import com.longfor.it.consumer.service.ConsumerService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daiguanjun
 */
@Service
@GlobalTransactional
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private Provider1 provider1;

    @Autowired
    private Provider2 provider2;

    @Override
    public void test1() {

        provider1.test1();
        provider2.test1();
    }

    @Override
    public void test2() {
        provider1.test1();
        provider2.test2();
    }

}
