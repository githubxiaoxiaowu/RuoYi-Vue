package com.ruoyi.core.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.ruoyi.code.service.ITiktokMarketingRecordService;
import com.ruoyi.code.service.bean.TiktokMarketingRecordQuery;
import com.ruoyi.code.util.JSONUtil;
import com.ruoyi.core.AppTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 18:35
 **/
@Slf4j
public class TIktokServiceTest extends AppTest {

    @Resource
    private ITiktokMarketingRecordService tiktokMarketingRecordService;

    @Test
    public void  addTest(){
        TiktokMarketingRecord tiktokMarketingRecord=new TiktokMarketingRecord();
        tiktokMarketingRecord.setKeyWord("ss,ssdd");
        tiktokMarketingRecord.setTiktokUrl("http://sddada");
        tiktokMarketingRecord.setTiktokNickname("sadadad");
        tiktokMarketingRecord.setTiktokNickname("1111");
        tiktokMarketingRecordService.save(tiktokMarketingRecord);

    }

    @Test
    public void  listTest(){
        TiktokMarketingRecord tiktokMarketingRecord=new TiktokMarketingRecord();
        tiktokMarketingRecord.setKeyWord("ss,ssdd");
        tiktokMarketingRecord.setTiktokUrl("http://sddada");
        tiktokMarketingRecord.setTiktokNickname("sadadad");
        Page page=new Page<>(1,10);


        log.info("json={}",JSONUtil.toJsonString( tiktokMarketingRecordService.list(page, new TiktokMarketingRecordQuery() {
            @Override
            public String getKeyWord() {
                return "dd";
            }

            @Override
            public String getAuditStatus() {
                return "";
            }

            @Override
            public String getTiktokUser() {
                return "";
            }
        })));

    }
}
