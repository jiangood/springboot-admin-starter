package io.github.jiangood.sa.modules.system.dao;

import io.github.jiangood.sa.framework.data.repository.BaseDao;
import io.github.jiangood.sa.modules.system.entity.SysFile;
import org.springframework.stereotype.Repository;

@Repository
public class SysFileDao extends BaseDao<SysFile> {

    public SysFile findByTradeNo(String tradeNo) {
        return this.findByField(SysFile.Fields.tradeNo, tradeNo);
    }
}
