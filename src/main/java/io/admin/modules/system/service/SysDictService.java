
package io.admin.modules.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import io.admin.common.utils.tree.TreeTool;
import io.admin.common.dto.Option;
import io.admin.modules.system.dto.response.SysDictTreeResponse;
import io.admin.modules.system.dao.SysDictDao;
import io.admin.modules.system.dao.SysDictItemDao;
import io.admin.modules.system.entity.SysDict;
import io.admin.modules.system.entity.SysDictItem;
import io.admin.framework.data.domain.BaseEntity;
import io.admin.framework.data.service.BaseService;
import io.admin.framework.data.query.JpaQuery;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SysDictService extends BaseService<SysDict> {

    @Resource
    private SysDictItemDao sysDictItemDao;

    @Resource
    private SysDictDao sysDictDao;

    /**
     * 初始一个数据字典
     * 先判断是否存在
     *
     * @param code
     * @param text
     * @return
     */
    @Transactional
    public SysDict init(String code, String text, String... itemCodeTextArr) {
        SysDict old = sysDictDao.findByCode(code);
        if (old != null) {
            log.info("字典已存在，忽略初始化。 {}={}", code, text);
            return old;
        }
        SysDict dict = new SysDict();
        dict.setCode(code);
        dict.setText(text);
        dict = sysDictDao.save(dict);

        for (int i = 0; i < itemCodeTextArr.length; i = i + 2) {
            String itemCode = itemCodeTextArr[i];
            String itemValue = itemCodeTextArr[i + 1];
            sysDictItemDao.add(dict, itemCode, itemValue);
        }

        return dict;
    }

    public String findTextByDictCodeAndKey(String code, String key) {
        return sysDictItemDao.findText(code, key);
    }

    public List<Option> findOptions(String code) {
        List<SysDictItem> list = sysDictItemDao.findAllByDictCode(code);

        return Option.convertList(list, BaseEntity::getId, SysDictItem::getText);
    }


    public List<SysDictTreeResponse> tree() {
        List<SysDictTreeResponse> resultList = CollectionUtil.newArrayList();

        JpaQuery<SysDict> query = new JpaQuery<>();
        List<SysDict> typeList = sysDictDao.findAll(query);

        for (SysDict sysDict : typeList) {
            SysDictTreeResponse sysDictTreeNode = new SysDictTreeResponse();
            BeanUtil.copyProperties(sysDict, sysDictTreeNode);
            sysDictTreeNode.setPid(null);
            sysDictTreeNode.setName(sysDict.getText());
            resultList.add(sysDictTreeNode);
        }


        List<SysDictItem> dictData = sysDictItemDao.findAll(Sort.by(SysDictItem.Fields.seq));
        for (SysDictItem item : dictData) {
            SysDictTreeResponse node = new SysDictTreeResponse();
            node.setId(item.getId());
            node.setPid(item.getSysDict().getId());
            node.setCode(getFinalKey(item));
            node.setName(item.getText());
            node.setColor(item.getColor());
            resultList.add(node);
        }
        return TreeTool.buildTree(resultList, SysDictTreeResponse::getId, SysDictTreeResponse::getPid,SysDictTreeResponse::getChildren, SysDictTreeResponse::setChildren);
    }

    public Object getFinalKey(SysDictItem item) {
        String code = item.getCode();
        return code;
    }

}
