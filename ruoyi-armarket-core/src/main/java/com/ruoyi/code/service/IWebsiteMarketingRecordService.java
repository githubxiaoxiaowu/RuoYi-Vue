package com.ruoyi.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.code.domain.WebsiteMarketingRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.code.mapper.WebsiteMarketingRecordMapper;
import com.ruoyi.code.service.bean.WebsiteMarketingRecordQuery;

import java.util.List;

/**
 * <p>
 * 网站推广记录 服务类
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
public interface IWebsiteMarketingRecordService extends IService<WebsiteMarketingRecord> {
    List<WebsiteMarketingRecord> list(IPage page, WebsiteMarketingRecordQuery websiteMarketingRecordQuery);

     boolean audit(Long id,boolean isPass);

}
