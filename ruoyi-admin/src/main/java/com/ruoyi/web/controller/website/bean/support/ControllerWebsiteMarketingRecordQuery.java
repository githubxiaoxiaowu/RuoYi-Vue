package com.ruoyi.web.controller.website.bean.support;

import com.ruoyi.code.service.bean.WebsiteMarketingRecordQuery;
import com.ruoyi.web.controller.website.bean.WebsiteMarketingRecordQueryBean;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-10 11:03
 **/
public class ControllerWebsiteMarketingRecordQuery extends WebsiteMarketingRecordQuery {

    private WebsiteMarketingRecordQueryBean websiteMarketingRecordQuery;
    public ControllerWebsiteMarketingRecordQuery(WebsiteMarketingRecordQueryBean websiteMarketingRecordQuery){
        this.websiteMarketingRecordQuery=websiteMarketingRecordQuery;
    }
    @Override
    public String getKeyWord() {
        return websiteMarketingRecordQuery.getKeyWord();
    }

    @Override
    public String getAuditStatus() {
        return websiteMarketingRecordQuery.getAuditStatus();
    }

    @Override
    public String getSearchEngines() {
        return websiteMarketingRecordQuery.getSearchEngines();
    }
}
