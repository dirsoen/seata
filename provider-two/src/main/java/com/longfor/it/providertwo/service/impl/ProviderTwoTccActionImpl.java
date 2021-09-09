package com.longfor.it.providertwo.service.impl;

import com.longfor.it.providertwo.entity.ResultHolder;
import com.longfor.it.providertwo.mapper.ProviderTwoMapper;
import com.longfor.it.providertwo.service.ProviderTwoTccAction;
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
public class ProviderTwoTccActionImpl implements ProviderTwoTccAction {

    @Autowired
    private ProviderTwoMapper providerTwoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean prepareProviderTwo(BusinessActionContext businessActionContext, String id, String name, int status) {
        providerTwoMapper.insert(id, name, status);
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
        providerTwoMapper.updateStatus(id, 1);
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
        providerTwoMapper.deleteById(id);
        ResultHolder.removeResult(getClass(), businessActionContext.getXid());
        return true;
    }
}
