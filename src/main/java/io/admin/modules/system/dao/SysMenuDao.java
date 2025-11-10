
package io.admin.modules.system.dao;


import cn.hutool.core.bean.BeanUtil;
import io.admin.framework.config.data.sysmenu.MenuDefinition;
import io.admin.framework.config.data.DataProp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class SysMenuDao {

    @jakarta.annotation.Resource
    private DataProp dataProp;


    /**
     * 扁平的列表
     *
     * @return
     */
    @SneakyThrows
    public List<MenuDefinition> findAll() {
        List<MenuDefinition> items = dataProp.getMenus();
        List<MenuDefinition> result = new ArrayList<>(items.size());
        for (MenuDefinition m : items) {
            // 克隆一个，但不设置children
            MenuDefinition cloned = new MenuDefinition();
            BeanUtil.copyProperties(m, cloned,"children");
            result.add(cloned);
        }
        return result;
    }





    public List<MenuDefinition> findAllById(List<String> ids) {
        List<MenuDefinition> list = this.findAll();
        return list.stream().filter(t->ids.contains(t.getId())).toList();
    }
}
