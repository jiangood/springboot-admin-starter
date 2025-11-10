package io.admin.modules.api.service;

import io.admin.modules.api.entity.ApiAccount;
import io.admin.framework.data.service.BaseService;
import io.admin.modules.api.dao.ApiAccountDao;
import io.admin.framework.data.query.JpaQuery;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApiAccountService extends BaseService<ApiAccount> {

    @Resource
    ApiAccountDao apiAccountDao;

    public ApiAccount findByAppId(String appId) {
        JpaQuery<ApiAccount> q = new JpaQuery<>();
        q.eq(ApiAccount.Fields.appId, appId);
        return apiAccountDao.findOne(q);
    }
}
