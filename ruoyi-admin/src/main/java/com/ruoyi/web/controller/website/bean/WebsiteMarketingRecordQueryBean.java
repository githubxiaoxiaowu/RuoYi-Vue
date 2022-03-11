package com.ruoyi.web.controller.website.bean;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-10 10:58
 **/
@Data
public class WebsiteMarketingRecordQueryBean {
    /**
     * 搜索引擎
     */
    @ApiParam("搜索引擎")
    private String searchEngines;

    /**
     * 关键词
     */
    @ApiParam("关键词模糊查询")
    private String keyWord;
    @ApiParam(value = "审核状态",example = "[参考sys_audit_estatus字典值]")
    private String auditStatus;
}
