package com.longfor.it.providerone.service.impl;

import com.longfor.it.providerone.mapper.ProviderOneMapper;
import com.longfor.it.providerone.service.ProviderOneService;
import com.longfor.it.providerone.service.ProviderOneTccAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daiguanjun
 */
@Service
@Transactional
@Slf4j
public class ProviderOneServiceImpl implements ProviderOneService {
    @Autowired
    private ProviderOneMapper providerMapper;

    @Autowired
    private ProviderOneTccAction providerOneTccAction;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private final AtomicInteger i = new AtomicInteger();

    @Override
    public void test1() {
        // AT
        providerMapper.insert(UUID.randomUUID().toString(), "第一个服务 - " + i.incrementAndGet(), 0);
        // TCC
//        providerOneTccAction.prepareProviderOne(null, UUID.randomUUID().toString(), "第一个服务 - " + i.incrementAndGet(), 0);
    }

    @Override
    public boolean test3() {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    providerMapper.insert(UUID.randomUUID().toString(), "第一个服务 - " + i.incrementAndGet(), 1);
                    return true;
                } catch (Exception e) {
                    log.error("第一个服务发生异常:{}", e.getMessage());
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
    }
}
