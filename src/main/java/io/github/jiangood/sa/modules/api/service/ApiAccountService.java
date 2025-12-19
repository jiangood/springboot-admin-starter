package io.github.jiangood.sa.modules.api.service;

import io.github.jiangood.sa.framework.data.service.BaseService;
import io.github.jiangood.sa.modules.api.dao.ApiAccountDao;
import io.github.jiangood.sa.modules.api.entity.ApiAccount;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApiAccountService extends BaseService<ApiAccount> {

    @Resource
    ApiAccountDao apiAccountDao;

    public ApiAccount findByAppId(String appId) {
        return apiAccountDao.findByField(ApiAccount.Fields.appId, appId);
    }
}
