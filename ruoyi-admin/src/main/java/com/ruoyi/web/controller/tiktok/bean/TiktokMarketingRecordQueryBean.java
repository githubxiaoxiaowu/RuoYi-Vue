package com.ruoyi.web.controller.tiktok.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-10 10:02
 **/
@Data
@ApiModel("抖音推广查询bean")
public class TiktokMarketingRecordQueryBean {
    /**
     * 抖音连接
     */
  /*  @ApiParam("抖音连接")
    private String tiktokUrl;*/

    /**
     * 抖音用户
     */
    @ApiParam("抖音用户模糊查询")
    private String tiktokUser;

    /**
     * 抖音昵称
     */
   /* @ApiParam("抖音昵称模糊查询")
    private String tiktokNickname;*/


    /**
     * 关键词
     */
    @ApiParam("关键词")
    private String keyWord;
    @ApiParam(value = "审核状态",example = "[参考sys_audit_estatus字典值]")
    private String auditStatus;
}
