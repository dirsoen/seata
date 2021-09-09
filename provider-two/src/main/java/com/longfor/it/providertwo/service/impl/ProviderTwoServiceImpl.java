package com.longfor.it.providertwo.service.impl;

import com.longfor.it.providertwo.mapper.ProviderTwoMapper;
import com.longfor.it.providertwo.service.ProviderTwoService;
import com.longfor.it.providertwo.service.ProviderTwoTccAction;
import io.seata.tm.api.TransactionalTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daiguanjun
 */
@Slf4j
@Service
@Transactional
public class ProviderTwoServiceImpl implements ProviderTwoService {
    @Autowired
    private ProviderTwoMapper providerMapper;

    @Autowired
    private ProviderTwoTccAction providerTwoTccAction;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private final AtomicInteger i = new AtomicInteger();

    @Override
    public void test1() {
        // AT
         providerMapper.insert(UUID.randomUUID().toString(), "第二个服务 - " + i.incrementAndGet(), 1);
        // TCC
//        providerTwoTccAction.prepareProviderTwo(null, UUID.randomUUID().toString(), "第二个服务 - " + i.incrementAndGet(), 0);
    }

    @Override
    public void test2() {
        // AT
         providerMapper.insert(UUID.randomUUID().toString(), "第二个服务 - " + i.incrementAndGet(), 1);
        // TCC
//        providerTwoTccAction.prepareProviderTwo(null, UUID.randomUUID().toString(), "第二个服务 - " + i.incrementAndGet(), 0);
        System.out.println(1 / 0);
    }

    @Override
    public boolean test3() {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                providerMapper.insert(UUID.randomUUID().toString(), "第二个服务 - " + i.incrementAndGet(), 1);
                return true;
            } catch (Exception e) {
                log.error("第二个服务执行失败:{}", e.getMessage());
                transactionStatus.setRollbackOnly();
                return false;
            }
        });
    }
}
