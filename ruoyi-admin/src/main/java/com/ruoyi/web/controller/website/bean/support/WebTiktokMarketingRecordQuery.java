package com.ruoyi.web.controller.website.bean.support;

import com.ruoyi.code.service.bean.TiktokMarketingRecordQuery;
import com.ruoyi.web.controller.tiktok.bean.TiktokMarketingRecordQueryBean;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-10 10:08
 **/
public class WebTiktokMarketingRecordQuery extends TiktokMarketingRecordQuery {

    private TiktokMarketingRecordQueryBean tiktokMarketingRecordQueryBean;
    public WebTiktokMarketingRecordQuery(TiktokMarketingRecordQueryBean tiktokMarketingRecordQueryBean){
        this. tiktokMarketingRecordQueryBean=tiktokMarketingRecordQueryBean;
    }

    @Override
    public String getKeyWord() {
        return tiktokMarketingRecordQueryBean.getKeyWord();
    }

    @Override
    public String getAuditStatus() {
        return tiktokMarketingRecordQueryBean.getAuditStatus();
    }

    @Override
    public String getTiktokUser() {
        return tiktokMarketingRecordQueryBean.getTiktokUser();
    }
}
