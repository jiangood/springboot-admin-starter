
package io.admin.modules.system.controller;


import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.data.query.JpaQuery;
import io.admin.modules.system.service.SysConfigService;
import io.admin.framework.config.security.HasPermission;
import io.admin.common.dto.AjaxResult;
import io.admin.modules.system.entity.SysConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;



/**
 * 参数配置
 */
@RestController
@RequestMapping("admin/sysConfig")
public class SysConfigController  {

  @Resource
  private SysConfigService service;



  @HasPermission("sysConfig:page")
  @RequestMapping("page")
  public AjaxResult page(String searchText,@PageableDefault(sort = {"id"}) Pageable pageable) throws Exception {
    JpaQuery<SysConfig> q= new JpaQuery<>();
    q.searchText(searchText, SysConfig.Fields.label, "id", SysConfig.Fields.remark);
    Page<SysConfig> page = service.findAll(q, pageable);

    return AjaxResult.ok().data(page);
  }

  @HasPermission("sysConfig:save")
  @PostMapping("save")
  public AjaxResult save(@RequestBody SysConfig param, RequestBodyKeys updateFields) throws Exception {
    Assert.state(!param.isNew(), "仅限修改");
    SysConfig result = service.saveOrUpdateByRequest(param,updateFields);
    return AjaxResult.ok().data( result.getId()).msg("保存成功");
  }

  @HasPermission("sysConfig:delete")
  @RequestMapping("delete")
  public AjaxResult delete(String id) {
    service.deleteById(id);
    return AjaxResult.ok();
  }


}


