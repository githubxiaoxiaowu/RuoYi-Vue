package com.ruoyi.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.ruoyi.code.domain.WebsiteMarketingRecord;
import com.ruoyi.code.mapper.TiktokMarketingRecordMapper;
import com.ruoyi.code.service.ITiktokMarketingRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.code.service.bean.TiktokMarketingRecordQuery;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 抖音营销记录 服务实现类
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
@Service
public class TiktokMarketingRecordServiceImpl extends ServiceImpl<TiktokMarketingRecordMapper, TiktokMarketingRecord> implements ITiktokMarketingRecordService {

    @Override
    public IPage<TiktokMarketingRecord> list(IPage page, TiktokMarketingRecordQuery tiktokMarketingRecordQuery) {
        LambdaQueryWrapper<TiktokMarketingRecord> lambdaQueryWrapper=new LambdaQueryWrapper<TiktokMarketingRecord>();
        Long userId = SecurityUtils.getUserId();
        if(!SecurityUtils.isAdmin(userId)){
            lambdaQueryWrapper.eq(TiktokMarketingRecord::getUserId,userId);
        }
        lambdaQueryWrapper
                .like(StringUtils.isNotEmpty(tiktokMarketingRecordQuery.getKeyWord()),TiktokMarketingRecord::getKeyWord,tiktokMarketingRecordQuery.getKeyWord())
                .eq(StringUtils.isNotEmpty(tiktokMarketingRecordQuery.getAuditStatus()),TiktokMarketingRecord::getAuditStatus,tiktokMarketingRecordQuery.getAuditStatus())
                .like(StringUtils.isNotEmpty(tiktokMarketingRecordQuery.getTiktokUser()),TiktokMarketingRecord::getTiktokUser,tiktokMarketingRecordQuery.getTiktokUser())
                .orderByDesc(TiktokMarketingRecord::getCreateTime);;
        return this.getBaseMapper().selectPage(page,lambdaQueryWrapper);
    }
}
