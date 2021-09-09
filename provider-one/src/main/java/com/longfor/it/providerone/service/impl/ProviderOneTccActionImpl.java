package com.longfor.it.providerone.service.impl;

import com.longfor.it.providerone.entity.ResultHolder;
import com.longfor.it.providerone.mapper.ProviderOneMapper;
import com.longfor.it.providerone.service.ProviderOneTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author daiguanjun
 */
@Component
@Slf4j
public class ProviderOneTccActionImpl implements ProviderOneTccAction {
    @Autowired
    private ProviderOneMapper providerOneMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareProviderOne(BusinessActionContext businessActionContext, String id, String name, int status) {
        providerOneMapper.insert(id, name, status);
        ResultHolder.setResult(getClass(), businessActionContext.getXid(), "p");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        if (ResultHolder.getResult(getClass(), businessActionContext.getXid()) == null) {
            return true;
        }

        String id = businessActionContext.getActionContext("id").toString();
        // 修改表中数据状态 update table xxx
        providerOneMapper.updateStatus(id, 1);
        ResultHolder.removeResult(getClass(), businessActionContext.getXid());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        if (ResultHolder.getResult(getClass(), businessActionContext.getXid()) == null) {
            return true;
        }

        String id = businessActionContext.getActionContext("id").toString();
        // delete 操作 删除插入的数据
        providerOneMapper.deleteById(id);
        ResultHolder.removeResult(getClass(), businessActionContext.getXid());
        return true;
    }
}
