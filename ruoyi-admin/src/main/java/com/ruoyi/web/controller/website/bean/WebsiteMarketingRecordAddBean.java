package com.ruoyi.web.controller.website.bean;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 23:35
 **/
@Data
public class WebsiteMarketingRecordAddBean {
    /**
     * 关键词
     */
    @ApiParam("关键词")
    @NotBlank (message = "关键词不能为空")
    private String keyWord;



    /**
     * 网站url
     */
    @ApiParam("网站url")
    @NotBlank(message = "网站url不能为空")
    private String websiteUrl;

    /**
     * 搜索引擎
     */
    @ApiParam(value = "搜索引擎渠道",example = "GOOGLE,UC,BAIDU[参考web_search_engine字典值]")
    @NotBlank (message = "搜索引擎渠道不能为空")
    private String searchEngines;




    /**
     * 备注
     */
    private String remark;
}
