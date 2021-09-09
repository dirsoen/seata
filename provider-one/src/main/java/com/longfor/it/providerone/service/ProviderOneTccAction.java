package com.longfor.it.providerone.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author admin
 */
@LocalTCC
public interface ProviderOneTccAction {
    @TwoPhaseBusinessAction(name = "providerOneTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepareProviderOne(BusinessActionContext businessActionContext,
                               @BusinessActionContextParameter(paramName = "id") String id,
                               @BusinessActionContextParameter(paramName = "name") String name,
                               @BusinessActionContextParameter(paramName = "status") int status);

    boolean commit(BusinessActionContext businessActionContext);

    boolean rollback(BusinessActionContext businessActionContext);
}
