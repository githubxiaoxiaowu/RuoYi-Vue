package com.ruoyi.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.ruoyi.code.domain.WebsiteMarketingRecord;
import com.ruoyi.code.mapper.WebsiteMarketingRecordMapper;
import com.ruoyi.code.service.IWebsiteMarketingRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.code.service.bean.WebsiteMarketingRecordQuery;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 网站推广记录 服务实现类
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
@Service
public class WebsiteMarketingRecordServiceImpl extends ServiceImpl<WebsiteMarketingRecordMapper, WebsiteMarketingRecord> implements IWebsiteMarketingRecordService {


    @Override
    public List<WebsiteMarketingRecord> list(IPage page, WebsiteMarketingRecordQuery websiteMarketingRecordQuery) {
        LambdaQueryWrapper<WebsiteMarketingRecord> lambdaQueryWrapper=new LambdaQueryWrapper<WebsiteMarketingRecord>();
        Long userId = SecurityUtils.getUserId();
        if(!SecurityUtils.isAdmin(userId)){
            lambdaQueryWrapper.eq(WebsiteMarketingRecord::getUserId,userId);
        }
        lambdaQueryWrapper
                .like(StringUtils.isNotEmpty(websiteMarketingRecordQuery.getKeyWord()),WebsiteMarketingRecord::getKeyWord,websiteMarketingRecordQuery.getKeyWord())
                .eq(StringUtils.isNotEmpty(websiteMarketingRecordQuery.getAuditStatus()),WebsiteMarketingRecord::getAuditStatus,websiteMarketingRecordQuery.getAuditStatus())
                .like(StringUtils.isNotEmpty(websiteMarketingRecordQuery.getSearchEngines()),WebsiteMarketingRecord::getSearchEngines,websiteMarketingRecordQuery.getSearchEngines())
        .orderByDesc(WebsiteMarketingRecord::getCreateTime);
        return this.getBaseMapper().selectPage(page,lambdaQueryWrapper).getRecords();
    }

    @Override
    public boolean audit(Long id, boolean isPass) {
        WebsiteMarketingRecord websiteMarketingRecordDB= this.getById(id);
        if(isPass){
            websiteMarketingRecordDB.pass();
        }else{
            websiteMarketingRecordDB.refuse();
        }
        return this.updateById(websiteMarketingRecordDB);
    }
}
