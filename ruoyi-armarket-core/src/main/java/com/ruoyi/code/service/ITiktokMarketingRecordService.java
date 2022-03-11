package com.ruoyi.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.code.service.bean.TiktokMarketingRecordQuery;

import java.util.List;

/**
 * <p>
 * 抖音营销记录 服务类
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
public interface ITiktokMarketingRecordService extends IService<TiktokMarketingRecord> {

    IPage<TiktokMarketingRecord> list(IPage page, TiktokMarketingRecordQuery tiktokMarketingRecordQuery);
}
