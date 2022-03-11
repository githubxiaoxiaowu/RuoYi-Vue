package com.ruoyi.web.controller.tiktok.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 23:37
 **/
@Data
@ApiModel("抖音推广添加bean")
public class TiktokMarketingRecordAddBean {
    /**
     * 抖音连接
     */
    @ApiParam("抖音连接")
    @NotBlank(message = "抖音连接不能为空")
    private String tiktokUrl;

    /**
     * 抖音用户
     */
    @ApiParam("抖音用户")
    @NotBlank(message = "抖音用户不能为空")
    private String tiktokUser;

    /**
     * 抖音昵称
     */
    @ApiParam("抖音昵称")
    @NotBlank(message = "抖音昵称不能为空")
    private String tiktokNickname;


    /**
     * 关键词
     */

    @ApiParam(value = "关键词模糊查询",example = "关键词 关键词2 关键词3")
    @NotBlank(message = "关键词不能为空")
    private String keyWord;


    /*
     * 备注
     */
    private String remark;
}
